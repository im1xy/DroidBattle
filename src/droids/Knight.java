package droids;

public class Knight extends Droid {
    SwordsMan(String name) {
        this.setClassName("SwordsMan");
        this.setName(name);
        this.setHp(7);
        this.setArmor(6);
        this.setEnergy(200);
        this.setDamage(0);
        this.setCriticalHit(5);
        this.setCriticalHitChance(20);
        this.setAvoidChance(10);
    }

    @Override
    public void attack(Droid otherDroid) {
        super.attack(otherDroid);
    }

    @Override
    public void receiveDamage(int damage) {
        super.receiveDamage(damage);
    }
}
