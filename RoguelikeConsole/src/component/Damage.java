package component;

import base.Entity;
import base.Weapon;
import core.PlayerMessageOutput;

//Basic damage component, doesn't consider armor, probably don't use
public class Damage extends Component {

    public String damage(Entity attacking, Entity defending, int attackTimes, Weapon weapon)
    {
        String returnMessage;

        try
        {
            //Check if the weapon is degraded
            if (!weapon.isDegraded())
            {
                //Apply weapon damage to health
                defending.setBaseHealth(defending.getBaseHealth() - weapon.getDamage());
                returnMessage = generateFormattedMsg(attacking, defending, weapon, weapon.getDamage());
                log.info(returnMessage);
            }
            else
            {
                returnMessage = attacking.getDisplayName() + " attempted to damage " + defending.getDisplayName() + " using a worn out " + weapon.getDisplayName();
                log.info(returnMessage);
            }
        }
        catch (Exception ex)
        {
            log.error("Exception while calculating damage taken [" + ex.getMessage() + "]");
            ex.printStackTrace();
            returnMessage = "Error while assigning damage to " + defending.getDisplayName() + ".";
        }

        PlayerMessageOutput.addToPlayerMessageOutputBuffer(returnMessage);
        return returnMessage;
    }

    protected String generateFormattedMsg(Entity attacking, Entity defending, Weapon weapon, double damageTaken)
    {
        return defending.getDisplayName() + " took " + damageTaken + " damage from " + attacking.getDisplayName() + " using a " + weapon.getDisplayName();
    }

}
