package base;

import util.LogManager;

import java.util.UUID;

public class Base {

    protected UUID id = UUID.randomUUID();
    protected LogManager log = new LogManager(id);
    protected int x = 0, y = 0;

    public void setCoordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
