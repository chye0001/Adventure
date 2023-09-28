public class Room {

    private String roomName;
    private String description;
    private Room north;
    private Room south;
    private Room east;
    private Room west;

    public Room (String roomName, String description) {
        this.roomName = roomName;
        this.description = description;
    }
    public String getRoomName(){
        return roomName;
    }
    public String getDescription(){
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


    public void setNorth(Room newRoom){
        this.north = newRoom;
    }
    public void setSouth(Room newRoom){
        this.south = newRoom;
    }
    public void setEast(Room newRoom){
        this.east = newRoom;
    }
    public void setWest(Room newRoom){
        this.west = newRoom;
    }


    public String toString(){
        return roomName + "\n" +
                "Room description: " + description + "\n";
    }

}
