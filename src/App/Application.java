import battle.ArenaTeamVsTeam;
import battle.Team;

import java.util.Scanner;

public class Application {
    Scanner scanner = new Scanner(System.in);
    public Application() {}
    public void start() {
        int choice = 0;

        choice = this.startingScreen();
        if (choice == 1) this.newGameScreen();

    }
    public int startingScreen() {
        System.out.print(
                """
                \t\t+--------------+
                \t\t| 1. НОВА ГРА  |
                \t\t| 2. ЗАГРУЗИТИ |
                \t\t| 3. ВИЙТИ     |
                \t\t+--------------+
                """);

        int choice = 0;
        System.out.print("\t\tВИБІР: ");
        choice = this.scanner.nextInt();
        while (choice < 1 || choice > 3){
            System.out.println("\t\tПомилка! Invalid Option.");
            System.out.print("\t\tВИБІР: ");
            choice = this.scanner.nextInt();
        }

        return choice;
    }

    public void newGameScreen() {
        System.out.print(
                """
                
                +--------------------------НОВА ГРА--------------------------+
                |-------------------СТВОРІТЬ СВОЇ КОМАНДИ--------------------|
                """);

        System.out.println(
                """
                |                   Команда Першого Гравця                   |
                """);
        Team firstTeam = new Team(2);
        System.out.println(
                """
                
                |                   Команда Другого Гравця                   |
                """);
        Team secondTeam = new Team(2);
        ArenaTeamVsTeam arena = new ArenaTeamVsTeam(firstTeam, secondTeam);

        System.out.println(
                """
                
                |------------------------ПОЧАТОК БОЮ-------------------------|
                """);
        arena.startArena();
    }
}
