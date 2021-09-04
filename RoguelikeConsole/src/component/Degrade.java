package component;

import base.Base;
import base.Item;

public class Degrade extends Base {

    public void degrade(Item i)
    {
        if (i.degradable)
        {
            i.degrade -= 1;

            if (i.degrade <= 0)
            {
                i.degraded = true;
                i.degrade = 0;
            }
            else
            {
                i.degraded = false;
            }
        }
        else
        {
            log.info("Item " + i.displayName + " is not degradable.");
        }
    }
}
