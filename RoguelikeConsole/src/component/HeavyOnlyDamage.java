package component;

import base.Armor;
import base.Character;
import base.Entity;
import base.Weapon;
import core.PlayerMessageOutput;
import util.ConfigManager;
import util.Enum;

import java.util.ArrayList;
import java.util.List;

//This damage component will only apply damage if the incoming attack is using a HEAVY weapon, follows standard D&D esque damage rules
public class HeavyOnlyDamage extends Damage {

    protected double criticalHitPercent;
    protected double damageReductionMax;

    public HeavyOnlyDamage() {

        ConfigManager config = new ConfigManager();
        this.criticalHitPercent = Double.parseDouble(config.getConfigValue("CRITICALHITPERCENT"));
        this.damageReductionMax = Double.parseDouble(config.getConfigValue("DMGREDUCTIONMAX"));
    }

    @Override
    public String damage(Entity givingDmg, Entity takingDmg, int attackTimes, Weapon weapon)
    {
        String returnMessage = "";

        try
        {
            //Check to see if the weapon is of type HEAVY if not than ignore the damage
            if (weapon.getWeaponType() == Enum.WeaponTypes.HEAVY)
            {
                //Calculate damage based on "normal" dnd damage rules
                //Check if the entity is a Character, if not throw an exception
                if (takingDmg.getClass().equals(Character.class))
                {
                    if (givingDmg.getClass().equals(Character.class))
                    {
                        //Check to make sure the weapon is not degraded
                        if (weapon.isDegraded())
                        {
                            //Entity is of the character type
                            Character defending = (Character) takingDmg;
                            Character attacking = (Character) givingDmg;

                            double armorRating = defending.getBaseArmor();
                            double toHit = weapon.getToHit();
                            double dmgReduction = 0;

                            List<Armor> equippedArmor = new ArrayList<>();

                            for (Enum.ArmorLocation armorLocation : defending.getArmorInventory().keySet())
                            {
                                //Check if the armor location actually has something equipped
                                if (defending.getArmorInventory().get(armorLocation).getCount() > 0)
                                {
                                    //Add to the armorRating from the equipped armor
                                    Armor armor = (Armor) defending.getArmorInventory().get(armorLocation).getItem();
                                    armorRating += armor.getArmorClass();
                                    dmgReduction += armor.getDmgReduction();
                                }
                            }

                            //Determine the actual attackToHit rating based off of the attacking player stats and weapon type/stats
                            switch (weapon.getWeaponType())
                            {
                                case HEAVY: //Will only be heavy in this case
                                    toHit *= (attacking.getStrength() * .1) + 1;
                                    break;
                            }

                            //Loop based on the number of times attacking
                            for ( int n = 0; n < attackTimes; n++ )
                            {
                                //Determine if the attack hits or not include randomization
                                double randomizedHitMult = Math.random();
                                boolean crit = false;

                                //Check for randomized crit
                                if (randomizedHitMult > criticalHitPercent)
                                {
                                    //Skip straight to damage because it is a crit
                                    crit = true;
                                }
                                else
                                {
                                    //Check to see if the attack hits
                                    if (toHit * randomizedHitMult < armorRating)
                                    {
                                        return attacking.getDisplayName() + " failed to hit " + defending.getDisplayName() + " with a " + weapon.getDisplayName();
                                    }
                                }

                                //the attack hits, time to calculate the damage
                                //Randomized damage number is rolled
                                double randomzedDmgMult = Math.random();
                                double totalDamage;

                                if (crit)
                                {
                                    //Damage reduction is ignored and damage is doubled
                                    totalDamage = weapon.getDamage() * randomzedDmgMult * 2;
                                }
                                else
                                {
                                    //Make sure dmgReduction can't be more than configured amount
                                    if (dmgReduction > damageReductionMax)
                                    {
                                        dmgReduction = damageReductionMax;
                                    }

                                    //Damage reduction reduces the damage by a percent
                                    totalDamage = (weapon.getDamage() * randomzedDmgMult) * (1 - dmgReduction);
                                }

                                //Apply damage to defending entity
                                defending.setBaseHealth(defending.getBaseHealth() - totalDamage);

                                //Run the degrade action against the weapon used in the attack
                                log.info(weapon.degradeAction());

                                //Check for critical damage to modify return message
                                if (crit)
                                {
                                    returnMessage = defending.getDisplayName() + " took " + totalDamage + " critical damage from " + attacking.getDisplayName() + " using a " + weapon.getDisplayName();
                                }
                                else
                                {
                                    returnMessage = generateFormattedMsg(attacking, defending, weapon, totalDamage);
                                }
                                log.info(returnMessage);
                            }
                        }
                        else
                        {
                            returnMessage = givingDmg.getDisplayName() + " attempted to damage " + takingDmg.getDisplayName() + " using a worn out " + weapon.getDisplayName();
                            log.info(returnMessage);
                        }
                    }
                    else
                    {
                        log.error("Attempt to use a HeavyOnlyDamage component for an attacking entity that is not a character.");
                        returnMessage = "Error while assigning damage to " + takingDmg.getDisplayName();
                    }
                }
                else
                {
                    log.error("Attempt to use a HeavyOnlyDamage component for a takingDmg entity that is not a character.");
                    returnMessage = "Error while assigning damage to " + takingDmg.getDisplayName();
                }
            }
            else
            {
                log.info("Defending entity taking damage is immune to weapons that are not of type " + Enum.WeaponTypes.HEAVY);
                returnMessage = givingDmg.getDisplayName() + " attempted to attack " + takingDmg.getDisplayName() + " with a " + weapon.getDisplayName() + " but it seemed ineffective";
            }
        }
        catch (Exception ex)
        {
            log.error("Exception calculating/applying heavy only damage. [" + ex.getMessage() + "]");
            ex.printStackTrace();
            returnMessage = "Error while assigning damage to " + takingDmg.getDisplayName();
        }

        PlayerMessageOutput.addToPlayerMessageOutputBuffer(returnMessage);
        return returnMessage;
    }
}
