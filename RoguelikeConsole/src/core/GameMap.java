package core;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class GameMap {

    char[][] gameMap = new char[72][100]; //[height][width] or [y][x]

    public void init() {
        for (int i = 0; i < gameMap.length; i++) {
            Arrays.fill(gameMap[i], ' ');
        }

        makeRoom(0,0, 19,19);
        makeRoom(28,2, 34,8);
    }

    public void makeRoom(int topLeftCornerX, int topLeftCornerY, int bottomRightCornerX, int bottomRightCornerY) {
        for (int i = topLeftCornerY; i <= bottomRightCornerY; i++) {
            for (int j = topLeftCornerX; j <= bottomRightCornerX; j++) {
                if (i == topLeftCornerY || i == bottomRightCornerY || j == topLeftCornerX || j == bottomRightCornerX) {
                    gameMap[i][j] = '#';
                }
                else {
                    gameMap[i][j] = '.';
                }
            }
        }
    }

}
