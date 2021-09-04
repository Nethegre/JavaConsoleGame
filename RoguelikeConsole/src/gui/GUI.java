package gui;

import gui.SwingActions.moveAction;
import mocks.MockedPlayer;

import javax.swing.*;
import java.awt.*;

public class GUI {

    private JFrame mainFrame;
    private JTextPane mapTextPane;

    /* keybind stuff */
    MockedPlayer mockedPlayer;

    public GUI(MockedPlayer mockedPlayer) {
        this.mockedPlayer = mockedPlayer;
    }
    /* end keybind stuff */

    public void init() {
        mainFrame = new JFrame();

        //Text pane to display game map
        mapTextPane = new JTextPane();
        mapTextPane.setEditable(false);
        mapTextPane.setFont(new Font("monospaced", Font.PLAIN, 14));
        mapTextPane.setBounds(0,0,100*mapTextPane.getFont().getSize(),100*mapTextPane.getFont().getSize());
        mapTextPane.setForeground(Color.WHITE);
        mapTextPane.setBackground(Color.BLACK);
        mainFrame.add(mapTextPane);

        //JFrame window settings - has to happen last
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //So program actually stops when X is clicked
        mainFrame.setSize(mapTextPane.getWidth()+20,mapTextPane.getHeight()+50);
        mainFrame.setLayout(null);
        mainFrame.setVisible(true);

        initKeybinds();
    }

    public void updateMap(char[][] gameMap) {
        String newMap = "";

        gameMap[mockedPlayer.getyCoordinate()][mockedPlayer.getxCoordinate()] = '@';

        for (int i = 0; i < gameMap.length; i++) {
            for (int j = 0; j < gameMap[i].length; j++) {
                newMap += gameMap[i][j];
            }
            newMap += '\n';
        }

        mapTextPane.setText(newMap);
    }

    private void initKeybinds() {
        mapTextPane.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke("UP"), "moveUp");
        mapTextPane.getActionMap().put("moveUp", new moveAction(mockedPlayer, 0, -1));
    }
}
