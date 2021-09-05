package component;

import base.Character;
import base.Entity;
import base.Item;

import java.util.List;

public abstract class Use extends Component {

    public void use(Character character, Item i, int direction, List<Entity> gameEntityList)
    {

    }

    protected void translateDirectionToOffset(int direction, int xOffset, int yOffset)
    {
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
    }
}
