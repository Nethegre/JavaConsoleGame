package base;

import component.Damage;

import java.util.Hashtable;

public class Entity extends Base {

    protected double baseHealth;
    protected double baseArmor;
    public boolean damageable;
    protected int inventorySize;
    protected Hashtable<Integer, InventoryEntry> inventory = new Hashtable<>();
    protected Damage damage;

    public boolean tryAddToInventory(Item item, double count, double remainder)
    {
        //Try to add to the existing entries
        for (Integer key = 0; key <= this.inventorySize -1; key ++)
        {
            if (inventory.containsKey(key)) //Makes sure that the inventory slot has items in it
            {
                if (inventory.get(key).tryAddItem(item, count, remainder))
                {
                    if (remainder == 0)
                    {
                        //If the remainder comes back 0 than everything has been added to the inventory slot
                        return true;
                    }
                    else
                    {
                        count = remainder;
                    }
                }
            }
        }
        //Try to add to the empty inventory slots because there was remainder
        for (int i = 0; i <= this.inventorySize -1; i ++)
        {
            if (!inventory.containsKey(i))
            {
                if (item.maxStackSize > count)
                {
                    //Check if the amount being added is larger than the maxStackSize and if so add a max stack to the current inventory slot
                    inventory.put(i, new InventoryEntry(item.maxStackSize, item));
                    count = count - item.maxStackSize;
                }
                else
                {
                    //Add the rest of the remaining item to the current inventory slot and return true
                    inventory.put(i, new InventoryEntry(count, item));
                    remainder = 0;
                    return true;
                }
            }
        }
        if (count > 0)
        {
            remainder = count;
            return false;
        }
        else
        {
            remainder = 0;
            return true;
        }
    }

    public boolean removeFromInventory(int location, double count, double removed)
    {
        //Make sure there are items in that inventory slot
        if (inventory.containsKey(location))
        {
            removed = inventory.get(location).removeItem(count);

            if (inventory.get(location).count == 0)
            {
                //Remove the inventory slot if it is now empty
                inventory.remove(location);
            }
            return true;
        }
        else
        {
            log.warn("Failed to remove items from inventory slot because it was already empty.");
            return false;
        }
    }

    public void takeDamage(double amount, Weapon weapon)
    {
        damage.damage(this, amount, weapon);
    }

}
