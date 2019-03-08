package Windows.CraftWindow;

import Abilities.Passive.Professions.Alchemist;
import Creatures.Player;
import Items.Alchemy.Ingredients.Ingredient;
import Things.Craft.AlchemyTable;
import Windows.SupportWindows.SupportComponents.IngredientButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowEvent;

public class AlchemyTableWindow extends CraftWindow {

    private AlchemyTable alchemyTable;
    private JPanel panel;
    private Player player;
    private GridBagConstraints constraints;
    private Ingredient[] ingredients;
    private JPanel ingredientsPanel;
    private IngredientButton[] buttons;

    public AlchemyTableWindow(AlchemyTable parent){
        super("Алхимический стол");
        setAlwaysOnTop(true);
        alchemyTable = parent;
        buttons = new IngredientButton[]{null, null, null, null, null, null};
        ingredients = new Ingredient[]{null, null, null, null, null, null};
        ingredientsPanel = new JPanel();
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        addComponentListener(new ComponentListener() {
            @Override
            public void componentResized(ComponentEvent e) {}

            @Override
            public void componentMoved(ComponentEvent e) {}

            @Override
            public void componentShown(ComponentEvent e) {
                alchemyTable.setCraftTableWindowOpen(true);
            }

            @Override
            public void componentHidden(ComponentEvent e) {
                alchemyTable.setCraftTableWindowOpen(false);
            }
        });

        setPreferredSize(new Dimension(720,480));
        setMaximumSize(new Dimension(720,480));
        setMinimumSize(new Dimension(720,480));

        panel = new JPanel(new GridBagLayout());
        constraints = new GridBagConstraints();

        constraints.anchor = GridBagConstraints.NORTH;
        constraints.insets = new Insets(0, 10, 0, 10);
        constraints.gridx = 0;
        constraints.gridy = 0;

        drawWindow();
    }

    public void drawWindow(){
        getContentPane().remove(panel);

        int alchemyLevel = 0;
        if(player != null && player.hasAbility(new Alchemist())){
            alchemyLevel = player.getAbility(new Alchemist()).getLevel();
        }

        ingredientsPanel.removeAll();
        ingredientsPanel = new JPanel(new GridBagLayout());
        GridBagConstraints ingredientsConstraints = new GridBagConstraints();

        ingredientsConstraints.anchor = GridBagConstraints.NORTH;
        ingredientsConstraints.insets = new Insets(10, 10, 10, 10);
        ingredientsConstraints.gridx = 0;
        ingredientsConstraints.gridy = 0;
        for(int i = 0; i < 3 + alchemyLevel; i++){
            if(buttons[i] == null){
                IngredientButton button = new IngredientButton("Ингридиент");
                buttons[i] = button;
                button.setPreferredSize(new Dimension(180, 120));
                button.setMaximumSize(new Dimension(180, 120));
                button.setMinimumSize(new Dimension(180, 120));
                int finalI = i;
                button.addActionListener(e -> {
                    IngredientChooser ingredientChooser = new IngredientChooser(player, ingredients, finalI, button);
                });
            }
            ingredientsPanel.add(buttons[i], ingredientsConstraints);

            if(i < 2){
                ingredientsConstraints.gridx ++;
            }else if(i == 2){
                ingredientsConstraints.gridx = 1;
                ingredientsConstraints.gridy ++;
            } else if(i == 3){
                ingredientsConstraints.gridx = 0;
            } else if(i == 4){
                ingredientsConstraints.gridx = 2;
            } else if(i == 5){
                ingredientsConstraints.gridx = 1;
                ingredientsConstraints.gridy ++;
            }
        }
        constraints.gridy = 0;
        panel.add(ingredientsPanel, constraints);

        constraints.gridy ++;
        JButton create = new JButton("Создать");
        create.addActionListener(e -> {
            alchemyTable.clearCreatedPotion();
            alchemyTable.create(ingredients);
            for(int i = 0; i < buttons.length; i++){
                if(buttons[i] != null)
                    if(buttons[i].getCountOfIngredients() > 0){
                        player.removeItem(buttons[i].getIngredient());
                        buttons[i].setCountOfIngredients(buttons[i].getCountOfIngredients()-1);
                        buttons[i].writeText();
                        if(buttons[i].getCountOfIngredients() == 0){
                            buttons[i] = null;
                            ingredients[i] = null;
                            drawWindow();
                        }
                    } else {
                        buttons[i] = null;
                        ingredients[i] = null;
                        drawWindow();
                    }
            }
            if(alchemyTable.getCreatedPotion() != null){
                player.addItemToInventory(alchemyTable.getCreatedPotion());
            }
            player.getFieldWindow().drawAllPlayerWindow();
        });
        panel.add(create, constraints);
        getContentPane().add(panel);
        pack();
        setVisible(true);
    }

    public void setIsVisible(boolean visible){
        drawWindow();
        setVisible(visible);
    }

    public void close(){
        dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}