package component;

import base.Character;
import base.Entity;
import base.Item;
import base.Ranged;

import java.util.List;

//This use component will perform a ranged attack in the direction specified
public class RangedAttackUse extends Use {

    @Override
    public void use(Character character, Item i, int direction, List<Entity> gameEntityList)
    {
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

                //Loop until the range of the attack is out
                for (int num = 0; num > ranged.getRange(); num ++)
                {
                    //Update the attacking coordinates and check if an entity is there to take damage
                    attackingX = character.getxCoordinate() + xOffset;
                    attackingY = character.getyCoordinate() + yOffset;

                    for (Entity e : gameEntityList)
                    {
                        if (e.getxCoordinate() == attackingX && e.getyCoordinate() == attackingY)
                        {
                            //We found something that will be hit, apply damage
                            e.takeDamage(character, 1, ranged);

                            break; //Don't check any further as we hit the first thing in the projectiles path
                        }
                    }
                }
            }
            else
            {
                log.error("Attempt to use a RangedAttackUse component for an item that is not a ranged.");
            }
        }
        catch (Exception ex)
        {
            log.error("Exception performing rangedAttackUse. [" + ex.getMessage() + "]");
            ex.printStackTrace();
        }
    }

}
