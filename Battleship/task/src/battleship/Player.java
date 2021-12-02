package battleship;

import java.io.IOException;
import java.util.Scanner;

public class Player {
    int gameSize;
    String[][] map;
    String[] letters = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
    Scanner scanner;
    private String name;

    public Player(int gameSize, String name) {
        this.gameSize = gameSize;
        this.name = name;
        map = new String[this.gameSize][this.gameSize];
        scanner = new Scanner(System.in);
    }



    public void initShips() {
        for (Ship shipx : Ship.values()) {
            initShip(shipx);
            print();
        }
    }

    public void init() {
        for (int i = 0; i < gameSize; i++) {
            for (int j = 0; j < gameSize; j++) {
                map[i][j] = "~";
            }
        }
    }

    public void print() {
        System.out.print("  ");
        for (int i = 0; i < gameSize; i++) {
            System.out.printf("%s ", i + 1);
        }
        System.out.println();

        for (int i = 0; i < gameSize; i++) {
            System.out.print(letters[i]);
            System.out.print(" ");
            for (int j = 0; j < 10; j++) {
                System.out.print(map[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    public void printFrog() {
        System.out.print("  ");
        for (int i = 0; i < gameSize; i++) {
            System.out.printf("%s ", i + 1);
        }
        System.out.println();

        for (int i = 0; i < gameSize; i++) {
            System.out.print(letters[i]);
            System.out.print(" ");
            for (int j = 0; j < 10; j++) {
                if (!map[i][j].equals("O")) {
                    System.out.print(map[i][j]);
                } else {
                    System.out.print("~");
                }
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    public void initShip(Ship ship) {
        System.out.printf("\nEnter the coordinates of the %s (%d cells):\n\n", ship.name, ship.size);
        while (true) {
            Point c1 = new Point(scanner.next());
            Point c2 = new Point(scanner.next());

            if (c1.x == c2.x) {
                int x = c1.x;
                int min = Math.min(c1.y, c2.y);
                int max = Math.max(c1.y, c2.y);
                int size = max - min + 1;

                if (size != ship.size) {
                    System.out.printf("Error! Wrong length of the %s! Try again:\n", ship.name);
                    continue;
                }

                boolean er = false;
                for (int i = Math.max(min - 1, 0); i <= Math.min(max + 1, gameSize - 1); i++) {
                    for (int j = Math.max(x - 1, 0); j <= Math.min(x + 1, gameSize - 1); j++) {
                        if (map[j][i].equals("O")) {
                            if (!er) {
                                System.out.printf("Error! You placed it too close to another one. Try again:\n");
                                er = true;
                            }
                        }
                    }
                }
                if (er) {
                    continue;
                }

                for (int i = min; i <= max; i++) {
                    map[x][i] = "O";
                }
                break;
            } else if (c1.y == c2.y) {
                int y = c1.y;
                int min = Math.min(c1.x, c2.x);
                int max = Math.max(c1.x, c2.x);
                int size = max - min + 1;

                if (size != ship.size) {
                    System.out.printf("Error! Wrong length of the %s! Try again:\n", ship.name);
                    continue;
                }

                boolean er = false;
                for (int i = Math.max(min - 1, 0); i <= Math.min(max + 1, gameSize - 1); i++) {
                    for (int j = Math.max(y - 1, 0); j <= Math.min(y + 1, gameSize - 1); j++) {
                        if (map[i][j].equals("O")) {
                            if (!er) {
                                System.out.printf("Error! You placed it too close to another one. Try again:\n");
                                er = true;
                            }
                        }
                    }
                }
                if (er) {
                    continue;
                }

                for (int i = min; i <= max; i++) {
                    map[i][y] = "O";
                }
                break;
            } else {
                System.out.printf("Error! Wrong ship location! Try again:\n");
                continue;
            }
        }

    }

    public void start() {
        System.out.printf("The game starts!\n");
        printFrog();
        System.out.printf("Take a shot!\n");
        while (true) {
            Point c = new Point(scanner.next());
            if (c.x == -1 || c.y == -1) {
                System.out.printf("Error! You entered the wrong coordinates! Try again:\n");
                continue;
            }
            if (map[c.x][c.y].equals("O") || map[c.x][c.y].equals("X")) {
                map[c.x][c.y] = "X";
                printFrog();
                //print();
                if (checkKill(c)) {
                    System.out.printf("You sank a ship! Specify a new target:\n");
                } else {
                    System.out.printf("You hit a ship!\n");
                }
                //break;
            } else if (map[c.x][c.y].equals("~")) {
                map[c.x][c.y] = "M";
                printFrog();
                //print();
                System.out.printf("You missed!\n");
                //break;
            }

            if (checkFill()) {
                System.out.printf("You sank the last ship. You won. Congratulations!\n");
                break;
            }
        }
        print();
    }



    private boolean checkKill(Point c) {
        /* (map[c.x + 1][c.y].equals("O") || map[c.x][c.y - 1].equals("O") || map[c.x][c.y + 1].equals("O"))) {*/
        /*else if (map[c.x][c.y].equals("X")) {
            return checkKill(new Point(c.x-1, c.y-1)) && checkKill(new Point(c.x-1, c.y)) &&  checkKill(new Point(c.x-1, c.y+1)) &&
                    checkKill(new Point(c.x, c.y-1)) && checkKill(new Point(c.x, c.y+1)) &
                    checkKill(new Point(c.x+1, c.y-1)) && checkKill(new Point(c.x+1, c.y)) &&  checkKill(new Point(c.x+1, c.y+1));
        } else if (map[c.x][c.y].equals("M") || map[c.x][c.y].equals("~")) {
            return true;
        } */
        if (c.x == -1 || c.y == -1 || c.x >= 10 || c.y >= 10) {
            return true;
        } else return (c.x - 1 < 0 || !map[c.x - 1][c.y].equals("O")) &&
                (c.x + 1 >= 10 || !map[c.x + 1][c.y].equals("O")) &&
                (c.y - 1 < 0 || !map[c.x][c.y - 1].equals("O")) &&
                (c.y + 1 >= 10 || !map[c.x][c.y + 1].equals("O"));
    }

    public boolean checkFill() {
        for (int i = 0; i < gameSize; i++) {
            for (int j = 0; j < gameSize; j++) {
                if (map[i][j].equals("O")) {
                    return false;
                }
            }
        }
        return true;
    }

    public String getName() {
        return this.name;
    }

    public void receiveShoot(Point c) {
        if (map[c.x][c.y].equals("O") || map[c.x][c.y].equals("X")) {
            map[c.x][c.y] = "X";
            printFrog();
            //print();
            if (checkKill(c)) {
                System.out.printf("You sank a ship! Specify a new target:\n");
            } else {
                System.out.printf("You hit a ship!\n");
            }
            //break;
        } else if (map[c.x][c.y].equals("~")) {
            map[c.x][c.y] = "M";
            printFrog();
            //print();
            System.out.printf("You missed!\n");
            //break;
        }

        if (checkFill()) {
            System.out.printf("You sank the last ship. You won. Congratulations!\n");
        }
    }
}

