package component;

import base.Character;
import base.Entity;
import base.Item;
import base.Weapon;

import java.util.List;

//This use component will perform a mele attack in the direction specified
public class MeleAttackUse extends Use {

    @Override
    public void use(Character character, Item i, int direction, List<Entity> gameEntityList)
    {
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

                //Check for any entity at the attacking coordinates
                for (Entity e : gameEntityList)
                {
                    if (e.getxCoordinate() == attackingX && e.getyCoordinate() == attackingY)
                    {
                        //We found something that will be hit, apply damage
                        e.takeDamage(character, 1, weapon);

                        break; //Don't check any further as we hit what we were going to
                    }
                }
            }
            else
            {
                log.error("Attempt to use a CharacterDamage component for a takingDmg entity that is not a character.");
            }
        }
        catch (Exception ex)
        {
            log.error("Exception performing meleAttackUse. [" + ex.getMessage() + "]");
            ex.printStackTrace();
        }
    }

    private void translateDirectionToOffset(int direction, int xOffset, int yOffset)
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
