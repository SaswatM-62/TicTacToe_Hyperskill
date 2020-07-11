package tictactoe;
import java.util.Scanner;
public class Main {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        String a = "_________";
        char[][] h = new char[3][3];
        h = toArray(a);
        show(h);
        char move = 'X';
        while (Status(h).equals("Game not finished")) {
            makeMove(h, move);
            if (move == 'X') {
                move = 'O'; 
            } else {
                move = 'X';
            }
        }
        System.out.println(Status(h));
        sc.close();
    }
    static void makeMove(char[][] b, char p) {
        System.out.println("Enter the coordinates:");
        int xCoordinate = 0;
        int yCoordinate = 0;
        do {
            if (sc.hasNextInt()) {
                xCoordinate = sc.nextInt();
                if (sc.hasNextInt()) {
                    yCoordinate = sc.nextInt();
                } else {
                    System.out.println("You should enter numbers!");
                } 
            } else {
                System.out.println("You should enter numbers!");
            }
        } while (!validCoordinateCheck(xCoordinate, yCoordinate, b));
        yCoordinate--;
        xCoordinate--;
        if (yCoordinate == 0) {
            yCoordinate = 2;
        } else if (yCoordinate == 2) {
            yCoordinate = 0;
        }
        b[yCoordinate][xCoordinate] = p;
        show(b);
    }
    static boolean validCoordinateCheck(int m, int n, char[][] b) {
        if (m > 0 && m < 4 && n > 0 && n < 4) {
            m--;
            n--;
            if (n == 0) {
                n = 2;
            } else if (n == 2) {
                n = 0;
            }
            if (b[n][m] == '_') {
                return true;
            } else {
                System.out.println("This cell is occupied! Choose another one!");
            }
        } else {
            System.out.println("Coordinates should be from 1 to 3!");
        }
        return false;
    }
    static char[][] toArray(String a) {
        char[][] b = new char[3][3];
        int x = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                b[i][j] = a.charAt(x++);
            }
        }
        return b;
    }
    
    static void show(char[][] b) {
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                if(b[i][j] == '_') {
                    System.out.print("  ");
                    continue;
                }
                System.out.print(b[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }
    static String Status(char[][] b) {
        String stat = "";
        int xCount = 0;
        int oCount = 0;
        int _Count = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (b[i][j] == 'X') {
                    xCount++;
                } else if (b[i][j] == 'O') {
                    oCount++;
                } else {
                    _Count++;
                }
            }
        }
        boolean xRow = rowCheck(b, 'X');
        boolean xCol = colCheck(b, 'X');
        boolean xDiag = diagCheck(b, 'X');
        boolean oRow = rowCheck(b, 'O');
        boolean oCol = colCheck(b, 'O');
        boolean oDiag = diagCheck(b, 'O');
        if ((xRow == true && oRow == true) || (xCol == true && oCol == true) || (Math.abs(xCount - oCount) > 1)) {
           stat = "Impossible";
        } else if ( xRow == false && xDiag == false && xCol == false && oCol == false && oRow == false && oDiag == false && _Count == 0) {
            stat = "Draw";
        } else if ( xRow == false && xDiag == false && xCol == false && oCol == false && oRow == false && oDiag == false && _Count != 0) {
            stat = "Game not finished";
        } else if ((xRow == true || xCol == true || xDiag == true) && oCol == false && oRow == false && oDiag == false) {
            stat = "X wins";
        } else {
            stat = "O wins";
        }
        return stat;
    }
    static boolean rowCheck(char[][] b, char c) {
        boolean a = false;
        for (int i = 0; i < 3; i++) {
            if (b[i][1] == c && c == b[i][2] && b[i][0] == c) {
                a = true;
            }
        }
        return a;
    }
    static boolean colCheck(char[][] b, char c) {
        boolean a = false;
        for (int i = 0; i < 3; i++) {
            if (b[1][i] == c && c == b[2][i] && b[0][i] == c) {
                a = true;
            }
        }
        return a;
    }
    static boolean diagCheck(char[][] b, char c) {
        boolean a = false;
        if (b[0][0] == c && c == b[1][1] && b[2][2] == c) {
            a = true;
        } else if (b[2][0] == c && c == b[1][1] && b[0][2] == c) {
            a = true;
        }
        return a;
    }
}
