package component;

import base.Entity;
import base.Item;
import core.Game;

//Normal drop action with the item ending up where the item was dropped
public class Drop extends Component {

    public String drop(Entity entity, Item i)
    {
        String returnMessage = "";

        try
        {
            //Update the items coordinates to the entity that was carrying it
            i.setxCoordinate(entity.getxCoordinate());
            i.setyCoordinate(entity.getyCoordinate());
            i.setPrevCoordinates(i.getxCoordinate(), i.getyCoordinate());

            //Mark the item as needs updating so it gets rendered
            i.setNeedsUpdating(true);

            //Add the item to the master list of items on the map
            Game.mapItems.add(i);
            returnMessage = "you drop " + i.getDisplayName() + " on the ground";
        }
        catch (Exception ex)
        {
            log.error("Exception while dropping item " + i.getDisplayName() + " [" + ex.getMessage() + "]");
            ex.printStackTrace();
            returnMessage = "Error while dropping item.";
        }

        return returnMessage;
    }
}
