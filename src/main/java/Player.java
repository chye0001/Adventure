import java.util.ArrayList;

public class Player {

    private Room currentRoom;
    private Room lastTeleport;
    private final ArrayList<Item> inventory = new ArrayList<>();
    private int health = 50;

    public Player(){}

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

    public void setCurrentRoom(Room setRoom){
        this.currentRoom = setRoom;
    }
    public int getHealth(){
        return health;
    }

    public void setLastTeleport(Room setLastTeleport){
        this.lastTeleport = setLastTeleport;
    }

    public void takeItem(String takeItem) {
        Item itemToTake = currentRoom.removeItemFromRoom(takeItem);
        if (itemToTake != null) {
            inventory.add(itemToTake);
            System.out.println("\nYou picked up " + itemToTake);
        }
    }

    public void dropItem (String dropItem) {
        Item dropItemInRoom = null;
        for (Item item : inventory) {
            if (item.getItemName().toLowerCase().contains(dropItem.toLowerCase())) {
                dropItemInRoom = item;
                currentRoom.dropItemInRoom(item);
            }else
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
                if (item.getItemName().toLowerCase().contains(eatFood.toLowerCase())) {
                    if (item instanceof Food food) {
                        health += food.getHealthPoints();
                        inventory.remove(food);
                        return ReturnMessage.OK;
                    } else
                        return ReturnMessage.NOT_EATABLE;
                } else
                    return ReturnMessage.CANT_FIND;
            }
        }return ReturnMessage.CANT_FIND;
    }

    public ReturnMessage drinkLiquid(String drinkLiquid) {
        if (inventory.isEmpty()) {
            return ReturnMessage.CANT_FIND;
        } else {
            for (Item item : inventory) {
                if (item.getItemName().toLowerCase().contains(drinkLiquid.toLowerCase())) {
                    if (item instanceof Liquid) {
                        Liquid liquid = (Liquid)item;
                        health += liquid.getHealthPoints();
                        inventory.remove(liquid);
                        return ReturnMessage.OK;
                    } else
                        return ReturnMessage.NOT_EATABLE;
                } else
                    return ReturnMessage.CANT_FIND;
            }
        }return ReturnMessage.CANT_FIND;
    }

    public ArrayList<Item> showInventory(){
        return inventory;
    }

    public ArrayList<Item> showItemsInRoom() {
        return currentRoom.showItemsInRoom();
    }
}