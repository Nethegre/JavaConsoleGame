package component;

import base.Character;
import base.Entity;
import core.PlayerMessageOutput;

import java.util.List;

public class Move extends Component {

    public String move(Character character, int xOffset, int yOffset, List<Entity> gameEntityList)
    {
        String returnMessage = "";

        //Check if the character is actually moving
        if (xOffset != 0 || yOffset != 0)
        {
            int xCoordinate = character.getxCoordinate() + xOffset;
            int yCoordinate = character.getyCoordinate() + yOffset;
            String blockingEntityName = "";

            log.info("Character moving to the following x and y coordinates:" + xCoordinate + "," + yCoordinate);
            if (checkUserMoveDirection(xCoordinate, yCoordinate, gameEntityList, blockingEntityName))
            {
                updateCoordinates(character, xCoordinate, yCoordinate);
            }
            else
            {
                //Character was not able to move due to something in the way
                log.info("Character was unable to move due to " + blockingEntityName + " being in the way.");
                returnMessage = "you were not able to move because " + blockingEntityName + " was in the way";
            }
        }
        else
        {
            //Character is not actually moving anywhere
            log.info("Character stood still for a turn.");
            returnMessage = "you stand still for a moment";
        }

        PlayerMessageOutput.addToPlayerMessageOutputBuffer(returnMessage);
        return returnMessage;
    }

    private void updateCoordinates(Character character, int xCoordinate, int yCoordinate)
    {
        //Update the needsUpdating flag in order to make sure the character position gets rendered
        character.setxCoordinate(xCoordinate);
        character.setyCoordinate(yCoordinate);

        character.setNeedsUpdating(true);
    }

    private boolean checkUserMoveDirection(int xCoordinate, int yCoordinate, List<Entity> gameEntityList, String blockingEntityName)
    {
        boolean succesfulMove = true;

        //Loop through all of the gameEntities and check if there is an entity at the location of the move
        for (Entity e : gameEntityList)
        {
            if (e.getxCoordinate() == xCoordinate && e.getyCoordinate() == yCoordinate)
            {
                if (e.isTakesWholeTile())
                {
                    //Found a blocking entity
                    succesfulMove = false;
                    blockingEntityName = e.getDisplayName();
                    break; //Don't need to search any futher
                }
            }
        }

        return succesfulMove;
    }
}
