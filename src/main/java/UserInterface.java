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
            System.out.println("I see the following enemies: " + controller.getListOfEnemies() + "\n");

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
                case "inventory" -> System.out.println("\nIn my bag I see: \n" + controller.showInventory() + "\n" +
                        "Equipped weapon 1: " + controller.getEquippedWeapon1() + "\n" +
                        "Equipped weapon 2: " + controller.getEquippedWeapon2() + "\n");
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
                    controller.getPlayerHealth();
                    if (controller.getPlayerHealth() <= 100 && controller.getPlayerHealth() > 50) {
                        System.out.println("Health: " + controller.getPlayerHealth() + " - you are in good health, but avoid fighting right now.\n");
                    } else if (controller.getPlayerHealth() <= 50 && controller.getPlayerHealth() > 35) {
                        System.out.println("Health: " + controller.getPlayerHealth() + " - you are not in the best of shape. You should eat some food and rest\n");
                    } else if (controller.getPlayerHealth() <= 35 && controller.getPlayerHealth() > 20) {
                        System.out.println("Health: " + controller.getPlayerHealth() + " - you are in poor condition, consider eating some food and rest up\n");
                    } else if (controller.getPlayerHealth() <= 20 && controller.getPlayerHealth() > 0) {
                        System.out.println("Health: " + controller.getPlayerHealth() + " - you are in extremely poor condition, eat tons of food and take a long rest\n");
                    }
                }
                case "equip1" -> {
                    ReturnMessage isEquipped = controller.equipWeapon1(secondInput);
                    if (isEquipped == ReturnMessage.WEAPON_EQUIPPED) {
                        System.out.println("\nYou have equipped " + controller.getEquippedWeapon1() + "\n");
                    }
                    if (isEquipped == ReturnMessage.IS_NOT_A_WEAPON) {
                        System.out.println("\nYou can not equip " + controller.getPickedUpItem() + " because it's not a weapon\n");
                    }
                    if (isEquipped == ReturnMessage.CANT_FIND) {
                        System.out.println("\nNo weapon was found with the name " + secondInput + " in the inventory\n");
                    }
                    if (isEquipped == ReturnMessage.WEAPON_SLOT_UNAVAILABLE) {
                        System.out.println("You must unequip your weapon first, before equipping " + controller.getAttemptEquipWeapon1());
                    }
                    if (isEquipped == ReturnMessage.WEAPON_ALREADY_EQUIPPED) {
                        System.out.println("\nYou have already equipped this weapon, in weapon slot 2\n");
                    }
                }
                case "equip2" -> {
                    //Udvidelse: Dueal wielding & Shields
                    ReturnMessage isEquipped = controller.equipWeapon2(secondInput);
                    if (isEquipped == ReturnMessage.WEAPON_EQUIPPED) {
                        System.out.println("\nYou have equipped " + controller.getEquippedWeapon2() + "\n");
                    }
                    if (isEquipped == ReturnMessage.IS_NOT_A_WEAPON) {
                        System.out.println("\nYou can not equip " + controller.getPickedUpItem() + " because it's not a weapon\n");
                    }
                    if (isEquipped == ReturnMessage.CANT_FIND) {
                        System.out.println("\nNo weapon was found with the name" + secondInput + "in the inventory\n");
                    }
                    if (isEquipped == ReturnMessage.WEAPON_SLOT_UNAVAILABLE) {
                        System.out.println("You must unequip your weapon first, before equipping " + controller.getAttemptEquipWeapon2());
                    }
                    if (isEquipped == ReturnMessage.WEAPON_ALREADY_EQUIPPED) {
                        System.out.println("\nYou have already equipped this weapon, in weapon slot 1\n");
                    }
                }
                case "unequip" -> {
                    //Udvidelse: Dueal wielding & Shields
                    boolean checkEquip = controller.unequip(secondInput);
                    if (checkEquip) {
                        controller.unequip(secondInput);
                        System.out.println("\nYou have unequipped " + controller.getUnequippedWeapon() + "\n");
                    } else
                        System.out.println("\nYou dont have anything to unequip...\n");
                }
                case "attack", "use" -> {
                    ReturnMessage checkStatusOfAttack = controller.attack(secondInput);

                    //Attack sekvens når den tomme luft angribes.
                    if (checkStatusOfAttack == ReturnMessage.DUAL_WIELDING_ATTACK) {   //Udvidelse: dual wielding
                        System.out.println("Dual wielding...!\nDamage dealt: " + controller.getDualWieldingDamage());

                    } else if (checkStatusOfAttack == ReturnMessage.WEAPON1_NO_AMMO_USE_WEAPON_2) {  //Udvidelse: dual wielding
                        System.out.println(controller.getEquippedWeapon1() + " has no more ammo..\n" +
                                "Therefore I use my " + controller.getAttemptEquipWeapon2() + "\n" +
                                "Damage dealt: " + controller.getDamageDone());

                    } else if (checkStatusOfAttack == ReturnMessage.WEAPON2_NO_AMMO_USE_WEAPON_1) {  //Udvidelse: dual wielding
                        System.out.println(controller.getEquippedWeapon2() + " has no more ammo..\n" +
                                "Therefore I use my " + controller.getAttemptEquipWeapon1() + "\n" +
                                "Damage dealt: " + controller.getDamageDone());

                    } else if (checkStatusOfAttack == ReturnMessage.ATTACK1) {
                        System.out.println("I use my " + controller.getEquippedWeapon1());
                        System.out.println("Damage dealt: " + controller.getDamageDone() + "\n");

                    } else if (checkStatusOfAttack == ReturnMessage.ATTACK2) {
                        System.out.println("I use my " + controller.getEquippedWeapon2());
                        System.out.println("Damage dealt: " + controller.getDamageDone() + "\n");

                    } else if (checkStatusOfAttack == ReturnMessage.NO_AMMO) {
                        System.out.println("You dont have any ammo left...\n");

                    } else if (checkStatusOfAttack == ReturnMessage.WEAPON_NOT_EQUIPPED) {
                        System.out.println("You have not equipped a weapon...");

                        //Attack sekvens når der angribes en enemy
                    } else if (checkStatusOfAttack == ReturnMessage.ENEMY_KILLED) {
                        System.out.println("You killed " + controller.getEnemyKilled());

                    } else if (checkStatusOfAttack == ReturnMessage.BATTLE_ONGOING_DUAL_WIELDING_ATTACK) {
                        System.out.println("Enemy health: " + controller.getEnemyHealth() + "\n" +
                                           "Your health: " + controller.getPlayerHealth());

                    } else if (checkStatusOfAttack == ReturnMessage.BATTLE_ONGOING_USE_WEAPON1_WEAPON2_NO_AMMO) {
                        System.out.println("Enemy health: " + controller.getEnemyHealth() + "\n" +
                                "Your health: " + controller.getPlayerHealth());

                    } else if (checkStatusOfAttack == ReturnMessage.BATTLE_ONGOING_USE_WEAPON2_WEAPON1_NO_AMMO) {
                        System.out.println("Enemy health: " + controller.getEnemyHealth() + "\n" +
                                "Your health: " + controller.getPlayerHealth());

                    } else if (checkStatusOfAttack == ReturnMessage.BATTLE_ONGOING_USE_WEAPON1_WEAPON2_NOT_EQUIPPED) {
                        System.out.println("Enemy health: " + controller.getEnemyHealth() + "\n" +
                                "Your health: " + controller.getPlayerHealth());

                    } else if (checkStatusOfAttack == ReturnMessage.BATTLE_ONGOING_USE_WEAPON2_WEAPON1_NOT_EQUIPPED) {
                        System.out.println("Enemy health: " + controller.getEnemyHealth() + "\n" +
                                "Your health: " + controller.getPlayerHealth());

                    } else if (checkStatusOfAttack == ReturnMessage.PLAYER_DEAD) {
                        System.out.println("You died, game over...\nDo you wish to reset the game? [yes/no]");
                        input = scanner.nextLine();
                        if (input.equalsIgnoreCase("yes")) {
                            controller.resetInventoryOnPlayerDeath();
                            controller.buildMap();
                        } else if (input.equalsIgnoreCase("no")) {
                            System.out.println("Thanks for playing");
                            input = "exit";
                        }

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
        }
        while (!input.equalsIgnoreCase("exit"));
    }
}
