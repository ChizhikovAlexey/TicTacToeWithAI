import java.util.Scanner;
import java.util.Random;
import java.util.Date;

import static java.lang.System.*;

final class Field {
    private char[][] field = new char[3][3];
    private int stepNum = 0;
    private Player.type playerX;
    private boolean isXStep;
    private Player.type playerO;
    private Scanner scanner = new Scanner(in);
    private Random random = new Random((new Date()).getTime());

    Field(Player.type p1, Player.type p2) {
        playerX = p1;
        playerO = p2;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = ' ';
            }
        }
        isXStep = true;
        game();
    }

    char[][] copyField (char[][] srcField) {
        char[][] result = new char[3][3];
        for (int i = 0; i < 3; i++) {
            arraycopy(srcField[i], 0, result[i], 0, 3);
        }
        return result;
    }

    private void game() {
        writeField();
        while (checkField().equals("Game not finished")) {
            nextStep();
            writeField();
            isXStep = !isXStep;
        }
        out.println(checkField());
    }

    void readField() {
        String input = scanner.next();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = input.charAt(i * 3 + j);
            }
        }
    }

    private String checkField() {
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

                    case ' ':

                        break;

                    default:
                        out.println("ERROR: wrong symbol!");
                        exit(1);
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

    private String checkField(char[][] curField) {
        int numberOfX = 0;
        int numberOfO = 0;
        boolean xWins = false;
        boolean oWins = false;
        String result;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                switch (curField[i][j]) {
                    case 'X':
                        numberOfX++;
                        if (!xWins) {
                            xWins = (curField[i][(j + 1) % 3] == 'X' && curField[i][(j + 2) % 3] == 'X') ||
                                    (curField[(i + 1) % 3][j] == 'X' && curField[(i + 2) % 3][j] == 'X') ||
                                    (i == j && curField[(i + 1) % 3][(j + 1) % 3] == 'X' && curField[(i + 2) % 3][(j + 2) % 3] == 'X') ||
                                    ((i + j) == 2 && curField[(i + 1) % 3][(j + 2) % 3] == 'X' && curField[(i + 2) % 3][(j + 1) % 3] == 'X');
                        }
                        break;

                    case 'O':
                        numberOfO++;
                        if (!oWins) {
                            oWins = (curField[i][(j + 1) % 3] == 'O' && curField[i][(j + 2) % 3] == 'O') ||
                                    (curField[(i + 1) % 3][j] == 'O' && curField[(i + 2) % 3][j] == 'O') ||
                                    (i == j && curField[(i + 1) % 3][(j + 1) % 3] == 'O' && curField[(i + 2) % 3][(j + 2) % 3] == 'O') ||
                                    ((i + j) == 2 && curField[(i + 1) % 3][(j + 2) % 3] == 'O' && curField[(i + 2) % 3][(j + 1) % 3] == 'O');
                        }
                        break;

                    case ' ':

                        break;

                    default:
                        out.println("ERROR: wrong symbol!");
                        exit(1);
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

    private void writeField() {
        out.println("---------");
        for (int i = 0; i < 3; i++) {
            out.print("| ");
            for (int j = 0; j < 3; j++) {
                out.print(field[i][j] + " ");
            }
            out.println("|");
        }
        out.println("---------");
    }

    private void writeField(char[][] curField) {
        out.println("---------");
        for (int i = 0; i < 3; i++) {
            out.print("| ");
            for (int j = 0; j < 3; j++) {
                out.print(curField[i][j] + " ");
            }
            out.println("|");
        }
        out.println("---------");
    }

    private void humanStep() {
        while (!scanner.hasNextInt()) {
            out.println("You should enter numbers!");
            scanner.next();
        }
        int y = scanner.nextInt() - 1;
        int x = (3 - scanner.nextInt());

        if (x > 2 || x < 0 || y > 2 || y < 0) {
            out.println("Coordinates should be from 1 to 3!");
            humanStep();
        } else if (field[x][y] != ' ') {
            out.println("This cell is occupied! Choose another one!");
            humanStep();
        } else {
            field[x][y] = stepNum % 2 == 0 ? 'X' : 'O';
        }
    }

    private void easyAIStep() {
        final int destCell = random.nextInt(9 - stepNum);
        int cellCounter = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (field[i][j] == ' ') {
                    if (cellCounter++ == destCell) {
                        field[i][j] = stepNum % 2 == 0 ? 'X' : 'O';
                    }
                }
            }
        }
    }

    private void mediumAIStep() {
        char currentSym = stepNum % 2 == 0 ? 'X' : 'O';
        boolean stepIsNotMade = true;
        for (int i = 0; i < 3 && stepIsNotMade; i++) {
            for (int j = i; j < 3 && stepIsNotMade; j++) {
                if (field[i][j] == currentSym) {
                    stepIsNotMade = false;
                    if (field[i][(j + 1) % 3] == currentSym && field[i][(j + 2) % 3] == ' ') {
                        field[i][(j + 2) % 3] = currentSym;
                    } else if (field[(i + 1) % 3][j] == currentSym && field[(i + 2) % 3][j] == ' ') {
                        field[(i + 2) % 3][j] = currentSym;
                    } else if (i == j && field[(i + 1) % 3][(j + 1) % 3] == currentSym && field[(i + 2) % 3][(j + 2) % 3] == ' ') {
                        field[(i + 2) % 3][(j + 2) % 3] = currentSym;
                    } else {
                        stepIsNotMade = true;
                    }
                }
            }
        }


        if (stepIsNotMade) {
            char anotherSym = stepNum % 2 == 0 ? 'O' : 'X';
            for (int i = 0; i < 3 && stepIsNotMade; i++) {
                for (int j = i; j < 3 && stepIsNotMade; j++) {
                    if (field[i][j] == anotherSym) {
                        stepIsNotMade = false;
                        if (field[i][(j + 1) % 3] == anotherSym && field[i][(j + 2) % 3] == ' ') {
                            field[i][(j + 2) % 3] = currentSym;
                        } else if (field[(i + 1) % 3][j] == anotherSym && field[(i + 2) % 3][j] == ' ') {
                            field[(i + 2) % 3][j] = currentSym;
                        } else if (i == j && field[(i + 1) % 3][(j + 1) % 3] == anotherSym && field[(i + 2) % 3][(j + 2) % 3] == ' ') {
                            field[(i + 2) % 3][(j + 2) % 3] = currentSym;
                        } else {
                            stepIsNotMade = true;
                        }
                    }
                }
            }
        }

        if (stepIsNotMade) {
            easyAIStep();
        }
    }

    private void hardAIStep() {
        char currentSym = stepNum % 2 == 0 ? 'X' : 'O';

        int maxScore = Integer.MIN_VALUE;
        int bestX = 1;
        int bestY = 1;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (field[i][j] == ' '){
                    char[][] tmpField = copyField(field);
                    tmpField[i][j] = currentSym;
                    int tmpScore = minimax(tmpField, currentSym == 'X' ? 'O' : 'X');
                    if (tmpScore > maxScore) {
                        maxScore = tmpScore;
                        bestX = i;
                        bestY = j;
                    }
                }
            }
        }

        field[bestX][bestY] = currentSym;
    }

    private int minimax(char[][] curField, char symbol) {
        int score;
        switch (checkField(curField)) {
            case "Game not finished":
                break;

            case "X wins":
                return isXStep ? 1 : -1;

            case "O wins":
                return isXStep ? -1 : 1;

            default:
                return 0;
        }

        if (symbol == 'X' && isXStep || symbol == 'O' && !isXStep) {
            int maxScore = Integer.MIN_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (curField[i][j] == ' ') {
                        char[][] tmpField = copyField(curField);
                        tmpField[i][j] = symbol;
                        int tmpScore = minimax(tmpField, symbol == 'X' ? 'O' : 'X');
                        if (tmpScore > maxScore) {
                            maxScore = tmpScore;
                        }
                    }
                }
            }
            score = maxScore;
        } else {
            int minScore = Integer.MAX_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (curField[i][j] == ' ') {
                        char[][] tmpField = copyField(curField);
                        tmpField[i][j] = symbol;
                        int tmpScore = minimax(tmpField, symbol == 'X' ? 'O' : 'X');
                        if (tmpScore < minScore) {
                            minScore = tmpScore;
                        }
                    }
                }
            }
            score = minScore;
        }

        return score;
    }

    private void nextStep() {
        switch (stepNum % 2 == 0 ? playerX : playerO) {
            case easy:
                out.println("Making move level \"easy\"");
                easyAIStep();
                break;
            case user:
                out.print("Enter the coordinates: ");
                humanStep();
                break;
            case medium:
                out.println("Making move level \"medium\"");
                mediumAIStep();
                break;
            case hard:
                out.println("Making move level \"hard\"");
                hardAIStep();
                break;
            default:
                break;
        }
        stepNum++;
    }
}