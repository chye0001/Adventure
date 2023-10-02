import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;


import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {


    Player player;
    private Room room1;
    private Room room2;
    ArrayList<Item> inventory;

    @BeforeEach
    public void Setup(){
        player = new Player();
        room1 = new Room("room 1" , "you are in room 1");
        room2 = new Room("room 2", "you are in room 2");
        inventory = new ArrayList<>(3);
    }

    @Test
    void teleport() {
        player.setCurrentRoom(room1);
        player.setLastTeleport(room2);
        player.teleport();
        assertEquals(room2, player.getCurrentRoom());

    }

    @Test
    void moveAround() {
        player.setCurrentRoom(room1);
        player.moveAround("north");
        assertEquals(room2, player.getCurrentRoom());
    }

    @Test
    void takeItem() {
       Item item = new Item("Sword");
        room1.addItemToRoom(item);
        player.setCurrentRoom(room1);

        player.takeItem("Sword");

        //Inventory has size 1 because we add sword to it.
        int actualInventorySize = inventory.size();
        int expectedInventorySize = 1;
        assertEquals(expectedInventorySize, actualInventorySize);
    }

    @Test
    void take_Item_That_Does_Not_Exist_In_Room() {
        Item item = new Item("Sword");
        room1.addItemToRoom(item);
        player.setCurrentRoom(room1);

        player.takeItem("Sword");

        //Inventory has size 1 because we add sword to it.
        int actualInventorySize = inventory.size();
        int expectedInventorySize = 1;
        assertEquals(expectedInventorySize, actualInventorySize);
    }

    @Test
    void drop_One_Item_From_Inventory() {
        Item mirror = new Item("Mirror");
        inventory.add(mirror);

        player.dropItem("mirror");

        int actualInventorySize = inventory.size();
        int expectedInventorySize = 0;
        assertEquals(expectedInventorySize, actualInventorySize);
    }

    @Test
    void drop_Item_From_Empty_Inventory() {
        player.dropItem("mirror");

        //String actualInventorySize = player.dropItem("mirror");
        String expectedInventorySize = "I can not find mirror in the inventory...";
        //assertEquals(expectedInventorySize, actualInventorySize);
    }

    @Test
    void show_Inventory_With_Nothing_In_It() {
        //Playerens inventory er 0 til at starte med.

        ArrayList<Item> expectedInventory = new ArrayList<>(0);
        ArrayList<Item> actualInventory = player.showInventory();

        assertEquals(expectedInventory, actualInventory);

    }

    @Test
    void show_Inventory_With_One_Item_In_It() {

        //Apple gets added to inventory
        Item apple = new Item("Apple");
        inventory.add(apple);

        ArrayList<Item> expectedInventory = inventory;
        ArrayList<Item> actualInventory = player.showInventory();

        assertEquals(expectedInventory, actualInventory);
    }

    @Test
    void show_Inventory_With_Multiple_Item_In_It() {

        //Multiple items gets added to inventory
        Item apple = new Item("Apple");
        Item map = new Item("Map");
        Item steak = new Item("Juicy Steak");
        inventory.add(apple);
        inventory.add(map);
        inventory.add(steak);

        ArrayList<Item> expectedInventory = inventory;
        ArrayList<Item> actualInventory = player.showInventory();

        assertEquals(expectedInventory, actualInventory);
    }
}