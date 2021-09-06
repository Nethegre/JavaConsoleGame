package base;

public class Ranged extends Weapon {

    protected int range = 0;
    protected String attackVerb = "shoot";

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public String getAttackVerb() {
        return attackVerb;
    }

    public void setAttackVerb(String attackVerb) {
        this.attackVerb = attackVerb;
    }

}
