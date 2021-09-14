package component;

import base.Character;
import base.Entity;
import base.Item;
import base.Weapon;
import core.PlayerMessageOutput;

import java.util.List;

//This use component will perform a mele attack in the direction specified
public class MeleAttackUse extends Use {

    @Override
    public String use(Character character, Item i, int direction, List<Entity> gameEntityList)
    {
        String returnMessage = "";

        try
        {
            //Check to see if the current item is a weapon, if not than throw error
            if (i.getClass().equals(Weapon.class))
            {
                //Cast the item into a weapon as we know it is one
                Weapon weapon = (Weapon) i;

                //Check to see if anything is within mele distance of the direction the attack is going
                int attackingX, attackingY, xOffset = 0, yOffset = 0;

                //Get the offset to create the coords that will be checked
                translateDirectionToOffset(direction, xOffset, yOffset);
                attackingX = character.getxCoordinate() + xOffset;
                attackingY = character.getyCoordinate() + yOffset;
                boolean hitSomething = false;

                //Check for any entity at the attacking coordinates
                for (Entity e : gameEntityList)
                {
                    if (e.getxCoordinate() == attackingX && e.getyCoordinate() == attackingY)
                    {
                        //We found something that will be hit, apply damage
                        e.takeDamage(character, 1, weapon);
                        returnMessage = "you swing your " + weapon.getDisplayName() + " at a " + e.getDisplayName();
                        hitSomething = true;

                        break; //Don't check any further as we hit what we were going to
                    }
                }

                if (!hitSomething)
                {
                    //Return a generic miss message
                    returnMessage = "you swing your " + weapon.getDisplayName() + " at nothing";
                }
            }
            else
            {
                log.error("Attempt to use a MeleAttackUse component for an item that is not a weapon.");
                returnMessage = "Error while attacking";
            }
        }
        catch (Exception ex)
        {
            log.error("Exception performing meleAttackUse. [" + ex.getMessage() + "]");
            ex.printStackTrace();
            returnMessage = "Error while attacking";
        }

        PlayerMessageOutput.addToPlayerMessageOutputBuffer(returnMessage);
        return returnMessage;
    }

}
