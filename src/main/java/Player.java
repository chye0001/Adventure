import java.util.ArrayList;

public class Player {

    Item item = new Item();

    private Room currentRoom;
    private Room lastTeleport;
    private ArrayList<Item> inventory = new ArrayList<>(5);

    public Player(){}


    public void teleport() {
        Room tempCurrentRoom = currentRoom;
        currentRoom = lastTeleport;

        lastTeleport = tempCurrentRoom;
    }


    public void moveAround(String direction) {

        if (direction.equalsIgnoreCase("north")) {
            if (currentRoom.getNorth() == null) {
                System.out.println("You can not go that way");
            } else
                currentRoom = currentRoom.getNorth();

        } else if (direction.equalsIgnoreCase("south")) {
            if (currentRoom.getSouth() == null) {
                System.out.println("You can not go that way");
            } else
                currentRoom = currentRoom.getSouth();

        } else if (direction.equalsIgnoreCase("east")) {
            if (currentRoom.getEast() == null) {
                System.out.println("You can not go that way");
            } else
                currentRoom = currentRoom.getEast();

        } else if (direction.equalsIgnoreCase("west")) {
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

    public void setCurrentRoom(Room setRoom){ this.currentRoom = setRoom;}

    public void setLastTeleport(Room setLastTeleport){
        this.lastTeleport = setLastTeleport;
    }

    public void takeItem(String takeItem) {
        if (currentRoom.removeItemFromRoom(takeItem) != null) {
            inventory.add(new Item(takeItem));
        }
    }

    public Item dropItem (String dropItem) {
        for (Item item : inventory) {
            if (item.getItemName().contains(dropItem)) {
                inventory.remove(new Item(dropItem)); //Potentiel bug, vi instancerer nyt objekt, og fjerner det. Før var det kun en string i parameteren.
                currentRoom.leftedItem(dropItem);
                return item;
            }else
                System.out.println("I can not find " + dropItem + "in the inventory...");
        }       return null;
    }

    public ArrayList<Item> showInventory(){
        return inventory;
    }


}






