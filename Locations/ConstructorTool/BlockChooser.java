package Locations.ConstructorTool;

import LiveCreatures.*;
import Locations.Map;
import Things.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class BlockChooser extends JFrame {

    private int width = 360;
    private int height = 720;
    private Block block;
    private GodCreature[][] map;
    private GodCreature[] blockListNotLive = new GodCreature[]{new GreatWallNullerField(), new Grass(), new Corpse(0,0), new BrickRoad(), new House(), new Stone(), new HealBlock(0,0), new Tree(), new Chest()};
    private GodCreature[] blockListLive = new GodCreature[]{ new Bandit(), new Dealer(0,0, "", 0,0), new Goblin(), new Knight()};

    private JPanel panel = new JPanel(new GridBagLayout());
    private GridBagConstraints constraints = new GridBagConstraints();
    private ToolMode toolMode;

    public BlockChooser(Block block, GodCreature[][] map, ToolMode toolMode){
        super("Выбор блока");

        this.block = block;
        this.map = map;
        this.toolMode = toolMode;

        setPreferredSize(new Dimension(width, height));
        setMinimumSize(new Dimension(width, height));
        setMaximumSize(new Dimension(width, height));
        setResizable(false);

        drawWindow();
    }


    public void drawWindow(){
        getContentPane().remove(panel);
        panel = new JPanel(new GridBagLayout());
        constraints = new GridBagConstraints();

        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.gridx = 0;
        constraints.gridy = 0;


        JButton build = new JButton("Строить");
        panel.add(build, constraints);
        constraints.gridx ++;
        JButton edit = new JButton("Редактировать");
        panel.add(edit, constraints);
        constraints.gridy ++;

        JLabel thingBlocks = new JLabel("Вещи: ");
        panel.add(thingBlocks, constraints);
        constraints.gridy ++;


        build.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                toolMode.setToolModeEnum(ToolModeEnum.BUILD);
                build.setBackground(new Color(0,255,0));
                edit.setBackground(new Color(255,255,255));
            }
        });
        edit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                toolMode.setToolModeEnum(ToolModeEnum.EDITOR);
                edit.setBackground(new Color(0,255,0));
                build.setBackground(new Color(255,255,255));
            }
        });
        for (GodCreature creature : blockListNotLive){
            JPanel blockPanel = new JPanel(new BorderLayout());

            JLabel blockName = new JLabel(creature.getName());

            JButton blockButton = new JButton("Выбрать");

            if(creature == block.getBlock()){
                blockButton.setBackground(new Color(0,255,0));
            } else {

                blockButton.setBackground(new Color(255,255,255));
            }

            blockButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (creature.getClass().toString().split("\\.")[1].split(" ")[0].equals("Chest")){
                        block.setEditable(true);
                    } else {
                        block.setEditable(false);
                    }
                    block.setBlock(creature);
                    block.setBlockType(BlockType.THING);
                    drawWindow();
                }
            });

            blockPanel.add(blockName, BorderLayout.LINE_START);
            blockPanel.add(blockButton, BorderLayout.LINE_END);

            panel.add(blockPanel, constraints);
            constraints.gridy ++;
        }

        JLabel personsBlocks = new JLabel("Персонажи:");
        panel.add(personsBlocks, constraints);
        constraints.gridy ++;

        for (GodCreature creature : blockListLive){
            JPanel blockPanel = new JPanel(new BorderLayout());

            JLabel blockName = new JLabel(creature.getName());

            JButton blockButton = new JButton("Выбрать");

            if(creature == block.getBlock()){
                blockButton.setBackground(new Color(0,255,0));
            } else {

                blockButton.setBackground(new Color(255,255,255));
            }


            blockButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (creature.getClass().toString().split(" ")[1].split("\\.")[0].equals("LiveCreatures")){
                        block.setEditable(true);
                    } else {
                        block.setEditable(false);
                    }
                    block.setBlock(creature);
                    block.setBlockType(BlockType.LIVECREATURE);
                    drawWindow();
                }
            });

            blockPanel.add(blockName, BorderLayout.LINE_START);
            blockPanel.add(blockButton, BorderLayout.LINE_END);

            panel.add(blockPanel, constraints);
            constraints.gridy ++;
        }

        JButton saveButton = new JButton("Сохранить");

        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try{
                    Map newMap = new Map(new Player(0,0,"",0,0), 20,20);
                    newMap.setMap(map);
                    FileOutputStream fos = new FileOutputStream("./temp.txt");
                    ObjectOutputStream outStream = new ObjectOutputStream(fos);
                    outStream.writeObject(newMap);
                    outStream.flush();
                    outStream.close();
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

            }
        });

        panel.add(saveButton, constraints);

        getContentPane().add(panel);
        pack();
        setVisible(true);
    }
}
