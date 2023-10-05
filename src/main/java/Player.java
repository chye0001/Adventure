import java.net.http.WebSocket;
import java.util.ArrayList;

public class Player {

    private Room currentRoom;
    private Room lastTeleport;
    private final ArrayList<Item> inventory = new ArrayList<>();
    private int health = 50;
    private Weapon equippedWeapon;
    private ReturnMessage checkWeapon;
    //private Item foodToEat;

    public Player() {
    }

    public void teleport() {
        Room tempCurrentRoom = currentRoom;
        currentRoom = lastTeleport;

        lastTeleport = tempCurrentRoom;
    }


    public void moveAround(String direction) {

        if (direction.equalsIgnoreCase("north") || direction.equalsIgnoreCase("w")) {
            if (currentRoom.getNorth() == null) {
                System.out.println("You can not go that way");
            } else
                currentRoom = currentRoom.getNorth();

        } else if (direction.equalsIgnoreCase("south") || direction.equalsIgnoreCase("s")) {
            if (currentRoom.getSouth() == null) {
                System.out.println("You can not go that way");
            } else
                currentRoom = currentRoom.getSouth();

        } else if (direction.equalsIgnoreCase("east") || direction.equalsIgnoreCase("d")) {
            if (currentRoom.getEast() == null) {
                System.out.println("You can not go that way");
            } else
                currentRoom = currentRoom.getEast();

        } else if (direction.equalsIgnoreCase("west") || direction.equalsIgnoreCase("a")) {
            if (currentRoom.getWest() == null) {
                System.out.println("You can not go that way");
            } else
                currentRoom = currentRoom.getWest();
        }
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void getMoveAround(String input) {
        moveAround(input);
    }

    public void setCurrentRoom(Room setRoom) {
        this.currentRoom = setRoom;
    }

    public int getHealth() {
        return health;
    }

    public void setLastTeleport(Room setLastTeleport) {
        this.lastTeleport = setLastTeleport;
    }

    public void takeItem(String takeItem) {
        Item itemToTake = currentRoom.removeItemFromRoom(takeItem);
        if (itemToTake != null) {
            inventory.add(itemToTake);
            System.out.println("\nYou picked up " + itemToTake);
        }
    }

    public void dropItem(String dropItem) {
        Item dropItemInRoom = null;
        for (Item item : inventory) {
            if (item.getITEM_NAME().toLowerCase().contains(dropItem.toLowerCase())) {
                dropItemInRoom = item;
                currentRoom.dropItemInRoom(item);
            } else
                System.out.println("I can not find " + dropItem + " in the inventory...");
        }
        inventory.remove(dropItemInRoom);
        if (inventory.isEmpty()) {
            System.out.println("\nThere is nothing in the bag, so I can't drop " + dropItem + "\n");
        }
    }

    public ReturnMessage eatFood(String eatFood) {
        if (inventory.isEmpty()) {
            return ReturnMessage.CANT_FIND;
        } else {
            for (Item item : inventory) {
                if (item.getITEM_NAME().toLowerCase().contains(eatFood.toLowerCase())) {
                    if (item instanceof Food food) {
                        health += food.getHealthPoints();
                        inventory.remove(food);
                        return ReturnMessage.EDIBLE;
                    } else
                        return ReturnMessage.NOT_EDIBLE;
                }
            }
        }
        return ReturnMessage.CANT_FIND;
    }

    // Udvidelse: Flere typer af "consumables"
    public ReturnMessage drinkLiquid(String drinkLiquid) {
        if (inventory.isEmpty()) {
            return ReturnMessage.CANT_FIND;
        } else {
            for (Item item : inventory) {
                if (item.getITEM_NAME().toLowerCase().contains(drinkLiquid.toLowerCase())) {
                    if (item instanceof Liquid) {
                        Liquid liquid = (Liquid) item;
                        health += liquid.getHealthPoints();
                        inventory.remove(liquid);
                        return ReturnMessage.EDIBLE;
                    } else
                        return ReturnMessage.NOT_DRINKABLE;
                }
            }
        }
        return ReturnMessage.CANT_FIND;
    }

    // UDVIDELSE - Klogere håndtering af giftig mad (i brugerfladen)
    public ReturnMessage tryToEatFood(String eatFood) {
        if (inventory.isEmpty()) {
            return ReturnMessage.CANT_FIND;
        } else {
            for (Item item : inventory) {
                if (item.getITEM_NAME().toLowerCase().contains(eatFood.toLowerCase())) {
                    if (item instanceof Food food) {
                        if (food.getHealthPoints() < 0) {
                            return ReturnMessage.REALLY_EAT;
                        } else {
                            health += food.getHealthPoints();
                            inventory.remove(food);
                            return ReturnMessage.EDIBLE;
                        }
                    } else
                        return ReturnMessage.NOT_EDIBLE;
                }
            }
        }
        return ReturnMessage.CANT_FIND;
    }


    // UDVIDELSE - Klogere håndtering af giftig mad (i brugerfladen)
    public ReturnMessage tryTodrinkLiquid(String drinkLiquid) {
        if (inventory.isEmpty()) {
            return ReturnMessage.CANT_FIND;
        } else {
            for (Item item : inventory) {
                if (item.getITEM_NAME().toLowerCase().contains(drinkLiquid.toLowerCase())) {
                    if (item instanceof Liquid) {
                        Liquid liquid = (Liquid) item;
                        if (liquid.healthPoints < 0) {
                            return ReturnMessage.REALLY_DRINK;
                        } else {
                            health += liquid.getHealthPoints();
                            inventory.remove(liquid);
                            return ReturnMessage.EDIBLE;
                        }
                    } else
                        return ReturnMessage.NOT_DRINKABLE;
                }
            }
        }
        return ReturnMessage.CANT_FIND;
    }

    public ArrayList<Item> showInventory() {
        return inventory;
    }

    public ArrayList<Item> showItemsInRoom() {
        return currentRoom.showItemsInRoom();
    }

    /*
    public ReturnMessage attack() {
        if (checkWeapon == ReturnMessage.IS_EQUIPPED) {
            if (equippedWeapon.equals(ReturnMessage.NO_AMMO)) {
                return ReturnMessage.NO_AMMO;

            } else {
                equippedWeapon.attack();
            return ReturnMessage.MELEE_ATTACK;
            }
        } else
            return ReturnMessage.IS_NOT_EQUIPPED;
    }

     */

    public ReturnMessage attack() {
        if (checkWeapon == ReturnMessage.IS_EQUIPPED) {
            if (equippedWeapon.getRemainingUsages() > 0) {
                equippedWeapon.getDamage(); // Skal udskiftes når der skal tilføjes fjender
                equippedWeapon.setRemainingUsages(equippedWeapon.getRemainingUsages() - 1);
                return ReturnMessage.ATTACK;
            } else if (equippedWeapon.getRemainingUsages() < 0) {
                return ReturnMessage.NO_AMMO;
            }
        }
        return ReturnMessage.IS_NOT_EQUIPPED;
    }

    public int getDamageDone() {
        return equippedWeapon.getDamage();
    }

    public ReturnMessage equipWeapon(String itemName) {
        for (Item item : inventory) {
            if (item.getITEM_NAME().toLowerCase().contains(itemName.toLowerCase())) {
                if (item instanceof Weapon) {
                    equippedWeapon = (Weapon) item;
                    checkWeapon = ReturnMessage.IS_EQUIPPED;
                    return ReturnMessage.IS_EQUIPPED;
                } else
                    return ReturnMessage.IS_NOT_A_WEAPON;
            }
        }
        return ReturnMessage.CANT_FIND;
    }

    public Weapon getEquippedWeapon() {
        return equippedWeapon;
    }

}