package gui;

import gui.SwingActions.MoveAction;
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
        /*mapTextPane.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke("UP"), "moveUp");
        mapTextPane.getActionMap().put("moveUp", new MoveAction(mockedPlayer, 0, -1));*/
        newKeybindAndAction(mapTextPane, KeyStroke.getKeyStroke("UP"), "moveUp", new MoveAction(mockedPlayer, 0, -1));
        newKeybindAndAction(mapTextPane, KeyStroke.getKeyStroke("DOWN"), "moveDown", new MoveAction(mockedPlayer, 0, 1));
        newKeybindAndAction(mapTextPane, KeyStroke.getKeyStroke("LEFT"), "moveLeft", new MoveAction(mockedPlayer, -1, 0));
        newKeybindAndAction(mapTextPane, KeyStroke.getKeyStroke("RIGHT"), "moveRight", new MoveAction(mockedPlayer, 1, 0));
    }

    //TODO Add some kind of validation and error/exception to throw if condition is not 0-2
    private void newKeybindAndAction(JComponent jComponent, int condition, KeyStroke keyStroke, String actionMapKey, Action action) {
        /*
            int condition:
            JComponent.WHEN_FOCUSED = 0 -- Default for getInputMap()
            JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT = 1
            JComponent.WHEN_IN_FOCUSED_WINDOW = 2
        */
        newKeybind(jComponent, condition, keyStroke, actionMapKey);
        jComponent.getActionMap().put(actionMapKey, action);
    }

    private void newKeybindAndAction(JComponent jComponent, KeyStroke keyStroke, String actionMapKey, Action action) {
        newKeybind(jComponent, JComponent.WHEN_FOCUSED, keyStroke, actionMapKey);
        jComponent.getActionMap().put(actionMapKey, action);
    }

    private void newKeybind(JComponent jComponent, int condition, KeyStroke keyStroke, String actionMapKey) {
        jComponent.getInputMap(condition).put(keyStroke, actionMapKey);
    }
}
