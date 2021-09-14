package base;

import util.Enum;

import java.util.Hashtable;

public class Potion extends Item {

    public Potion()
    {
        this.degradable = false;
    }

    protected Hashtable<Enum.PotionModifier, Integer> potionModifier = new Hashtable<>();

    public Hashtable<Enum.PotionModifier, Integer> getPotionModifier() {
        return potionModifier;
    }

    public void setPotionModifier(Hashtable<Enum.PotionModifier, Integer> potionModifier) {
        this.potionModifier = potionModifier;
    }

}
