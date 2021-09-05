package core;

import base.Character;
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
    private String mapBase;
    private Document mapDocument;
    private SimpleAttributeSet playerAttributeSet;

    /* keybind stuff */
    Character playerCharacter;

    public GUI(Character character) {
        this.playerCharacter = character;
        init(character);
        mapDocument = mapTextPane.getDocument();
    }
    /* end keybind stuff */

    private void init(Character playerCharacter) {
        this.playerCharacter = playerCharacter;

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
            mapDocument.insertString(prevPosition, String.valueOf(gameMap[entity.getPrevYCoordinate()][entity.getPrevXCoordinate()]), null);

            int newPosition = entity.getyCoordinate() * 101 + entity.getxCoordinate();
            mapDocument.remove(newPosition, 1);
            mapDocument.insertString(newPosition, String.valueOf(entity.getDisplayCharacter()), entity.getAttributeSet());
        } catch (BadLocationException ex) {
            //TODO Probably should add this to logging
        }
    }

    private void initKeybinds() {
        newKeybindAndAction(mapTextPane, KeyStroke.getKeyStroke("UP"), "moveUp", new MoveAction(playerCharacter, 0, -1));
        newKeybindAndAction(mapTextPane, KeyStroke.getKeyStroke("DOWN"), "moveDown", new MoveAction(playerCharacter, 0, 1));
        newKeybindAndAction(mapTextPane, KeyStroke.getKeyStroke("LEFT"), "moveLeft", new MoveAction(playerCharacter, -1, 0));
        newKeybindAndAction(mapTextPane, KeyStroke.getKeyStroke("RIGHT"), "moveRight", new MoveAction(playerCharacter, 1, 0));
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

    public void setJFrameTitle(String title) {
        mainFrame.setTitle("Cycles: " + title);
    }
}
