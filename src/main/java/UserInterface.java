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
            if (commands.length == 1) {
                firstInput = commands[0];
            } else if (commands.length == 2) {
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
                case "eat" -> {
                    ReturnMessage returnValueOfFood = controller.tryToEat(secondInput);

                    // UDVIDELSE - Klogere håndtering af giftig mad (i brugerfladen)
                    String answer = "";
                    if (returnValueOfFood == ReturnMessage.REALLY_EAT) {
                        System.out.println("That does not look to healthy do you really want to eat it? [yes/no]");
                        while (!scanner.hasNextLine()) {
                            System.out.println("You cant enter anything but characters");
                            scanner.nextLine();
                        }
                        answer = scanner.nextLine();

                    }
                    if (answer.equalsIgnoreCase("yes")) {
                        controller.eat(secondInput);
                        System.out.println("*eating " + secondInput + "*");
                    } else if (returnValueOfFood == ReturnMessage.REALLY_EAT)
                        System.out.println("okay i wont eat it anyway...");


                    if (returnValueOfFood == ReturnMessage.EDIBLE) {
                        System.out.println("*eating " + secondInput + "*");
                    } else if (returnValueOfFood == ReturnMessage.CANT_FIND) {
                        System.out.println("I can't find " + secondInput + " in the inventory");
                    } else if (returnValueOfFood == ReturnMessage.NOT_EDIBLE) {
                        System.out.println(secondInput + " is not edible...\n");
                    }
                }
                case "drink" -> {
                    ReturnMessage returnValueOfLiquid = controller.tryToDrink(secondInput);

                    // UDVIDELSE - Klogere håndtering af giftig mad (i brugerfladen)
                    String answer = "";
                    if (returnValueOfLiquid == ReturnMessage.REALLY_DRINK) {
                        System.out.println("That does not look to healthy do you really want to drink that? [yes/no]");
                        while (!scanner.hasNextLine()) {
                            System.out.println("You cant enter anything but characters");
                            scanner.nextLine();
                        }
                        answer = scanner.nextLine();
                    }
                    if (answer.equalsIgnoreCase("yes")) {
                        controller.drink(secondInput);
                        System.out.println("*drinking " + secondInput + "*");
                    } else if (returnValueOfLiquid == ReturnMessage.REALLY_DRINK)
                        System.out.println("okay i won't drink it anyway...");


                    if (returnValueOfLiquid == ReturnMessage.EDIBLE) {
                        System.out.println("*drinking " + secondInput + "*");
                    } else if (returnValueOfLiquid == ReturnMessage.CANT_FIND) {
                        System.out.println("I can't find " + secondInput + " in the inventory");
                    } else if (returnValueOfLiquid == ReturnMessage.NOT_DRINKABLE) {
                        System.out.println(secondInput + " is not drinkable...\n");
                    }
                }
                case "health" -> {
                    controller.getHealth();
                    if (controller.getHealth() <= 100 && controller.getHealth() > 50) {
                        System.out.println("Health: " + controller.getHealth() + " - you are in good health, but avoid fighting right now.\n");
                    } else if (controller.getHealth() <= 50 && controller.getHealth() > 35) {
                        System.out.println("Health: " + controller.getHealth() + " - you are not in the best of shape. You should eat some food and rest\n");
                    } else if (controller.getHealth() <= 35 && controller.getHealth() > 20) {
                        System.out.println("Health: " + controller.getHealth() + " - you are in poor condition, consider eating some food and rest up\n");
                    } else if (controller.getHealth() <= 20 && controller.getHealth() > 0) {
                        System.out.println("Health: " + controller.getHealth() + " - you are in extremely poor condition, eat tons of food and take a long rest\n");
                    }
                }
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
                case "look" ->
                        System.out.println("It seems like this room contains: \n" + controller.showItemsInRoom());
                case "exit" -> System.out.println("thank you for playing ");
                default -> System.out.println("Not a known command!. Write help for instructions");
            }
        } while (!input.equalsIgnoreCase("exit"));
    }
}