package base;

import util.Enum;

public class Armor extends Item {

    protected Enum.ArmorLocation armorLocation;
    protected boolean stackable = false;
    protected int maxStackSize = 1;
    protected double armorClass;
    protected double dmgReduction; //less than 1 value that represents a percent of damage reduction

    public Enum.ArmorLocation getArmorLocation() {
        return armorLocation;
    }

    public void setArmorLocation(Enum.ArmorLocation armorLocation) {
        this.armorLocation = armorLocation;
    }

    public boolean isStackable() {
        return stackable;
    }

    public void setStackable(boolean stackable) {
        this.stackable = stackable;
    }

    public int getMaxStackSize() {
        return maxStackSize;
    }

    public void setMaxStackSize(int maxStackSize) {
        this.maxStackSize = maxStackSize;
    }

    public double getArmorClass() {
        return armorClass;
    }

    public void setArmorClass(double armorClass) {
        this.armorClass = armorClass;
    }

    public double getDmgReduction() {
        return dmgReduction;
    }

    public void setDmgReduction(double dmgReduction) {
        this.dmgReduction = dmgReduction;
    }
}
