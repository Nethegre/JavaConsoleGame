package base;

import component.Degrade;
import component.Drop;
import component.Use;

import java.util.List;

public class Item extends Base {

    protected int maxStackSize;
    protected boolean degradable;
    protected boolean degraded;
    protected boolean stackable;
    protected boolean sellable;
    protected double maxDegrade;
    protected double degrade;
    protected double cost;
    protected Use primaryAction;
    protected Drop dropAction;
    protected Degrade degradeAction;

    public double getSellCost()
    {
        if (sellable)
        {
            return this.cost;
        }
        else
        {
            return -1;
        }
    }

    public void degradeAction()
    {
        degradeAction.degrade(this);
    }

    public void primaryAction(Character character, int direction, List<Entity> gameEntityList)
    {
        primaryAction.use(character, this, direction, gameEntityList);
    }

    public void dropAction()
    {
        dropAction.drop(this);
    }

    public int getMaxStackSize() {
        return maxStackSize;
    }

    public void setMaxStackSize(int maxStackSize) {
        this.maxStackSize = maxStackSize;
    }

    public boolean isDegradable() {
        return degradable;
    }

    public void setDegradable(boolean degradable) {
        this.degradable = degradable;
    }

    public boolean isDegraded() {
        return degraded;
    }

    public void setDegraded(boolean degraded) {
        this.degraded = degraded;
    }

    public boolean isStackable() {
        return stackable;
    }

    public void setStackable(boolean stackable) {
        this.stackable = stackable;
    }

    public boolean isSellable() {
        return sellable;
    }

    public void setSellable(boolean sellable) {
        this.sellable = sellable;
    }

    public double getMaxDegrade() {
        return maxDegrade;
    }

    public void setMaxDegrade(double maxDegrade) {
        this.maxDegrade = maxDegrade;
    }

    public double getDegrade() {
        return degrade;
    }

    public void setDegrade(double degrade) {
        this.degrade = degrade;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public Use getPrimaryAction() {
        return primaryAction;
    }

    public void setPrimaryAction(Use primaryAction) {
        this.primaryAction = primaryAction;
    }

    public Drop getDropAction() {
        return dropAction;
    }

    public void setDropAction(Drop dropAction) {
        this.dropAction = dropAction;
    }

    public Degrade getDegradeAction() {
        return degradeAction;
    }

    public void setDegradeAction(Degrade degradeAction) {
        this.degradeAction = degradeAction;
    }

}
