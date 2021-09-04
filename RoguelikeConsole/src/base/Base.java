package base;

import util.LogManager;

import java.util.UUID;

public class Base {

    protected UUID id = UUID.randomUUID();
    protected LogManager log = new LogManager(id);
    protected int xCoordinate = 0, yCoordinate = 0;

    public void setCoordinates(int x, int y) {
        xCoordinate = x;
        yCoordinate = y;
    }

    public int getxCoordinate() {
        return xCoordinate;
    }

    public void setxCoordinate(int xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public int getyCoordinate() {
        return yCoordinate;
    }

    public void setyCoordinate(int yCoordinate) {
        this.yCoordinate = yCoordinate;
    }
}
