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

    public Item getPickedUpItem() {
      return player.getPickedUpItem();
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


    public ReturnMessage eat(String foodToEat) { // Potentiel cleanup i koden
        return player.eatFood(foodToEat);        // gør eat() metoden void
    }

    public ReturnMessage tryToEat(String tryToEatFood) { // Udvidelse: Klogere håndtering af giftigt mad (i brugerfladen)
        return player.tryToEatFood(tryToEatFood);
    }

    public ReturnMessage drink(String liquidToDrink) { //Mulighed for cleanup i koden
        return player.drinkLiquid(liquidToDrink);     //gør drink() void
    }

    public ReturnMessage tryToDrink(String tryToDrinkLiquid) {// Udvidelse: Klogere håndtering af giftigt mad (i brugerfladen) & Flere typer "consumables"
        return player.tryTodrinkLiquid(tryToDrinkLiquid);
    }

    public int getPlayerHealth() {
        return player.getPlayerHealth();
    }

    public ReturnMessage attack(String enemyToAttack) {
        return player.attack(enemyToAttack);
    }

    public int getDamageDone() {
        return player.getDamageDone();
    }

    public ReturnMessage equipWeapon1(String weaponToEquip) {
        return player.equipWeapon1(weaponToEquip);
    }

    public ReturnMessage equipWeapon2(String weaponToEquip) {
        return player.equipWeapon2(weaponToEquip);
    }

    public Weapon getAttemptEquipWeapon1() {
        return player.getAttemptEquipWeapon1();
    }

    public Weapon getAttemptEquipWeapon2() {
        return player.getAttemptEquipWeapon2();
    }

    public Weapon getEquippedWeapon1() {
        return player.getEquippedWeapon1();
    }


    public Weapon getEquippedWeapon2() {
        return player.getEquippedWeapon2();
    }

    public boolean unequip(String chosenWeaponToUnequip) {
        return player.unequipWeapon(chosenWeaponToUnequip);
    }

    public Weapon getUnequippedWeapon() {
        return player.getUnequippedWeapon();
    }

    public ArrayList<Enemy> getListOfEnemies() {
        return player.getListOfEnemies();
    }

    public int getEnemyHealth() {
        return player.getEnemyHealth();
    }

    public Weapon getEnemyWeapon() {
        return player.getEnemyWeapon();
    }

    public String getBattlingCurrentEnemy() {
        return player.getBattlingCurrentEnemy();
    }

    public Enemy getEnemyKilled() {
        return player.getEnemyKilled();
    }

    public void resetInventoryOnPlayerDeath() {
        player.resetInventoryOnPlayerDeath();
    }
}