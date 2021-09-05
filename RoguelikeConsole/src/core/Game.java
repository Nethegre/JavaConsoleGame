package core;

import mocks.MockedPlayer;

import javax.swing.*;

public class Game {

    private GUI gui;
    private GameMap gameMap;
    private MockedPlayer mockedPlayer;
    private JTextPane mapTextPane;

    public Game() {
        mockedPlayer = new MockedPlayer();
        gui = new GUI(mockedPlayer);
        gameMap = new GameMap();
    }

    public Game(GUI gui, GameMap gameMap, MockedPlayer mockedPlayer) {
        this.gui = gui;
        this.gameMap = gameMap;
        this.mockedPlayer = mockedPlayer;
    }

    public void init() {
        mockedPlayer.setCoordinates(3,3);
        gameMap.init();
        mapTextPane = gui.getMapTextPane();
    }

    public void testLoop() throws InterruptedException {
        init();
        gameMap.testLoop(gui, true, false, false);
    }

    public void gameLoop() throws InterruptedException {
        init();

        boolean shouldLoop = true;

        do {
            gui.generateMapBase(gameMap.gameMap);
            gui.addEntityToMap(mockedPlayer);
            gui.drawMap();
        } while (shouldLoop);
    }

    private void drawEntitiesOnMap() {
        String mapString = mapTextPane.getText();
        int position = mockedPlayer.getxCoordinate() * 100 + mockedPlayer.getxCoordinate();
        mapString.toCharArray()[position] = '@';
        mapTextPane.setText(mapString);
    }
}
