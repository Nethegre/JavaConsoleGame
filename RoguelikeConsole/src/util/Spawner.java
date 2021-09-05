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
        zombie.setDisplayCharacter('Z');
        zombie.setDisplayColor(Enum.DisplayColor.GREEN);

        return zombie;
    }

    public Character createPlayerCharacter(int x, int y) {
        Character player = new Character();
        player.setSupportsArmor(true);
        player.setDamageable(true);
        player.setBaseArmor(0);
        player.setBaseHealth(10);
        player.setMove(new Move());
        player.setDamage(new CharacterDamage());
        player.addToArmorInventory(Enum.ArmorLocation.CHEST);
        player.addToArmorInventory(Enum.ArmorLocation.LEGS);
        player.addToArmorInventory(Enum.ArmorLocation.SHOES);
        player.addToArmorInventory(Enum.ArmorLocation.HAT);
        player.setDisplayCharacter('@');
        player.setDisplayColor(Enum.DisplayColor.CYAN);
        player.setCoordinates(x, y);
        player.setPrevCoordinates(x, y);

        return player;
    }
}
