package Windows.ConversationWindows;

import Abilities.Passive.Professions.Steal;
import Conversations.Conversation;
import Conversations.DialogConversation;
import Conversations.QuestDialogConversation;
import Conversations.Shop;
import Creatures.LiveCreature;
import Creatures.PeacefulNPC.Peaceful;
import Creatures.Player;
import Windows.SupportWindows.SupportComponents.Console;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.Serializable;
import java.util.ArrayList;

public class ConversationWindow extends JFrame implements Serializable {

    private Player player;
    private LiveCreature opponent;
    private JPanel panel = new JPanel(new GridBagLayout());
    private GridBagConstraints constraints;
    private Console dialog = new Console();
    private int width = 720;
    private int height = 720;

    public ConversationWindow(LiveCreature opponent){
        super("Диалог с " + opponent.getName());
        setAlwaysOnTop(true);

        this.opponent = opponent;

        this.player = player;
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        addComponentListener(new ComponentListener() {
            @Override
            public void componentResized(ComponentEvent e) {}

            @Override
            public void componentMoved(ComponentEvent e) {}

            @Override
            public void componentShown(ComponentEvent e) {
                opponent.setConversationWindowOpen(true);
            }

            @Override
            public void componentHidden(ComponentEvent e) {
                opponent.setConversationWindowOpen(false);
            }
        });
        setPreferredSize(new Dimension(width, height));
        setMinimumSize(new Dimension(width, height));
        setMaximumSize(new Dimension(width, height));

        dialog = new Console();

        dialog.setSpeed(0);

        dialog.setSizeArea(width-30,height-240);

        getContentPane().add(dialog, BorderLayout.NORTH);

        drawWindow();
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    private void drawWindow(){
        Thread thread = new Thread(new Runnable(){
            public void run(){

                getContentPane().remove(panel);

                panel = new JPanel(new GridBagLayout());
                constraints = new GridBagConstraints();

                constraints.anchor = GridBagConstraints.NORTH;
                constraints.insets = new Insets(5, 0, 0, 0);
                constraints.gridx = 0;
                constraints.gridy = 0;

                for(int s = 0; s < opponent.getConversation().getConversationTree().size(); s++) {
                    for (int k = 0; k < opponent.getConversation().getConversationTree().get(s).size(); k++) {
                        if(opponent.getConversation().getConversationTree().get(s).get(k) == null || !opponent.getConversation().getConversationTree().get(s).get(k).getIsVisible()){
                            continue;
                        }
                        JPanel convPart = new JPanel(new GridBagLayout());
                        GridBagConstraints convPartconstraints = new GridBagConstraints();

                        convPartconstraints.anchor = GridBagConstraints.WEST;
                        convPartconstraints.insets = new Insets(0, 10, 0, 0);
                        convPartconstraints.gridx = 0;
                        convPartconstraints.gridy = 0;

                        JButton title = new JButton(opponent.getConversation().getConversationTree().get(s).get(k).getTitle());

                        title.setPreferredSize(new Dimension(width, 30));
                        title.setMinimumSize(new Dimension(width, 30));
                        title.setMaximumSize(new Dimension(width, 30));

                        int finalS = s;
                        int finalK = k;
                        title.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                if (opponent.getConversation().getConversationTree().get(finalS).get(finalK).getClass().toString().contains("Shop")) {
                                    ((Shop) opponent.getConversation().getConversationTree().get(finalS).get(finalK)).setPlayer(player);
                                    close();
                                    opponent.getConversation().getConversationTree().get(finalS).get(finalK).run();
                                } else if (opponent.getConversation().getConversationTree().get(finalS).get(finalK).getClass().toString().contains("DialogConversation")) {
                                    ((DialogConversation) opponent.getConversation().getConversationTree().get(finalS).get(finalK)).setPlayerName(player.getName());
                                    ((DialogConversation) opponent.getConversation().getConversationTree().get(finalS).get(finalK)).setOpponentName(opponent.getName());
                                    ((DialogConversation) opponent.getConversation().getConversationTree().get(finalS).get(finalK)).setConsole(dialog);
                                    if(opponent.getConversation().getConversationTree().get(finalS).get(finalK).getClass().toString().contains("QuestDialogConversation")){
                                        ((QuestDialogConversation)opponent.getConversation().getConversationTree().get(finalS).get(finalK)).setPlayer(player);
                                        ((QuestDialogConversation)opponent.getConversation().getConversationTree().get(finalS).get(finalK)).setPeaceful((Peaceful) opponent);
                                    }
                                    opponent.getConversation().getConversationTree().get(finalS).get(finalK).run();

                                    int size = opponent.getConversation().getConversationTree().get(finalS).get(finalK).getConversationTree().size();
                                    Conversation supportConversation = opponent.getConversation().getConversationTree().get(finalS).get(finalK);
                                    opponent.getConversation().getConversationTree().remove(finalS);
                                    for (int z = 0; z < size; z++){
                                        ArrayList<Conversation> conv = new ArrayList<>();
                                        for (int x = 0; x < supportConversation.getConversationTree().get(z).size(); x++){
                                            conv.add(supportConversation.getConversationTree().get(z).get(x));
                                        }
                                        opponent.getConversation().getConversationTree().add(z+1, conv);
                                    }
                                    setVisible(false);
                                    drawWindow();
                                }
                            }
                        });

                        convPart.add(title, convPartconstraints);

                        panel.add(convPart, constraints);
                        constraints.gridy++;
                    }
                }

                if(player != null && player.hasAbility(new Steal())){
                    JButton title = new JButton("Обокрасть");

                    title.setPreferredSize(new Dimension(width, 30));
                    title.setMinimumSize(new Dimension(width, 30));
                    title.setMaximumSize(new Dimension(width, 30));
                    title.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            ThiefWindow thiefWindow = new ThiefWindow(opponent, player);
                            close();
                        }
                    });

                    panel.add(title, constraints);
                }
                getContentPane().add(panel, BorderLayout.SOUTH);
                pack();
                setVisible(true);
            }
        });
        thread.run();
    }

    public void close(){
        dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }

    public void setIsVisible(boolean b) {
        setVisible(b);
        if(b){
            drawWindow();
        }
    }

    public Console getDialog() {
        return dialog;
    }
}
