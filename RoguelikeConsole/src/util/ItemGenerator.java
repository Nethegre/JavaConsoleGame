package util;

import base.Item;
import base.Weapon;

public class ItemGenerator {

    public Weapon createSword()
    {
        Weapon w = new Weapon();
        w.setDegradable(true);
        w.setDamage(3);
        w.setToHit(3);
        w.setWeaponType(Enum.WeaponTypes.AVERAGE);
        w.setDisplayName("basic sword");
        w.setDisplayCharacter(')');
        w.setDisplayColor(Enum.DisplayColor.GRAY);
        w.setMaxDegrade(100);
        w.setDegrade(Math.random());

        return w;
    }

}
