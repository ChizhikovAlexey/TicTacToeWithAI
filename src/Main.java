import java.util.Scanner;

public class Main {
    private static void readCommand() {
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();
        Scanner commandScanner = new Scanner(command);
        switch(commandScanner.next()){
            case "start":
                start(commandScanner.hasNext() ? Player.typeFromString(commandScanner.next()) : null,
                        commandScanner.hasNext() ? Player.typeFromString(commandScanner.next()) : null);
                break;
            case "exit":
                System.exit(0);
            default:
                System.out.println("Bad parameters!");
                break;
        }
    }

    private static void start(Player.type playerX, Player.type playerO) {
        if (playerX != null && playerO != null) {
            Field field = new Field(playerX, playerO);
        } else {
            System.out.println("Bad parameters!");
        }
    }

    public static void main(String[] args) {
        while (true){
            System.out.print("Input command: ");
            readCommand();
        }
    }
}