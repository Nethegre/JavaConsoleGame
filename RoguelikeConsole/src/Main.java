import gui.GUI;
import gui.GameMap;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        GUI gui = new GUI();
        gui.init();

        GameMap gameMap = new GameMap();

        gameMap.init();
        gameMap.testLoop(gui, true, true);
    }
}
