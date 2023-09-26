package battle;
import droids.*;

import java.util.Random;

public class ArenaTeamVsTeam implements UI{
    int rounds;
    Team firstTeam;
    Team secondTeam;

    public ArenaTeamVsTeam(Team firstTeam, Team secondTeam) {
        this.rounds = 0;
        this.firstTeam = firstTeam;
        this.secondTeam = secondTeam;
    }

    public void startArena() {
        Random random = new Random();

        Droid firstDroid = null;
        int firstDroidAction = 0;
        Droid secondDroid = null;
        int secondDroidAction = 0;

        if (random.nextInt(101) >= 50)
            this.swapTeams();

        System.out.println("Бій починає команда " + this.getFirstTeam().getName());
        System.out.println("Після КОЖНОГО раунду початкова команда змінюється на протилежну!");

        while (this.firstTeam.isAlive() && this.secondTeam.isAlive()) {
            this.setRounds(this.getRounds() + 1);
            System.out.println("|--------------------РАУНД №" + this.getRounds() + "----------------------|");

            System.out.println("Команда " + this.getFirstTeam().getName() + " оберіть дроїда:");
            firstDroid = this.chooseDroid(this.getFirstTeam());
            System.out.println("Команда " + this.getSecondTeam().getName() + " оберіть дроїда:");
            secondDroid = this.chooseDroid(this.getSecondTeam());

            System.out.println("\t" + this.getFirstTeam().getName() + " Оберіть ДІЮ для " + firstDroid.getName());
            firstDroidAction = this.droidAction(firstDroid);
            System.out.println("\t" + this.getSecondTeam().getName() + " Оберіть дію для " + secondDroid.getName());
            secondDroidAction = this.droidAction(secondDroid);

            System.out.println("\t\t ### РЕЗУЛЬТАТИ РАУНДУ ###");
            startHalfRound(firstDroid, firstDroidAction, secondDroid, secondDroidAction);
            startHalfRound(secondDroid, secondDroidAction, firstDroid, firstDroidAction);

            swapTeams();
        }


    }

    public void startHalfRound(Droid firstDroid, int firstDroidAction, Droid secondDroid, int secondDroidAction) {
        if (firstDroidAction == 1) {
            if (secondDroidAction != 2)
            {
                firstDroid.attack(secondDroid);
                System.out.println(firstDroid.getName() + " наніс урон " + secondDroid.getName());
            }
            else
                System.out.println(firstDroid.getName() + " не наніс урону " + secondDroid.getName());
        }
        else
        {
            firstDroid.block();
            System.out.println(firstDroid.getName() + " поставив БЛОК");
        }
        System.out.println("Залишок здоров'я " + secondDroid.getName() + " = " + secondDroid.getHp());
    }

    public void printBattleResults() {
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
        }
        System.out.print(" \uD83C\uDFC6");
        System.out.println("Кількість Раундів - " + this.getRounds());
    }

    public int droidAction(Droid droid) {
        int choice = 0;

        while (choice < 1 || choice > 2) {
            System.out.println(
                            """
                            1 - Атака
                            2 - Блок
                            """
            );
            System.out.print("Ваш Вибір: ");
            choice = this.scanner.nextInt();

            if (choice == 2 && !droid.isBlockPossible()) {
                System.out.println("Поставити блок не можливо. У Дроїда " + droid.getName() + " недостатньо енергії.");
                choice = 0;
            }
        }

        return choice;
    }

    public Droid chooseDroid(Team team) {
        int choice = 0;
        Droid chosenDroid = null;

        while (true) {
            System.out.println(team);
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
            else
                System.out.println("ПОМИЛКА! Обраний Дроїд ПОМЕР, більше обрати його неможливо!");
        }
    }

    public void swapTeams() {
        Team temp = this.getSecondTeam();
        this.setSecondTeam(this.getFirstTeam());
        this.setFirstTeam(temp);
    }

    @Override
    public int confirmChoice() {
        int choice = 0;

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
