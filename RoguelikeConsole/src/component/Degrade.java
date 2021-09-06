package component;

import base.Item;

public class Degrade extends Component {

    public String degrade(Item i)
    {
        String returnMessage = "";

        if (i.isDegradable())
        {
            i.setDegrade(-1);

            if (i.getDegrade() <= 0)
            {
                i.setDegraded(true);
                i.setDegrade(0);
                returnMessage = "your " + i.getDisplayName() + " seems to be worn out from use";
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

        return returnMessage;
    }
}
