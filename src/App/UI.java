package battle;

import java.util.Scanner;

interface UI {
    Scanner scanner = new Scanner(System.in);
    int confirmChoice();

}
