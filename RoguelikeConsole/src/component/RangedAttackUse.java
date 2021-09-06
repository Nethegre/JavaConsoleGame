package component;

import base.Character;
import base.Entity;
import base.Item;
import base.Ranged;

import java.util.List;

//This use component will perform a ranged attack in the direction specified
public class RangedAttackUse extends Use {

    @Override
    public String use(Character character, Item i, int direction, List<Entity> gameEntityList)
    {
        String returnMessage = "";

        try
        {
            //Check to see if the current item is a ranged, if not than throw an error
            if (i.getClass().equals(Ranged.class))
            {
                //Cast the item into a ranged as we know it is one
                Ranged ranged = (Ranged) i;

                //Check to see if anything is within the range of the attack in the direction specified
                int attackingX, attackingY, xOffset = 0, yOffset = 0;

                //Get the offset to figure out what direction that attack is going
                translateDirectionToOffset(direction, xOffset, yOffset);
                boolean hitSomething = false;

                //Loop until the range of the attack is out
                for (int num = 0; num <= ranged.getRange(); num ++)
                {
                    //Update the attacking coordinates and check if an entity is there to take damage
                    attackingX = character.getxCoordinate() + xOffset;
                    attackingY = character.getyCoordinate() + yOffset;

                    //Check if there is anything at the current coordinates
                    for (Entity e : gameEntityList)
                    {
                        if (e.getxCoordinate() == attackingX && e.getyCoordinate() == attackingY)
                        {
                            //We found something that will be hit, apply damage
                            e.takeDamage(character, 1, ranged);
                            returnMessage = "you " + ranged.getAttackVerb() + " your " + ranged.getDisplayName() + " at a " + e.getDisplayName();
                            hitSomething = true;

                            break; //Don't check any further as we hit the first thing in the projectiles path
                        }
                    }

                    if (hitSomething)
                        break; //Need to break out of the main loop
                }

                if (!hitSomething)
                {
                    //Return a generic miss message
                    returnMessage = "you managed to miss everything";
                }
            }
            else
            {
                log.error("Attempt to use a RangedAttackUse component for an item that is not a ranged.");
                returnMessage = "Error while attacking";
            }
        }
        catch (Exception ex)
        {
            log.error("Exception performing rangedAttackUse. [" + ex.getMessage() + "]");
            ex.printStackTrace();
            returnMessage = "Error while attacking";
        }

        return returnMessage;
    }

}
