package mocks;

import base.Entity;

public class MockedPlayer extends Entity {

    public void move(int xOffset, int yOffset) {
        log.info("Player moved " + xOffset + ", " + yOffset);
        x += xOffset;
        y += yOffset;
    }
}
