package base;

public class InventoryEntry extends Base {

    public double count;
    protected Item item;

    public InventoryEntry(double count, Item item)
    {
        this.count = count;
        this.item = item;
    }

    public double removeItem(double count)
    {
        double removed = 0;

        if (this.count > count)
        {
            //If you are removing less than the total
            removed = count;
            this.count -= count;
        }
        else if (this.count == count)
        {
            //If you are removing the same as the total
            removed = count;
            this.count = 0;
        }
        else
        {
            //If you are attempting to remove more than the total
            removed = this.count;
            this.count = 0;
        }

        return removed;
    }

    public boolean isType(Item i)
    {
        //Check to see if the types are the same as what is currently stored
        return this.item.getClass().equals(i.getClass());
    }

    public boolean tryAddItem(Item item, double count, double remaining)
    {
        if (!item.stackable)
        {
            log.warn("Attempted to add item to stack that is not stackable.");
            return false;
        }
        else if (!this.isType(item))
        {
            log.warn("Attempted to combine different item types.{" + this.item.displayName + "," + item.displayName + "}");
            return false;
        }
        else
        {
            if (this.count + count > item.maxStackSize)
            {
                //Adding more than the maxStackSize so there will be leftover
                remaining = this.count + count - item.maxStackSize;
                this.count = item.maxStackSize;
            }
            else
            {
                //Adding less or equal to the maxStackSize so there will be no leftover
                remaining = 0;
                this.count += count;
            }
            return true;
        }
    }

}
