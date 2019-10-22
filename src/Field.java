import java.util.Scanner;
import java.util.Random;
import java.util.Date;

final class Field {
    private char[][] field = new char[3][3];
    private int stepNum = 0;
    private Player playerX;
    private Player playerO;
    private Scanner scanner = new Scanner(System.in);
    private Random random = new Random((new Date()).getTime());

    Field(Player p1, Player p2) {
        playerX = p1;
        playerO = p2;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = '_';
            }
        }
    }

    void readField() {
        String input = scanner.next();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = input.charAt(i * 3 + j);
            }
        }
    }

    String checkField() {
        int numberOfX = 0;
        int numberOfO = 0;
        boolean xWins = false;
        boolean oWins = false;
        String result;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                switch (field[i][j]) {
                    case 'X':
                        numberOfX++;
                        if (!xWins) {
                            xWins = (field[i][(j + 1) % 3] == 'X' && field[i][(j + 2) % 3] == 'X') ||
                                    (field[(i + 1) % 3][j] == 'X' && field[(i + 2) % 3][j] == 'X') ||
                                    (i == j && field[(i + 1) % 3][(j + 1) % 3] == 'X' && field[(i + 2) % 3][(j + 2) % 3] == 'X') ||
                                    ((i + j) == 2 && field[(i + 1) % 3][(j + 2) % 3] == 'X' && field[(i + 2) % 3][(j + 1) % 3] == 'X');
                        }
                        break;

                    case 'O':
                        numberOfO++;
                        if (!oWins) {
                            oWins = (field[i][(j + 1) % 3] == 'O' && field[i][(j + 2) % 3] == 'O') ||
                                    (field[(i + 1) % 3][j] == 'O' && field[(i + 2) % 3][j] == 'O') ||
                                    (i == j && field[(i + 1) % 3][(j + 1) % 3] == 'O' && field[(i + 2) % 3][(j + 2) % 3] == 'O') ||
                                    ((i + j) == 2 && field[(i + 1) % 3][(j + 2) % 3] == 'O' && field[(i + 2) % 3][(j + 1) % 3] == 'O');
                        }
                        break;

                    case '_':

                        break;

                    default:
                        System.out.println("ERROR: wrong symbol!");
                        System.exit(1);
                        break;
                }
            }
        }

        if ((xWins && oWins) || Math.abs(numberOfO - numberOfX) > 1) {
            result = "Impossible";
        } else if (xWins) {
            result = "X wins";
        } else if (oWins) {
            result = "O wins";
        } else if (numberOfO + numberOfX == 9) {
            result = "Draw";
        } else {
            result = "Game not finished";
        }

        return result;
    }

    void writeField() {
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(field[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }

    private void humanStep() {
        while (!scanner.hasNextInt()) {
            System.out.println("You should enter numbers!");
            scanner.next();
        }
        int y = scanner.nextInt() - 1;
        int x = (3 - scanner.nextInt());

        if (x > 2 || x < 0 || y > 2 || y < 0) {
            System.out.println("Coordinates should be from 1 to 3!");
            humanStep();
        } else if (field[x][y] != '_') {
            System.out.println("This cell is occupied! Choose another one!");
            humanStep();
        } else {
            field[x][y] = stepNum % 2 == 0 ? 'X' : 'O';
        }
    }

    private void easyAIStep() {
        final int destCell = random.nextInt(9 - stepNum);
        int cellCounter = 0;
        for (int i = 0; i < 3; i ++) {
            for (int j = 0; j < 3; j++) {
                if (field[i][j] == '_') {
                    if (cellCounter++ == destCell) {
                        field[i][j] = stepNum % 2 == 0 ? 'X' : 'O';
                    }
                }
            }
        }
    }

    void nextStep() {
        switch (stepNum % 2 == 0 ? playerX : playerO) {
            case easyAI:
                System.out.println("Making move level \"easy\"");
                easyAIStep();
                break;
            case human:
                System.out.print("Enter the coordinates: ");
                humanStep();
                break;
            default:
                break;
        }
        stepNum++;
    }
}