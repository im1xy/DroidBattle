package droids;
import java.util.Random;

public class Archer extends Droid{
    public Archer() {
        this.setClassName("Archer");
        this.setName("Lutick");
        this.setHp(750);
        this.setEnergy(200);
        this.setEnergyOnBlock(50);
        this.setDamage(250);
        this.setAvoidSaveHp(200);
        this.setAvoidChance(25);
        this.setSkillType("Recover Energy");
    }

    @Override
    public void attack(Droid otherDroid) {
        super.attack(otherDroid);
    }

    @Override
    public void useSkill() {
        int additionalEnergy = new Random().nextInt(101);
        this.setEnergy(this.getEnergy() + additionalEnergy);
        System.out.println("Енергія " + this.getName() + " була збільшена на " + additionalEnergy);
        System.out.println("Новий показник енергії " + this.getName() + ": " + this.getEnergy());
    }

    @Override
    public void receiveDamage(int damage) {
        super.receiveDamage(damage);
    }
}
