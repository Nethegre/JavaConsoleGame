package component;

import base.Armor;
import base.Character;
import base.Entity;
import base.Weapon;
import util.ConfigManager;
import util.Enum;

import java.util.ArrayList;
import java.util.List;

//This damage component will account for equipped armor
public class CharacterDamage extends Damage {

    protected double criticalHitPercent;
    protected double damageReductionMax;

    public CharacterDamage() {

        ConfigManager config = new ConfigManager();
        this.criticalHitPercent = Double.parseDouble(config.getConfigValue("CRITICALHITPERCENT"));
        this.damageReductionMax = Double.parseDouble(config.getConfigValue("DMGREDUCTIONMAX"));
    }

    @Override
    public void damage(Entity givingDmg, Entity takingDmg, int attackTimes, Weapon weapon)
    {
        try
        {
            //Check if the entity is a Character, if not throw an exception
            if (takingDmg.getClass().equals(Character.class))
            {
                if (givingDmg.getClass().equals(Character.class))
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
                        case HEAVY:
                            toHit *= (attacking.getStrength() * .1) + 1;
                            break;
                        case LIGHT:
                            toHit *= (attacking.getDexterity() * .1) + 1;
                            break;
                        case MENTAL:
                            toHit *= (attacking.getWisdom() * .1) + 1;
                            break;
                        case AVERAGE:
                            toHit *= (attacking.getDexterity() * .05) + (attacking.getStrength() * .05) + 1;
                    }

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
                            return; //Attack doesn't hit, therefore return
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

                    defending.setBaseHealth(defending.getBaseHealth() - totalDamage);
                    log.info("Defending character took " + totalDamage + " damage from attacking character.");
                }
                else
                {
                    log.error("Attempt to use a CharacterDamage component for an attacking entity that is not a character.");
                }
            }
            else
            {
                log.error("Attempt to use a CharacterDamage component for a takingDmg entity that is not a character.");
            }
        }
        catch (Exception ex)
        {
            log.error("Exception calculating/applying character damage. [" + ex.getMessage() + "]");
            ex.printStackTrace();
        }
    }
}
