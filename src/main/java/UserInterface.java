import java.util.Scanner;

public class UserInterface {

    Scanner scanner = new Scanner(System.in);
    String input;

    AdventureController controller;
    public UserInterface(AdventureController controller) {
        this.controller = controller;
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
            System.out.println("In this room I see the following items:\n" + controller.showItemsInRoom());

            System.out.println("\nWhat do you wish to do?");
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
                case "north", "w" -> controller.moveAround(input);
                case "south", "s" -> controller.moveAround(input);
                case "east", "d" -> controller.moveAround(input);
                case "west", "a" -> controller.moveAround(input);
                case "teleport" -> controller.teleport();
                case "inventory" -> System.out.println("\nIn my bag I see: \n" + controller.showInventory() + "\n");
                case "take" -> controller.takeItemToInventory(secondInput);
                case "drop" -> controller.dropItemFromInventory(secondInput);
                case "help" -> System.out.println("""
                        If you want to move North, write: "North"
                        If you want to move East, then write "East"
                        If you want to move South, then write "South"
                        If you want to move West, then write "West"
                        If you want to pick up an item, then write "take *item*"
                        If you want to drop an item, then write "drop *item*"
                        If you want to check what is in you inventory, then write "inventory"
                        If you want to know what room you're in write: "look"
                        If you wish to end the game write: "exit"
                        """);
                case "look" -> System.out.println("It seems like this room contains: \n" + controller.showItemsInRoom());
                case "exit" -> System.out.println("thank you for playing ");
                default -> System.out.println("Not a known command!. Write help for instructions");
            }
        } while (!input.equalsIgnoreCase("exit"));
    }
}