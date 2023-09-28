public class AdventureController {

    Map map = new Map();
    Player player = new Player();

    public AdventureController() {
    }



    public void buildMap() {
        map.getMap();
    }

    public Room getCurrentRoom() {
        return player.getCurrentRoom();
    }

    public void setCurrentRoom() {
        player.setCurrentRoom(map.getCurrentRoom());
    }

    public void moveAround(String input) {
        player.getMoveAround(input);
    }

    public void setLastTeleport() {
        player.setLastTeleport(map.getLastTeleport());
    }
    public void teleport() {
        player.teleport();
    }
}