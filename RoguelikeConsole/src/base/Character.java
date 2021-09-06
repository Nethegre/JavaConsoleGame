package base;

import component.Move;
import util.Enum;

import java.util.Hashtable;
import java.util.List;

public class Character extends Entity {

    protected Hashtable<Enum.ArmorLocation, InventoryEntry> armorInventory = new Hashtable<>();
    protected Move move;
    protected boolean supportsArmor;
    protected int charisma, intelligence, strength, dexterity, constitution, wisdom;

    public String move(int direction, List<Entity> gameEntityList)
    {
        int xOffset = 0, yOffset = 0;

        switch (direction)
        {
            case 1:
                xOffset = -1;
                yOffset = 1;
                break;
            case 2:
                yOffset = 1;
                break;
            case 3:
                xOffset = 1;
                yOffset = 1;
                break;
            case 4:
                xOffset = -1;
                break;
            case 5:
                break;
            case 6:
                xOffset = 1;
                break;
            case 7:
                xOffset = -1;
                yOffset = -1;
                break;
            case 8:
                yOffset = -1;
                break;
            case 9:
                xOffset = 1;
                yOffset = -1;
                break;
        }

        return move.move(this, xOffset, yOffset, gameEntityList);
    }

    public String move(int xOffset, int yOffset, List<Entity> gameEntityList)
    {
        return move.move(this, xOffset, yOffset, gameEntityList);
    }

    public boolean equipArmor(Armor armor, Enum.ArmorLocation location)
    {
        boolean success = false;

        //Check if the character supports armor
        if (supportsArmor)
        {
            //Check if the armor slot is available for the character and whether or not it currently contains a piece of armor
            if (armorInventory.containsKey(location))
            {
                if (armorInventory.get(location).count == 0)
                {
                    //There is nothing equipped here so equip the new piece of armor
                    armorInventory.remove(location); //Need to remove the existing one first
                    armorInventory.put(location, new InventoryEntry(1, armor)); //Create new inventory entry as it will have a new guid etc.
                    log.info("Equipped the characters " + location + " armor.");
                    success = true;
                }
                else
                {
                    //There is a piece of armor equipped here so swap the piece of armor
                    Item removedArmor = armorInventory.get(location).item; //Save the piece of armor that is being removed
                    armorInventory.remove(location); //Remove the existing armor slot first
                    armorInventory.put(location, new InventoryEntry(1, armor)); //Create new inventory entry as it will have a new guid etc.

                    //Attempt to add the current armor to the characters inventory
                    if (tryAddToInventory(removedArmor, 1, 0))
                    {
                        //Successfully added the piece of armor back into the characters inventory
                        log.info("Equipped the characters " + location + " armor and placed the existing armor into their inventory.");
                        success = true;
                    }
                    else
                    {
                        //Need to drop the previously equipped armor because the character can't store it in their inventory
                        removedArmor.dropAction(this);
                        log.info("Equipped the characters " + location + " armor and dropped the existing armor because they couldn't store it in their inventory.");
                        success = true;
                    }
                }
            }
            else
            {
                log.warn("Attempted to equip a piece of armor [" + armor.displayName + "] that was not supported by the character.");
            }
        }
        else
        {
            log.warn("Attempted to equip a piece of armor [" + armor.displayName + "] to a character that doesn't support armor.");
        }

        return success;
    }

    public boolean dequipArmor(Enum.ArmorLocation location)
    {
        boolean success = false;

        //Check if the character supports armor
        if (supportsArmor)
        {
            //Check if the armor slot is available
            if (armorInventory.containsKey(location))
            {
                //Check if there is a piece of armor in that slot
                if (armorInventory.get(location).count == 0)
                {
                    //Armor slot was empty
                    log.warn("Attempted to dequip a piece of armor [" + location + "] for a slot that was empty.");
                }
                else
                {
                    //There is a piece of armor here, dequip it now
                    Item removedArmor = armorInventory.get(location).item; //Save the piece of armor that is being removed
                    armorInventory.remove(location); //Remove the existing armor slot first
                    armorInventory.put(location, new InventoryEntry(0, null)); //Fill in the armor location with a null value and count = 0

                    //Attempt to add the current armor to the characters inventory
                    if (tryAddToInventory(removedArmor, 1, 0))
                    {
                        //Successfully added the piece of armor back into the characters inventory
                        log.info("Dequipped the characters " + location + " armor and placed it into their inventory.");
                        success = true;
                    }
                    else
                    {
                        //Need to drop the previously equipped armor because the character can't store it in their inventory
                        removedArmor.dropAction(this);
                        log.info("Dequipped the characters " + location + " armor and dropped itr because they couldn't store it in their inventory.");
                        success = true;
                    }
                }
            }
            else
            {
                log.warn("Attempted to dequip a piece of armor [" + location + "] that was not supported by the character.");
            }
        }
        else
        {
            log.warn("Attempted to dequip a piece of armor [" + location + "] from a character that doesn't support armor.");
        }

        return success;
    }

    public Hashtable<Enum.ArmorLocation, InventoryEntry> getArmorInventory() {
        return armorInventory;
    }

    public void addToArmorInventory(Enum.ArmorLocation location) {
        if (armorInventory.containsKey(location))
        {
            log.warn("Attempt to add armor location " + location + " that already exists for character.");
        }
        else
        {
            armorInventory.put(location, new InventoryEntry(0, null));
        }
    }

    public void setMove(Move move) {
        this.move = move;
    }

    public boolean isSupportsArmor() {
        return supportsArmor;
    }

    public void setSupportsArmor(boolean supportsArmor) {
        this.supportsArmor = supportsArmor;
    }

    public int getCharisma() {
        return charisma;
    }

    public void setCharisma(int charisma) {
        this.charisma = charisma;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getDexterity() {
        return dexterity;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public int getConstitution() {
        return constitution;
    }

    public void setConstitution(int constitution) {
        this.constitution = constitution;
    }

    public int getWisdom() {
        return wisdom;
    }

    public void setWisdom(int wisdom) {
        this.wisdom = wisdom;
    }
}
