import java.util.Scanner;

public class UserInterface {

    Scanner scanner = new Scanner(System.in);
    String input;

    AdventureController controller = new AdventureController();
    public UserInterface(AdventureController controller) {

    }

    public void startGame() {

        controller.buildMap();
        controller.setCurrentRoom();
        controller.setLastTeleport();
        System.out.println("\nWelcome to the adventure game!");
        System.out.println("Write help for a list of instructions\n");

        do {
            System.out.print("You are in the following room: ");
            System.out.println(controller.getCurrentRoom());

            System.out.println("\nWhere do you wish to go?");
            input = scanner.nextLine();

            switch (input.toLowerCase()) {
                case "go north" -> controller.moveAround(input);
                case "go south" -> controller.moveAround(input);
                case "go east" -> controller.moveAround(input);
                case "go west" -> controller.moveAround(input);
                case "teleport" -> controller.teleport();
                case "help" -> System.out.println("If you want to move North, write: \"go north\"\n" +
                        "If you want to move East, then write \"go East\"\n" +
                        "If you want to move South, then write \"go south\"\n" +
                        "If you want to move West, then write \"go west\"\n" +
                        "If you want to know what room you're in write: \"look around\"\n" +
                        "If you wish to end the game write: \"exit\"\n");
                case "look around" -> System.out.println(controller.getCurrentRoom().getDescription());
                case "exit" -> System.out.println("thank you for playing ");
                default -> System.out.println("Not a known command!. Write help for instructions");
            }
        } while (!input.equalsIgnoreCase("exit"));
    }
}