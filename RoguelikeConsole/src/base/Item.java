package base;

import component.Degrade;
import component.Drop;
import component.Use;

import java.util.List;

public class Item extends Base {

    public String displayName;
    public int maxStackSize;
    public boolean degradable;
    public boolean degraded;
    public boolean stackable;
    public boolean sellable;
    public double maxDegrade;
    public double degrade;
    public double cost;
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

}
