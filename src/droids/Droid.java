package droids;
import java.util.Random;

abstract class BasicDroidInfo implements DroidInterface{
    // Characteristics
    protected int hp;
    protected int armor;
    protected int energy;
    protected int damage;
    protected int criticalHit;
    protected int criticalHitChance;
    protected int avoidSaveHp;
    protected int avoidChance;

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getCriticalHit() {
        return criticalHit;
    }

    public void setCriticalHit(int criticalHit) {
        this.criticalHit = criticalHit;
    }

    public int getCriticalHitChance() {
        return criticalHitChance;
    }

    public void setCriticalHitChance(int criticalHitChance) {
        this.criticalHitChance = criticalHitChance;
    }

    public int getAvoidSaveHp() {
        return avoidSaveHp;
    }

    public void setAvoidSaveHp(int avoidSaveHp) {
        this.avoidSaveHp = avoidSaveHp;
    }

    public int getAvoidChance() {
        return avoidChance;
    }

    public void setAvoidChance(int avoidChance) {
        this.avoidChance = avoidChance;
    }
}
