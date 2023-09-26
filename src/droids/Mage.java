package droids;
import java.util.Random;

public class Mage extends Droid{
    public Mage() {
        this.setClassName("Mage");
        this.setName("Triss");
        this.setHp(600);
        this.setEnergy(270);
        this.setEnergyOnBlock(90);
        this.setDamage(300);
        this.setAvoidSaveHp(150);
        this.setAvoidChance(15);
        this.setSkillType("Heal");
    }

    @Override
    public void attack(Droid otherDroid) {
        super.attack(otherDroid);
    }

    @Override
    public void useSkill() {
        int additionalHp = new Random().nextInt(301);
        this.setHp(this.getHp() + additionalHp);
        System.out.println("Здоров'я " + this.getName() + " було збільшено на " + additionalHp);
        System.out.println("Новий показник здоров'я " + this.getName() + ": " + this.getHp());
    }

    @Override
    public void receiveDamage(int damage) {
        super.receiveDamage(damage);
    }
}
