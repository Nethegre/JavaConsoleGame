import gui.GUI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        GUI gui = new GUI();
        gui.init();

        char[][] gameMap = new char[300][300];

        for (int i = 0; i < 300; i++) {
            Arrays.fill(gameMap[i], '.');
        }

        for (int i = 0; i < 20; i++) {
            gameMap[i][0] = '#';
            gameMap[0][i] = '#';
            gameMap[i][19] = '#';
            gameMap[19][i] = '#';
        }

        gui.updateMap(gameMap);
    }
}
