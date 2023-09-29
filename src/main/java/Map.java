public class Map {

    private Room currentRoom;
    private Room lastTeleport;


    public Map(){}
    public void buildMap() {
        Room room1 = new Room("Room 1", "You are in Room 1");
        Room room2 = new Room("Room 2", "You are in Room 2");
        Room room3 = new Room("Room 3", "You are in Room 3");
        Room room4 = new Room("Room 4", "You are in Room 4");
        Room room5 = new Room("Room 5", "You are in Room 5");
        Room room6 = new Room("Room 6", "You are in Room 6");
        Room room7 = new Room("Room 7", "You are in Room 7");
        Room room8 = new Room("Room 8", "You are in Room 8");
        Room room9 = new Room("Room 9", "You are in Room 9");

        room1.setEast(room2);
        room1.setNorth(null);
        room1.setSouth(room4);
        room1.setWest(null);
        room1.addItemToRoom(new Item("Long sword"));


        room2.setEast(room3);
        room2.setNorth(null);
        room2.setSouth(null);
        room2.setWest(room1);
        room2.addItemToRoom(new Item("Pile of Bones"));
        room2.addItemToRoom(new Item("Rose"));


        room3.setEast(null);
        room3.setNorth(null);
        room3.setSouth(room6);
        room3.setWest(room2);
        room3.addItemToRoom(new Item("..."));

        room4.setEast(null);
        room4.setNorth(room1);
        room4.setSouth(room7);
        room4.setWest(null);
        room4.addItemToRoom(new Item("Magic Orb"));

        room5.setEast(null);
        room5.setNorth(null);
        room5.setSouth(room8);
        room5.setWest(null);
        room5.addItemToRoom(new Item("Long sword"));

        room6.setEast(null);
        room6.setNorth(room3);
        room6.setSouth(room9);
        room6.setWest(null);
        room6.addItemToRoom(new Item("Pile of bones"));

        room7.setEast(room8);
        room7.setNorth(room4);
        room7.setSouth(null);
        room7.setWest(null);
        room7.addItemToRoom(new Item("Magic Orb"));

        room8.setEast(room9);
        room8.setNorth(room5);
        room8.setSouth(null);
        room8.setWest(room7);
        room8.addItemToRoom(new Item("Long sword"));

        room9.setEast(null);
        room9.setNorth(room6);
        room9.setSouth(null);
        room9.setWest(room8);
        room9.addItemToRoom(new Item("Rose"));

        currentRoom = room1;
        lastTeleport = room1;

    }

    public void getMap() {
        buildMap();
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public Room getLastTeleport() {
        return lastTeleport;
    }

}
