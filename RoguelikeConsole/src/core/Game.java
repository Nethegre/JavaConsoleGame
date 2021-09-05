package core;

import base.Entity;
import mocks.MockedPlayer;

import javax.swing.*;
import java.util.ArrayList;

public class Game {

    private GUI gui;
    private GameMap gameMap;
    private MockedPlayer mockedPlayer;
    private JTextPane mapTextPane;
    private ArrayList<Entity> entities = new ArrayList<>();

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

        /*do {
            gui.generateMapBase(gameMap.gameMap);
            gui.addEntityToMap(mockedPlayer);
            gui.drawMap();
        } while (shouldLoop);*/
        gui.generateMapBase_DocumentVersion(gameMap.gameMap);
        entities.add(mockedPlayer);
        do {
            updateEntities();
        } while (shouldLoop);
    }

    private void updateEntities() {
        for (Entity entity: entities) {
            if (entity.isNeedsUpdating()) {
                entity.setNeedsUpdating(false);
                gui.updateEntityOnMap_DocumentVersion(mockedPlayer, gameMap.gameMap);
            }
        }
    }
}
