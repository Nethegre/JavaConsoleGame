package core;

import base.Character;
import base.Entity;
import util.Spawner;

import java.util.ArrayList;

public class Game {

    private GUI gui;
    private GameMap gameMap;
    private Spawner spawner = new Spawner();
    private ArrayList<Entity> entities = new ArrayList<>();

    public Game() {
        Character playerCharacter = spawnCharacter("player", 3, 3);
        gui = new GUI(playerCharacter);
        gameMap = new GameMap();
    }

    public Game(GUI gui, GameMap gameMap) {
        this.gui = gui;
        this.gameMap = gameMap;
    }

    public void init() {
        gameMap.init();
    }

    public void gameLoop() throws InterruptedException {
        init();

        boolean shouldLoop = true;

        gui.generateMapBase_DocumentVersion(gameMap.gameMap);
        spawnCharacter("zombie", 5, 10);
        /*int cycleCount = 0;*/
        do {
            updateEntities();
            /*cycleCount++;
            gui.setJFrameTitle(Integer.toString(cycleCount));*/
        } while (shouldLoop);
    }

    private void updateEntities() {
        for (Entity entity: entities) {
            if (entity.isNeedsUpdating()) {
                entity.setNeedsUpdating(false);
                gui.updateEntityOnMap_DocumentVersion(entity, gameMap.gameMap);
            }
        }
    }

    private Character spawnCharacter(String whatToSpawn, int x, int y) {
        Character character = null;
        switch (whatToSpawn) {
            case "player":
                character = spawner.createPlayerCharacter(x, y);
                break;
            case "zombie":
                character = spawner.createZombie();
                character.setCoordinates(x, y);
                character.setPrevCoordinates(x, y);
                break;
            default:
                //TODO add log to say nothing spawned
                break;
        }
        if (character != null) {
            entities.add(character);
        }
        return character;
    }
}
