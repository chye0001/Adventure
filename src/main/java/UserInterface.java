import java.util.Scanner;

public class UserInterface {

    Adventure adventure = new Adventure();
    Scanner scanner = new Scanner(System.in);
    String input;

    public UserInterface() {
    }

    public void startGame() {


            adventure.buildMap();

            System.out.println("\nWelcome to the adventure game!");
            System.out.println("Write help for a list of instructions\n");

        do {
            System.out.print("You are in the following room: ");
            System.out.println(adventure.getCurrentRoom());

            System.out.println("\nWhere do you wish to go?");
            input = scanner.nextLine();

            switch (input.toLowerCase()) {
                case "go north" -> adventure.moveAround(input);
                case "go south" -> adventure.moveAround(input);
                case "go east" -> adventure.moveAround(input);
                case "go west" -> adventure.moveAround(input);
                case "help" -> System.out.println("If you want to move North, write: \"go north\"\n" +
                        "If you want to move East, then write \"go East\"\n" +
                        "If you want to move South, then write \"go south\"" +
                        "If you want to move West, then write \"go west\"" +
                        "If you want to know what room you're in write: \"look around\"\n" +
                        "If you wish to end the game write: \"exit\"");
                case "look around" -> System.out.println(adventure.getCurrentRoom().getDescription());
                case "exit" -> System.out.println("thank you for playing ");
                default -> System.out.println("Not a known command!. Write help for instructions");
            }
        } while (!input.equalsIgnoreCase("exit"));
    }
}