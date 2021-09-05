package component;

import base.Base;
import base.Character;

public class Move extends Base {

    //Should probably check for walls  at some point
    public void move(Character character, int xOffset, int yOffset)
    {
        log.info("Character moving with the following x and y offset:" + xOffset + "," + yOffset);
        if (checkUserMoveDirection(xOffset, yOffset))
        {
            updateCoordinates(character, xOffset, yOffset);
        }
    }

    private void updateCoordinates(Character character, int xOffset, int yOffset)
    {
        //Check if the character is actually moving and if so set the needsUpdating flag
        if (xOffset != 0 || yOffset != 0)
        {
            character.setNeedsUpdating(true);

            character.setxCoordinate(character.getxCoordinate() + xOffset);
            character.setyCoordinate(character.getyCoordinate() + yOffset);
        }
    }

    private boolean checkUserMoveDirection(int xOffset, int yOffset)
    {
        return true;
    }
}
