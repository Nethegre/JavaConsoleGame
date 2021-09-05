package base;

import util.Enum;

public class Weapon extends Item {

    protected double damage;
    protected double toHit;
    protected Enum.WeaponTypes weaponType;

    public double getDamage() {
        return damage;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }

    public double getToHit() {
        return toHit;
    }

    public void setToHit(double toHit) {
        this.toHit = toHit;
    }

    public Enum.WeaponTypes getWeaponType() {
        return weaponType;
    }

    public void setWeaponType(Enum.WeaponTypes weaponType) {
        this.weaponType = weaponType;
    }
}
