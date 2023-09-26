package droids;
import java.util.Random;

public class Knight extends Droid {
    public Knight() {
        this.setClassName("Knight");
        this.setName("Geralt");
        this.setHp(1000);
        this.setEnergy(200);
        this.setEnergyOnBlock(40);
        this.setDamage(200);
        this.setAvoidSaveHp(100);
        this.setAvoidChance(10);
        this.setSkillType("Increase Damage");
    }

    @Override
    public void attack(Droid otherDroid) {
        super.attack(otherDroid);
    }

    @Override
    public void useSkill() {
        int additionalDamage = new Random().nextInt(201);
        this.setDamage(this.getDamage() + additionalDamage);
        System.out.println("Урон " + this.getName() + " було збільшено на " + additionalDamage);
        System.out.println("Новий показник урону " + this.getName() + ": " + this.getDamage());
    }

    @Override
    public void receiveDamage(int damage) {
        super.receiveDamage(damage);
    }
}
