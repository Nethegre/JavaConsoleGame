package mocks;

import base.Entity;

public class MockedPlayer extends Entity {

    public void move(int xOffset, int yOffset) {
        log.info("Player moved " + xOffset + ", " + yOffset);
        setPrevCoordinates(xCoordinate, yCoordinate);
        xCoordinate += xOffset;
        yCoordinate += yOffset;
        needsUpdating = true;
    }
}
