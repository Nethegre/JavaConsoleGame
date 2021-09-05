package mocks;

import base.Entity;
import util.Enum;

public class MockedPlayer extends Entity {

    public MockedPlayer() {
        displayColor = Enum.DisplayColor.CYAN;
    }

    public void move(int xOffset, int yOffset) {
        log.info("Player moved " + xOffset + ", " + yOffset);
        setPrevCoordinates(xCoordinate, yCoordinate);
        xCoordinate += xOffset;
        yCoordinate += yOffset;
        needsUpdating = true;
    }
}
