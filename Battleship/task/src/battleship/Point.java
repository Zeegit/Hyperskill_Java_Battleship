package battleship;

public class Point {
    int x;
    int y;
    String[] letters = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Point(String c) {
        String sx = c.substring(0, 1);
        String sy = c.substring(1, c.length());
        x = -1;
        for (int i = 0; i < letters.length; i++) {
            if (sx.equals(letters[i])) {
                x = i;
                break;
            }
        }
        y = Integer.parseInt(sy) - 1;
        if (y >= 10) {
            y = -1;
        }
    }
}
