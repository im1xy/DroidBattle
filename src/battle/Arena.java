package battle;

import droids.*;
import App.UI;

import java.io.Serializable;
import java.util.Random;

public class Arena implements UI, Serializable {
    private int rounds;
    private Team firstTeam;
    private Team secondTeam;

    public Arena(Team firstTeam, Team secondTeam) {
        this.rounds = 0;
        this.firstTeam = firstTeam;
        this.secondTeam = secondTeam;
    }

    public void startArena() {
        Random random = new Random();

        if (random.nextInt(101) >= 50)
            this.swapTeams();

        System.out.println("Бій починає команда " + this.getFirstTeam().getName());
        System.out.println("Після КОЖНОГО раунду початкова команда змінюється на протилежну!");
        this.resumeArena();
    }

    public void resumeArena() {
        Droid firstDroid, secondDroid;

        while (this.getFirstTeam().isAlive() && this.getSecondTeam().isAlive()) {
            this.setRounds(this.getRounds() + 1);
            System.out.println("|--------------------РАУНД №" + this.getRounds() + "----------------------|");

            System.out.println("Команда " + this.getFirstTeam().getName() + " оберіть дроїда:");
            firstDroid = this.chooseDroid(this.getFirstTeam());
            System.out.println("Команда " + this.getSecondTeam().getName() + " оберіть дроїда:");
            secondDroid = this.chooseDroid(this.getSecondTeam());

            System.out.println("\t" + this.getFirstTeam().getName() + " Оберіть ДІЮ для " + firstDroid.getName());
            firstDroid.chooseDroidAction();
            System.out.println("\t" + this.getSecondTeam().getName() + " Оберіть дію для " + secondDroid.getName());
            secondDroid.chooseDroidAction();

            System.out.println("\t\t ### РЕЗУЛЬТАТИ РАУНДУ ###");
            System.out.println(getFirstTeam().getName());
            firstDroid.makeMove(secondDroid);
            System.out.println(getSecondTeam().getName());
            secondDroid.makeMove(firstDroid);

            firstDroid.setUsedAction(0);
            secondDroid.setUsedAction(0);

            swapTeams();

            if (this.pauseArena() == 1)
                return;
        }
        this.printBattleResults();
    }

    public int pauseArena() {
        int choice;
        System.out.println("ВИЙТИ У МЕНЮ (1 - Так, 2 - Ні): ");
        choice = this.scanner.nextInt();

        while (choice < 1 || choice > 2) {
            System.out.println("Помилка! Підтвердіть ваш Вибір: ");
            choice = this.scanner.nextInt();
        }

        return choice;
    }

    private void printBattleResults() {
        System.out.println("|--------------------РЕЗУЛЬТАТИ МАТЧУ--------------------|");
        if (!this.getFirstTeam().isAlive() && !this.getSecondTeam().isAlive())
            System.out.println("Результати бою: НІЧИЯ");
        else
        {
            System.out.print("\uD83C\uDFC6 Перемогла Команда ");
            if (this.getFirstTeam().isAlive())
                System.out.print(this.getFirstTeam().getName());
            else
                System.out.print(this.getSecondTeam().getName());
            System.out.print(" \uD83C\uDFC6 \n");
        }
        System.out.println("Кількість Раундів - " + this.getRounds());
    }

    private Droid chooseDroid(Team team) {
        int choice;
        Droid chosenDroid;

        System.out.println(team);
        while (true) {
            System.out.print("Ваш вибір (ID у команді): ");
            choice = this.scanner.nextInt();

            while(choice < 1 || choice > team.getMaxCapacity()) {
                System.out.println("Помилка! Оберіть ID дроїда з вашої команди");
                System.out.print("Ваш Вибір (ID у команді): ");
                choice = this.scanner.nextInt();
            }

            chosenDroid = team.getTeamList().get(choice - 1);
            if (chosenDroid.isAlive())
                return chosenDroid;

            System.out.println("ПОМИЛКА! Обраний Дроїд ПОМЕР, більше обрати його неможливо!");
        }
    }

    private void swapTeams() {
        Team temp = this.getSecondTeam();
        this.setSecondTeam(this.getFirstTeam());
        this.setFirstTeam(temp);
    }

    @Override
    public int confirmChoice() {
        int choice;

        System.out.println("Ви впевнені у виборі? (1 - Так, 2 - Ні): ");
        choice = this.scanner.nextInt();
        while(choice < 1 || choice > 2) {
            System.out.println("Помилка! Підтвердіть ваш Вибір: ");
            choice = this.scanner.nextInt();
        }

        System.out.println(" ");
        return choice;
    }

    // Getters and Setters
    public int getRounds() {
        return rounds;
    }

    public void setRounds(int rounds) {
        this.rounds = rounds;
    }

    public Team getFirstTeam() {
        return firstTeam;
    }

    public void setFirstTeam(Team firstTeam) {
        this.firstTeam = firstTeam;
    }

    public Team getSecondTeam() {
        return secondTeam;
    }

    public void setSecondTeam(Team secondTeam) {
        this.secondTeam = secondTeam;
    }
}
