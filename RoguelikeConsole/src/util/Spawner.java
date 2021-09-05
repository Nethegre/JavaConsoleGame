package util;

import base.Base;
import base.Character;
import component.CharacterDamage;
import component.Move;

public class Spawner extends Base {

    //This class will spawn classes
    public Character createZombie()
    {
        Character zombie = new Character();
        zombie.setSupportsArmor(true);
        zombie.setDamageable(true);
        zombie.setBaseArmor(3);
        zombie.setBaseHealth(10);
        zombie.setMove(new Move());
        zombie.setDamage(new CharacterDamage());
        zombie.addToArmorInventory(Enum.ArmorLocation.CHEST);
        zombie.addToArmorInventory(Enum.ArmorLocation.LEGS);
        zombie.addToArmorInventory(Enum.ArmorLocation.SHOES);
        zombie.addToArmorInventory(Enum.ArmorLocation.HAT);

        return zombie;
    }
}
