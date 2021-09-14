package component;

import base.Character;
import base.Entity;
import base.Item;
import base.Potion;
import util.Enum;

import java.util.List;

//This component is meant to be used with any Potion items
public class DrinkPotionUse extends Use {

    @Override
    public String use(Character character, Item i, int direction, List<Entity> gameEntityList)
    {
        String returnMessage = "";

        //Check if the Item is of the potion class if not throw an error
        if (i.getClass().equals(Potion.class))
        {
            //Cast the item to a potion class now that we know it is a potion
            Potion p = (Potion) i;

            //Check to see if the potion has any modifiers
            if (p.getPotionModifier().size() > 0)
            {
                //Prep the return message and modifier count for the player
                String modifierMessage = "you drink and feel";
                int modifierCount = 0;

                //There is at least 1 potion modifier so we need to loop through them and apply them to the character
                for (Enum.PotionModifier trait : p.getPotionModifier().keySet())
                {
                    //Determine the type of modification and then apply the changes to the character and build the return message
                    switch (trait)
                    {
                        case CHA:
                        {
                            modifierMessage += formatPotionModifierMesssage(modifierCount, "charismatic", p.getPotionModifier().get(trait));
                            character.setCharisma(character.getCharisma() + p.getPotionModifier().get(trait));
                            break;
                        }
                        case INT:
                        {
                            modifierMessage += formatPotionModifierMesssage(modifierCount, "intelligent", p.getPotionModifier().get(trait));
                            character.setIntelligence(character.getIntelligence() + p.getPotionModifier().get(trait));
                            break;
                        }
                        case CON:
                        {
                            modifierMessage += formatPotionModifierMesssage(modifierCount, "sturdy", p.getPotionModifier().get(trait));
                            character.setConstitution(character.getConstitution() + p.getPotionModifier().get(trait));
                            break;
                        }
                        case STR:
                        {
                            modifierMessage += formatPotionModifierMesssage(modifierCount, "strong", p.getPotionModifier().get(trait));
                            character.setStrength(character.getStrength() + p.getPotionModifier().get(trait));
                            break;
                        }
                        case DEX:
                        {
                            modifierMessage += formatPotionModifierMesssage(modifierCount, "dexterous", p.getPotionModifier().get(trait));
                            character.setDexterity(character.getDexterity() + p.getPotionModifier().get(trait));
                            break;
                        }
                        case WIS:
                        {
                            modifierMessage += formatPotionModifierMesssage(modifierCount, "wise", p.getPotionModifier().get(trait));
                            character.setWisdom(character.getWisdom() + p.getPotionModifier().get(trait));
                            break;
                        }
                        case HEALTH:
                        {
                            modifierMessage += formatPotionModifierMesssage(modifierCount, "healthy", p.getPotionModifier().get(trait));
                            character.setBaseHealth(character.getBaseHealth() + p.getPotionModifier().get(trait));
                            break;
                        }
                        case MANA:
                        {
                            //Not yet implemented
                            log.warn("Character attempted to drink a potion with a mana modifier that has not been implemented yet.");
                        }
                    }

                    //Increment the modifierCount
                    modifierCount++;
                }

                //Add the modifierMessage to the returnMessage
                returnMessage = modifierMessage;
            }
            else
            {
                //There were no potionModifiers therefore the potion does nothing, return generic message to player
                returnMessage = "you drink but don't notice any differences";
            }
        }

        return returnMessage;
    }

    private String formatPotionModifierMesssage(int modifierCount, String text, int modifierAmount)
    {
        String returnMessage = "";

        //If the modifier is the first one than don't add comma
        if (modifierCount > 0)
        {
            returnMessage += ",";
        }

        returnMessage += " ";

        if (modifierAmount > 0)
        {
            //This is an increase in the trait
            returnMessage += "more ";
        }
        else if (modifierAmount < 0)
        {
            //This is a decrease in the trait
            returnMessage += "less ";
        }
        else
        {
            //This is no change in the trait
            log.warn("Potion with no modifier changes was consumed.");
            return "";
        }

        returnMessage += text;

        return returnMessage;
    }

}
