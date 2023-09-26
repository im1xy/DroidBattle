package App;

import battle.Arena;
import battle.Team;

import java.io.*;

public class Application implements UI{
    private Arena arena = null;
    public Application() {}
    public void start() {
        while (true) {
            int choice = this.startingScreen();
            if (choice == 1) this.newGameScreen();
            else if (choice == 2) this.resumeGame();
            else if (choice == 3) this.saveGame();
            else if (choice == 4) this.loadGame();
            else
                break;
        }
    }
    private int startingScreen() {
        System.out.println(getMenuScreen());

        int choice;
        System.out.print("\t\tВИБІР: ");
        choice = this.scanner.nextInt();
        while (choice < 1 || choice > 5){
            System.out.println("\t\tПомилка! Invalid Option.");
            System.out.print("\t\tВИБІР: ");
            choice = this.scanner.nextInt();
        }

        return choice;
    }
    private String getMenuScreen() {
        String menuScreen = "";
        if (this.getArena() != null)
            menuScreen +=
                    """
                    \t\t+----------------+
                    \t\t|   # ПАУЗА #    |
                    """;
        menuScreen +=
                """
                \t\t+----------------+
                \t\t| 1. НОВА ГРА    |
                \t\t| 2. ПРОДОВЖИТИ  |
                \t\t| 3. ЗБЕРЕГТИ    |
                \t\t| 4. ЗАГРУЗИТИ   |
                \t\t| 5. ВИЙТИ       |
                \t\t+----------------+
                """;
        return menuScreen;
    }

    private void newGameScreen() {
        System.out.print(
                """
                
                +--------------------------НОВА ГРА--------------------------+
                """);
        int capacity = this.chooseTeamCapacity();
        this.createTeams(capacity);
        System.out.println(
                """
                
                |------------------------ПОЧАТОК БОЮ-------------------------|
                """);
        this.getArena().startArena();
    }

    private int chooseTeamCapacity() {
        System.out.println(
                """
                |-----------ОБЕРІТЬ КІЛЬКІСТЬ ДРОЇДІВ У КОМАНДАХ-------------|
                """);
        System.out.print("Кількість: ");
        return this.scanner.nextInt();
    }

    private void createTeams(int maxCapacity) {
        System.out.println(
                """
                |-------------------СТВОРІТЬ СВОЇ КОМАНДИ--------------------|
                |                   Команда Першого Гравця                   |
                """);
        Team firstTeam = new Team(maxCapacity);
        System.out.println(
                """
                
                |                   Команда Другого Гравця                   |
                """);
        Team secondTeam = new Team(maxCapacity);
        this.setArena(new Arena(firstTeam, secondTeam));
    }
    
    private void resumeGame() {
        if (this.getArena() == null) {
            System.out.println("ПОМИЛКА! Гра не створена.");
            return;
        }
        
        this.getArena().resumeArena();
    }

    private void saveGame() {
        if (this.getArena() == null) {
            System.out.println("ПОМИЛКА! Гра не створена.");
            return;
        }

        System.out.println(
                """
                |------------------------ЗБЕРЕГТИ ГРУ------------------------|
                """);
        System.out.print("Повний шлях куди зберегти файл: ");
        String filePath = this.scanner.next();

        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filePath));
            outputStream.writeObject(this.getArena());
            outputStream.close();
            System.out.println("Файл збережено успішно!");

        } catch (IOException e) {
            System.out.println("ПОМИЛКА! Не вдалося створити save file.");
            e.fillInStackTrace();
        }
    }

    private void loadGame() {
        System.out.println(
                """
                |------------------------ЗАВАНТАЖИТИ ГРУ----------------------|
                """);
        System.out.print("Повний шлях до файлу: ");
        String filePath = this.scanner.next();

        try {
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filePath));
            this.setArena((Arena) inputStream.readObject());
            inputStream.close();

            this.resumeGame();

        } catch (IOException e) {
            System.out.println("ПОМИЛКА! Не вдалося знайти save file.");
            e.fillInStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("ПОМИЛКА! Не вдалося прочитати save file.");
            e.fillInStackTrace();
        }
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

    public Arena getArena() {
        return arena;
    }

    public void setArena(Arena arena) {
        this.arena = arena;
    }
}
