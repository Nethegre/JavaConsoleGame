import gui.GUI;
import gui.GameMap;
import gui.SwingActions.moveAction;
import mocks.MockedPlayer;

import javax.swing.*;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        /* Keybind stuff */
        MockedPlayer mockedPlayer = new MockedPlayer();
        mockedPlayer.setCoordinates(3,3);
        /* End keybind stuff */

        GUI gui = new GUI(mockedPlayer);
        gui.init();

        GameMap gameMap = new GameMap();

        gameMap.init();
        gameMap.testLoop(gui, true, false, false);
    }
}