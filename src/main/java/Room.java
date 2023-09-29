import java.util.ArrayList;

public class Room {

    private String roomName;
    private String description;
    private Room north;
    private Room south;
    private Room east;
    private Room west;
    private ArrayList<Item> itemInRoom = new ArrayList<>(5);

    Item item = new Item();

    public Room(String roomName, String description) {
        this.roomName = roomName;
        this.description = description;
    }

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

    public void leftedItem(String leftItem) {
        itemInRoom.add(new Item(leftItem));
    }

    public void addItemToRoom(Item addItem) {
        itemInRoom.add(addItem);
    }

    public Item removeItemFromRoom(String removeItem) {
        for (Item item : itemInRoom) {
            if (item.getItemName().contains(removeItem)) {
                itemInRoom.remove(new Item(removeItem));
                return item;
            } else
                System.out.println(removeItem + "does not exist in the room..");
        }
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