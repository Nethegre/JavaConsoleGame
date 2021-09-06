package component;

import base.Character;
import base.Entity;
import base.Item;

import java.util.List;

//This will call the drop component whenever this item is "used"
public class DropUse extends Use {

    @Override
    public String use(Character character, Item i, int direction, List<Entity> gameEntityList)
    {
        return i.dropAction(character);
    }
}
