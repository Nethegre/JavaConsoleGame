package base;

import util.Enum;
import util.LogManager;

import java.util.UUID;

public class Base {

    protected UUID id = UUID.randomUUID();
    protected LogManager log = new LogManager(id);
    protected int xCoordinate = 0;
    protected int yCoordinate = 0;
    protected int prevXCoordinate = 0;
    protected int prevYCoordinate = 0;
    protected char displayCharacter;
    protected Enum.DisplayColor displayColor;

    public void setCoordinates(int x, int y) {
        xCoordinate = x;
        yCoordinate = y;
    }

    public int getxCoordinate() {
        return xCoordinate;
    }

    public void setxCoordinate(int xCoordinate) {

        this.prevXCoordinate = this.xCoordinate;
        this.xCoordinate = xCoordinate;
    }

    public int getyCoordinate() {
        return yCoordinate;
    }

    public void setyCoordinate(int yCoordinate) {

        this.prevYCoordinate = this.yCoordinate;
        this.yCoordinate = yCoordinate;
    }

    public void setPrevCoordinates(int prevX, int prevY) {
        prevXCoordinate = prevX;
        prevYCoordinate = prevY;
    }

    public int getPrevXCoordinate() {
        return prevXCoordinate;
    }

    public void setPrevXCoordinate(int prevXCoordinate) {
        this.prevXCoordinate = prevXCoordinate;
    }

    public int getPrevYCoordinate() {
        return prevYCoordinate;
    }

    public void setPrevYCoordinate(int prevYCoordinate) {
        this.prevYCoordinate = prevYCoordinate;
    }

    public char getDisplayCharacter() {
        return displayCharacter;
    }

    public void setDisplayCharacter(char displayCharacter) {
        this.displayCharacter = displayCharacter;
    }

    public Enum.DisplayColor getDisplayColor() {
        return displayColor;
    }

    public void setDisplayColor(Enum.DisplayColor displayColor) {
        this.displayColor = displayColor;
    }

}
