package battleship;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    private static Scanner scanner;

    public static void main(String[] args) {
        // Write your code here
        scanner = new Scanner(System.in);

        Player player1 = new Player(10, "Player 1");
        Player player2 = new Player(10, "Player 2");

        System.out.println("Player 1, place your ships on the game field");
        player1.init();
        player1.print();

        player1.initShips();
        promptEnterKey();

        System.out.println("Player 2, place your ships on the game field");
        player2.init();
        player2.print();

        player2.initShips();
        promptEnterKey();

        Player shooter = player1;
        Player goal  = player2;

        while (true) {
            goal.printFrog();
            System.out.printf("---------------------\n");
            shooter.print();

            System.out.printf("%s, it's your turn:\n", shooter.getName());
            Point p = getShoot();

            goal.receiveShoot(p);
            if (goal.checkFill())
                break;

            promptEnterKey();

            Player tmp = shooter;
            shooter = goal;
            goal = tmp;
        }
    }

    private static void swap(Player shooter, Player goal) {

    }

    private static Point getShoot() {
        Point c;
        while (true) {
            c = new Point(scanner.next());
            if (c.x == -1 || c.y == -1) {
                System.out.printf("Error! You entered the wrong coordinates! Try again:\n");
                continue;
            }
            break;
        }
        return c;
    }

    public static void promptEnterKey() {
        System.out.println("Press Enter and pass the move to another player");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
