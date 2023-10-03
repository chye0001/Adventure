import java.util.ArrayList;

public class AdventureController {

    //De instancerede objekter af map og player, kalder man object referencer.
    //De skaber nemlig forbindelse fra AdventureController til Player og Map klasserne.
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

    public void takeItemToInventory(String input) {
        player.takeItem(input);
        }

    public void dropItemFromInventory(String input) {
        player.dropItem(input);

    }

    public ArrayList<Item> showItemsInRoom() {
       return player.showItemsInRoom();
    }

    public ArrayList<Item> showInventory() {
        return player.showInventory();
    }

    public ReturnMessage eat(String foodToEat) {
        return player.eatFood(foodToEat);
    }

    public int getHealth() {
       return player.getHealth();
    }
}