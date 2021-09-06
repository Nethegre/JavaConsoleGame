package component;

import base.Item;

public class Degrade extends Component {

    public void degrade(Item i)
    {
        if (i.isDegradable())
        {
            i.setDegrade(-1);

            if (i.getDegrade() <= 0)
            {
                i.setDegraded(true);
                i.setDegrade(0);
            }
            else
            {
                i.setDegraded(false);
            }
        }
        else
        {
            log.info("Item " + i.getDisplayName() + " is not degradable.");
        }
    }
}
