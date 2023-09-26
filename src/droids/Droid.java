package droids;

import java.io.Serializable;
import java.util.Random;
import java.util.Scanner;

abstract public class Droid implements Serializable {
    // Characteristics
    protected String name;
    protected String className;
    protected int hp;
    protected int energy;
    protected int energyOnBlock;
    protected int damage;
    protected int avoidSaveHp;
    protected int avoidChance;
    protected String skillType;
    protected int energyOnSkill;

    // Current Action Status
    protected int usedAction;

    // Skills
    public void attack(Droid otherDroid) {
        otherDroid.receiveDamage(this.getDamage());
        System.out.println(this.getName() + " наніс урон опоненту " + otherDroid.getName());
    }

    public void block() {
        this.setEnergy(this.getEnergy() - this.getEnergyOnBlock());
        System.out.println(this.getName() + " поставив БЛОК");
    }

    public void useSkill() {}

    public void chooseDroidAction() {
        int choice = 0;
        while (choice < 1 || choice > 3) {
            System.out.println(this.availableActions());
            System.out.print("Ваш Вибір: ");
            choice = new Scanner(System.in).nextInt();

            if (choice == 2 && !this.isBlockPossible()) {
                System.out.println("Поставити блок не можливо. У Дроїда " + this.getName() + " недостатньо енергії.");
                choice = 0;
            }
            else if (choice == 3 && !this.isSkillPossible()) {
                System.out.println("Використати Вміння не можливо. У Дроїда " + this.getName() + " недостатньо енергії.");
                choice = 0;
            }
        }

        this.setUsedAction(choice);
    }

    public void makeMove(Droid enemyDroid) {
        if (this.getUsedAction() == 1) {
            if (enemyDroid.getUsedAction() == 2)
                System.out.println(this.getName() + " не наніс урону " + enemyDroid.getName());
            else
                this.attack(enemyDroid);
            System.out.println("Залишок здоров'я опонента " + enemyDroid.getName() + " = " + enemyDroid.getHp());
        }
        else if (this.getUsedAction() == 2)
            this.block();
        else
            this.useSkill();
    }

    // Status
    public void receiveDamage(int damage) {
        if (new Random().nextInt(101) <= this.getAvoidChance()) {
            damage = damage - this.getAvoidSaveHp();
            System.out.println("☘️ Опонент " + this.getName() + " зміг ухилитися, тому отримав менше урону!");
        }

        int hpRemains = this.getHp() - damage;
        if (hpRemains < 0)
            hpRemains = 0;

        this.setHp(hpRemains);
    }

    public boolean isBlockPossible() {
        return this.getEnergy() > this.getEnergyOnBlock();
    }

    public boolean isSkillPossible() {
        return this.getEnergy() > this.getEnergyOnSkill();
    }

    public boolean isAlive() {
        return getHp() > 0;
    }

    // Information
    public String availableActions() {
        return """
                1 - Атака
                2 - Блок
                3 - Вміння
               """;
    }

    private String formatTableLine(int amount) {
        String line = "";
        for (int i = 0; i < amount; i++)
            line += "-";
        return line;
    }

    public String basicInfo() {
        String strInfo = formatTableLine(10 * 5 + 20);
        strInfo += "\n%-10s%-10s%-10s%-10s%-10s%-20s".formatted("Class", "Name", "❤️Health", "⚡Energy", "⚔️Damage", "Skill Type");
        strInfo += "\n%-10s%-10s%-10s%-10s%-10s%-20s\n".formatted(className, name, hp, energy, damage, skillType);
        strInfo += formatTableLine(10 * 5 + 20);
        return strInfo;
    }

    @Override
    public String toString() {
        String strInfo = formatTableLine(10 * 5 + 20 + 20 + 30 + 15);
        strInfo += "\n%-10s%-10s%-10s%-10s%-20s%-10s%-20s%-30s%-15s".formatted(
                "Class", "Name", "❤️Health", "⚡Energy", "\uD83D\uDEE1️Energy On Block",
                "⚔️Damage", "Skill Type", "\uD83C\uDF43Health Saved when Avoided", "☘️Avoid Chance");
        strInfo += "\n%-10s%-10s%-10s%-10s%-20s%-10s%-20s%-30s%-15s\n".formatted(
                className, name, hp, energy, energyOnBlock, damage, skillType, avoidSaveHp, avoidChance);
        strInfo += formatTableLine(10 * 5 + 20 + 20 + 30 + 15);

        return strInfo;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }
    public int getEnergyOnBlock() {
        return energyOnBlock;
    }

    public void setEnergyOnBlock(int energyOnBlock) {
        this.energyOnBlock = energyOnBlock;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
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

    public String getSkillType() {
        return skillType;
    }

    public void setSkillType(String skillType) {
        this.skillType = skillType;
    }

    public int getEnergyOnSkill() {
        return energyOnSkill;
    }

    public void setEnergyOnSkill(int energyOnSkill) {
        this.energyOnSkill = energyOnSkill;
    }

    public int getUsedAction() {
        return usedAction;
    }

    public void setUsedAction(int usedAction) {
        this.usedAction = usedAction;
    }
}
