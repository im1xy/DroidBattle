package App;

import java.util.Scanner;

public interface UI {
    Scanner scanner = new Scanner(System.in);
    int confirmChoice();
}
