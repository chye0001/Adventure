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

            String firstInput = "";
            String secondInput = "";
            String[] commands = input.split("\\s+");
            if (commands.length == 1){
                firstInput = commands[0];
            }else if (commands.length == 2) {
                firstInput = commands[0];
                secondInput = commands[1];
            }

            switch (firstInput.toLowerCase()) {
                case "north" -> controller.moveAround(input);
                case "south" -> controller.moveAround(input);
                case "east" -> controller.moveAround(input);
                case "west" -> controller.moveAround(input);
                case "teleport" -> controller.teleport();
                case "inventory" -> controller.showInventory();
                case "take" -> controller.takeItemToInventory(secondInput);
                case "drop" -> controller.dropItemFromInventory(secondInput);
                case "help" -> System.out.println("If you want to move North, write: \"North\"\n" +
                        "If you want to move East, then write \"East\"\n" +
                        "If you want to move South, then write \"South\"\n" +
                        "If you want to move West, then write \"West\"\n" +
                        "If you want to pick up an item, then write \"take *item*)\"\n" +
                        "If you want to drop an item, then write \"drop *item*)\"\n" +
                        "If you want to check what is in you inventory, then write \"drop *item*)\"\n" +
                        "If you want to know what room you're in write: \"look\"\n" +
                        "If you wish to end the game write: \"exit\"\n");
                case "look" -> System.out.println(controller.showItemsInRoom());
                case "exit" -> System.out.println("thank you for playing ");
                default -> System.out.println("Not a known command!. Write help for instructions");
            }
        } while (!input.equalsIgnoreCase("exit"));
    }
}