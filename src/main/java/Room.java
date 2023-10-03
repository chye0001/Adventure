import java.util.ArrayList;

public class Room {

    private final String roomName;
    private final String description;
    private Room north;
    private Room south;
    private Room east;
    private Room west;
    private final ArrayList<Item> itemInRoom = new ArrayList<>(5);

    public Room(String roomName, String description) {
        this.roomName = roomName;
        this.description = description;
    }

    //getRoomName() og getDescription() kunne slettet da de ikke anvendes.
    public String getRoomName() {
        return roomName;
    }

    public String getDescription() {
        return description;
    }

    public Room getNorth() {
        return north;
    }

    public Room getSouth() {
        return south;
    }

    public Room getEast() {
        return east;
    }

    public Room getWest() {
        return west;
    }


    public void setNorth(Room newRoom) {
        this.north = newRoom;
    }

    public void setSouth(Room newRoom) {
        this.south = newRoom;
    }

    public void setEast(Room newRoom) {
        this.east = newRoom;
    }

    public void setWest(Room newRoom) {
        this.west = newRoom;
    }

    //Denne metode kunne godt slettes og erstattes med "addItemToRoom(Item addItem)" der, hvor "dropItemInRoom(Item droppedItem)" benyttes.
    public void dropItemInRoom(Item dropedItem) {
        itemInRoom.add(dropedItem);
    }

    public void addItemToRoom(Item addItem) {
        itemInRoom.add(addItem);
    }

    /*public void addFoodToRoom(Food addFood) {
        itemInRoom.add(addFood);
    }*/

    public Item removeItemFromRoom(String removeItem) {
        for (Item item : itemInRoom) {
            if (item.getItemName().toLowerCase().contains(removeItem.toLowerCase())) {
                itemInRoom.remove(item);
                return item;
            }
        }
        System.out.println("I can't take that, because the room has been looted dry");
        return null;
    }

    public ArrayList<Item> showItemsInRoom() {
        return itemInRoom;
    }

    public String toString() {
        return roomName + "\n" +
                "Room description: " + description + "\n";
    }

}