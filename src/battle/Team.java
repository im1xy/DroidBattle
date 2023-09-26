package battle;

import droids.*;
import App.UI;

import java.io.Serializable;
import java.util.ArrayList;

public class Team implements UI, Serializable {
    private String name;
    private int maxCapacity;
    private ArrayList<Droid> TeamList;

    public Team(int maxCapacity) {
        this.setMaxCapacity(maxCapacity);
        this.setName(this.createTeamName());
        this.setTeamList(this.createTeam());
    }

    private ArrayList<Droid> createTeam() {
        ArrayList<Droid> team = new ArrayList<>();
        Droid droid;
        int i = 1;

        System.out.println("\t# Створіть команду " + this.getName() + " #");
        while (team.size() < this.getMaxCapacity()) {
            System.out.println(i + ". Дроїд");
            droid = this.chooseClass();
            if (droid == null)
                continue;

            team.add(droid);
            i++;
        }

        return team;
    }

    private Droid chooseClass() {
        int choice;
        Droid chosenDroid;

        System.out.println(
                """
                Виберіть Клас:
                1 - Лицар
                2 - Маг
                3 - Лучник
                """
        );
        System.out.print("Ваш Вибір: ");
        choice = this.scanner.nextInt();

        while(choice < 1 || choice > 3) {
            System.out.println("Помилка! Оберіть ID класу із запропонованого списку");
            System.out.print("Ваш Вибір: ");
            choice = this.scanner.nextInt();
        }

        if (choice == 1) chosenDroid = new Knight();
        else if (choice == 2) chosenDroid = new Mage();
        else chosenDroid = new Archer();

        System.out.println("\tХарактеристики цього Дроїду");
        System.out.println(chosenDroid);

        if (confirmChoice() == 1) {
            return chosenDroid;
        }

        return null;
    }

    private String createTeamName() {
        System.out.print("Назвіть свою команду: ");
        return this.scanner.next();
    }

    public boolean isAlive() {
        for (Droid droid: this.getTeamList()) {
            if (droid.isAlive())
                return true;
        }
        return false;
    }

    @Override
    public String toString() {
        String strTeamList = "";
        int N = 1;

        for (Droid member: this.getTeamList()) {
            strTeamList += "№" + N + " ДРОЇД\n" + member.basicInfo() + "\n";
            N++;
        }

        return strTeamList;
    }

    @Override
    public int confirmChoice() {
        int choice = 0;

        System.out.print("Ви впевнені у виборі? (1 - Так, 2 - Ні): ");
        choice = this.scanner.nextInt();
        while(choice < 1 || choice > 2) {
            System.out.print("Помилка! Підтвердіть ваш Вибір: ");
            choice = this.scanner.nextInt();
        }
        System.out.println(" ");

        return choice;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = "[" + name + "]";
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public ArrayList<Droid> getTeamList() {
        return TeamList;
    }

    public void setTeamList(ArrayList<Droid> teamList) {
        TeamList = teamList;
    }
}
