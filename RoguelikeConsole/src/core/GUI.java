package core;

import base.Entity;
import core.SwingActions.MoveAction;
import mocks.MockedPlayer;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import java.awt.*;

public class GUI {

    private JFrame mainFrame;
    private JTextPane mapTextPane;
    private String mapBase, mapWithEntities;
    private Document mapDocument;
    private SimpleAttributeSet playerAttributeSet;

    /* keybind stuff */
    MockedPlayer mockedPlayer;

    public GUI(MockedPlayer mockedPlayer) {
        this.mockedPlayer = mockedPlayer;
        init();
        mapDocument = mapTextPane.getDocument();
    }
    /* end keybind stuff */

    private void init() {
        mainFrame = new JFrame();

        //Text pane to display game map
        mapTextPane = new JTextPane();
        mapTextPane.setEditable(false);
        mapTextPane.setFont(new Font("monospaced", Font.PLAIN, 14));
        mapTextPane.setBounds(0,0,100*mapTextPane.getFont().getSize(),100*mapTextPane.getFont().getSize());
        mapTextPane.setForeground(Color.WHITE);
        mapTextPane.setBackground(Color.BLACK);
        mapTextPane.setHighlighter(null); //Game would crash if anything was highlighted
        mainFrame.add(mapTextPane);

        //JFrame window settings - has to happen last
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //So program actually stops when X is clicked
        mainFrame.setSize(mapTextPane.getWidth()+20,mapTextPane.getHeight()+50);
        mainFrame.setLayout(null);
        mainFrame.setVisible(true);

        initKeybinds();

        playerAttributeSet = new SimpleAttributeSet();
        StyleConstants.setForeground(playerAttributeSet, Color.cyan);
    }

    public void generateMapBase(char[][] gameMap) {
        mapBase = "";

        for (int i = 0; i < gameMap.length; i++) {
            for (int j = 0; j < gameMap[i].length; j++) {
                mapBase += gameMap[i][j];
            }
            mapBase += '\n';
        }
    }

    public void addEntityToMap(MockedPlayer mockedPlayer) {
        mapWithEntities = mapBase;
        int position = mockedPlayer.getyCoordinate() * 101 + mockedPlayer.getxCoordinate();
        mapWithEntities = mapWithEntities.substring(0, position) + '@' + mapWithEntities.substring(position+1);
    }

    public void generateMapBase_DocumentVersion(char[][] gameMap) {
        mapBase = "";

        for (int i = 0; i < gameMap.length; i++) {
            for (int j = 0; j < gameMap[i].length; j++) {
                mapBase += gameMap[i][j];
            }
            mapBase += '\n';
        }

        mapTextPane.setText(mapBase);
    }

    public void updateEntityOnMap_DocumentVersion(Entity entity, char[][] gameMap) {
        int prevPosition = entity.getPrevYCoordinate() * 101 + entity.getPrevXCoordinate();
        try {
            mapDocument.remove(prevPosition, 1);
            mapDocument.insertString(prevPosition, Character.toString(gameMap[entity.getPrevYCoordinate()][entity.getPrevXCoordinate()]), null);

            int newPosition = entity.getyCoordinate() * 101 + entity.getxCoordinate();
            mapDocument.remove(newPosition, 1);
            mapDocument.insertString(newPosition, "@", playerAttributeSet);
        } catch (BadLocationException ex) {
            //TODO Probably should add this to logging
        }
    }

    public void drawMap() {
        mapTextPane.setText(mapWithEntities);
    }

    private void initKeybinds() {
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

    public JTextPane getMapTextPane() {
        return mapTextPane;
    }
}