public class Main {
    public static void main(String[] args) {
        Field field = new Field(Player.easyAI, Player.easyAI);
        field.writeField();

        while (field.checkField().equals("Game not finished")) {
            field.nextStep();
            field.writeField();
        }

        System.out.println(field.checkField());
    }
}