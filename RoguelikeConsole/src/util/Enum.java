package util;

import java.awt.*;

public class Enum {

    public enum ArmorLocation {
        HAT, CHEST, LEGS, GLOVES, RING, AMULET, SHOES, SHIELD, WEAPON, CAPE
    }

    public enum LogLevel {
        DEBUG, INFO, WARN, ERROR
    }

    public enum WeaponTypes {
        LIGHT, HEAVY, AVERAGE, MENTAL
    }

    public enum DisplayColor {
        BLUE (Color.blue),
        GREEN (Color.GREEN),
        YELLOW (Color.yellow),
        ORANGE (Color.orange),
        RED (Color.red),
        BLACK (Color.black),
        WHITE (Color.white),
        GRAY (Color.gray),
        CYAN (Color.cyan);

        private final Color color;
        DisplayColor(Color color) {
            this.color = color;
        }

        public Color getColor() {
            return color;
        }
    }

}
