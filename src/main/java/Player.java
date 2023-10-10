import java.util.ArrayList;

public class Player {

    private Room currentRoom;
    private Room lastTeleport;
    private final ArrayList<Item> inventory = new ArrayList<>();
    private int playerHealth = 50;
    private Item pickedUpItem;
    private Weapon equippedWeapon1 = null;
    private Weapon equippedWeapon2 = null;
    private ReturnMessage checkWeapon1;
    private ReturnMessage checkWeapon2;
    private Weapon attemptEquipWeapon1;
    private Weapon attemptEquipWeapon2;
    private Weapon unequippedWeapon;
    private int dualWieldingDamage; //Udvidelse: dual wielding
    private int weaponDamage;
    private Enemy enemyKilled;
    private int enemyHealth;
    private Enemy enemyWeapon;
    private Enemy enemy;
    private int play = 0;

    public Player() {
    }

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

    public void setCurrentRoom(Room setRoom) {
        this.currentRoom = setRoom;
    }

    public void setLastTeleport(Room setLastTeleport) {
        this.lastTeleport = setLastTeleport;
    }

    public void takeItem(String takeItem) {
        Item itemToTake = currentRoom.removeItemFromRoom(takeItem);
        if (itemToTake != null) {
            inventory.add(itemToTake);
            pickedUpItem = itemToTake;
            System.out.println("\nYou picked up " + itemToTake);
        }
    }

    public Item getPickedUpItem() {
        return pickedUpItem;
    }

    public void dropItem(String dropItem) {
        Item dropItemInRoom = null;
        for (Item item : inventory) {
            if (item.getITEM_NAME().toLowerCase().contains(dropItem.toLowerCase())) {
                dropItemInRoom = item;
                currentRoom.dropItemInRoom(item);
            } else
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
                if (item.getITEM_NAME().toLowerCase().contains(eatFood.toLowerCase())) {
                    if (item instanceof Food food) {
                        playerHealth += food.getHealthPoints();
                        inventory.remove(food);
                        return ReturnMessage.EDIBLE;
                    } else
                        return ReturnMessage.NOT_EDIBLE;
                }
            }
        }
        return ReturnMessage.CANT_FIND;
    }

    // Udvidelse: Flere typer af "consumables"
    public ReturnMessage drinkLiquid(String drinkLiquid) {
        if (inventory.isEmpty()) {
            return ReturnMessage.CANT_FIND;
        } else {
            for (Item item : inventory) {
                if (item.getITEM_NAME().toLowerCase().contains(drinkLiquid.toLowerCase())) {
                    if (item instanceof Liquid liquid) {
                        playerHealth += liquid.getHealthPoints();
                        inventory.remove(liquid);
                        return ReturnMessage.EDIBLE;
                    } else
                        return ReturnMessage.NOT_DRINKABLE;
                }
            }
        }
        return ReturnMessage.CANT_FIND;
    }

    // UDVIDELSE - Klogere håndtering af giftig mad (i brugerfladen)
    public ReturnMessage tryToEatFood(String eatFood) {
        if (inventory.isEmpty()) {
            return ReturnMessage.CANT_FIND;
        } else {
            for (Item item : inventory) {
                if (item.getITEM_NAME().toLowerCase().contains(eatFood.toLowerCase())) {
                    if (item instanceof Food food) {
                        if (food.getHealthPoints() < 0) {
                            return ReturnMessage.REALLY_EAT;
                        } else {
                            playerHealth += food.getHealthPoints();
                            inventory.remove(food);
                            return ReturnMessage.EDIBLE;
                        }
                    } else
                        return ReturnMessage.NOT_EDIBLE;
                }
            }
        }
        return ReturnMessage.CANT_FIND;
    }


    // UDVIDELSE - Klogere håndtering af giftig mad (i brugerfladen)
    public ReturnMessage tryTodrinkLiquid(String drinkLiquid) {
        if (inventory.isEmpty()) {
            return ReturnMessage.CANT_FIND;
        } else {
            for (Item item : inventory) {
                if (item.getITEM_NAME().toLowerCase().contains(drinkLiquid.toLowerCase())) {
                    if (item instanceof Liquid liquid) {
                        if (liquid.healthPoints < 0) {
                            return ReturnMessage.REALLY_DRINK;
                        } else {
                            playerHealth += liquid.getHealthPoints();
                            inventory.remove(liquid);
                            return ReturnMessage.EDIBLE;
                        }
                    } else
                        return ReturnMessage.NOT_DRINKABLE;
                }
            }
        }
        return ReturnMessage.CANT_FIND;
    }

    public ArrayList<Item> showInventory() {
        return inventory;
    }

    public ArrayList<Item> showItemsInRoom() {
        return currentRoom.showItemsInRoom();
    }

    /*
    //Basis udgave af attak-metoden.
    public ReturnMessage attack() {
        if (checkWeapon1 == ReturnMessage.WEAPON_EQUIPPED) {
            if (equippedWeapon1.getRemainingUsages() > 0) {
                equippedWeapon1.getDamage(); // Skal udskiftes når der skal tilføjes fjender
                equippedWeapon1.setRemainingUsages(equippedWeapon1.getRemainingUsages() - 1);
                return ReturnMessage.ATTACK1;
            } else if (equippedWeapon1.getRemainingUsages() == 0) {
                return ReturnMessage.NO_AMMO;
            }
        }
        return ReturnMessage.WEAPON_NOT_EQUIPPED;
    }
     */


    public ReturnMessage attack() { //Denne attack-metode angriber den tomme luft
        if (checkWeapon1 == ReturnMessage.WEAPON_EQUIPPED && checkWeapon2 == ReturnMessage.WEAPON_EQUIPPED) { //If both weapons are equiped
            if (equippedWeapon1.getRemainingUsages() > 0 && equippedWeapon2.getRemainingUsages() > 0) { //If they both have ammo
                equippedWeapon1.setRemainingUsages(equippedWeapon1.getRemainingUsages() - 1);
                equippedWeapon2.setRemainingUsages(equippedWeapon2.getRemainingUsages() - 1);
                dualWieldingDamage = equippedWeapon1.getDamage() + equippedWeapon2.getDamage();
                return ReturnMessage.DUAL_WIELDING_ATTACK;

            } else if (equippedWeapon1.getRemainingUsages() == 0 && equippedWeapon2.getRemainingUsages() == 0) { //If neither weapon has ammo
                return ReturnMessage.NO_AMMO;

            } else if (equippedWeapon1.getRemainingUsages() == 0 && equippedWeapon2.getRemainingUsages() > 0) {
                equippedWeapon2.setRemainingUsages(equippedWeapon2.getRemainingUsages() - 1);
                weaponDamage = equippedWeapon2.getDamage();
                return ReturnMessage.WEAPON1_NO_AMMO_USE_WEAPON_2;

            } else if (equippedWeapon2.getRemainingUsages() == 0 && equippedWeapon1.getRemainingUsages() > 0) {
                equippedWeapon1.setRemainingUsages(equippedWeapon1.getRemainingUsages() - 1);
                weaponDamage = equippedWeapon1.getDamage();
                return ReturnMessage.WEAPON2_NO_AMMO_USE_WEAPON_1;

            }
        }

        if (checkWeapon1 == ReturnMessage.WEAPON_EQUIPPED) {
            if (equippedWeapon1.getRemainingUsages() > 0) {
                weaponDamage = equippedWeapon1.getDamage();
                equippedWeapon1.setRemainingUsages(equippedWeapon1.getRemainingUsages() - 1);
                return ReturnMessage.ATTACK1;
            } else if (equippedWeapon1.getRemainingUsages() == 0) {
                return ReturnMessage.NO_AMMO;
            }
        }

        if (checkWeapon2 == ReturnMessage.WEAPON_EQUIPPED) {
            if (equippedWeapon2.getRemainingUsages() > 0) {
                weaponDamage = equippedWeapon2.getDamage();
                equippedWeapon2.setRemainingUsages(equippedWeapon2.getRemainingUsages() - 1);
                return ReturnMessage.ATTACK2;
            } else if (equippedWeapon2.getRemainingUsages() == 0) {
                return ReturnMessage.NO_AMMO;
            }
        }

        return ReturnMessage.WEAPON_NOT_EQUIPPED;
    }

    //TODO ATTACK_SEQUNCE
    //Hjælpe metode for variation 1 med dual wielding attack.
    public ReturnMessage attackSequenceDualWielding(String enemyToAttack) {
        for (Enemy enemy : currentRoom.getListOfEnemies()) {
            if (enemy.getEnemyName().toLowerCase().contains(enemyToAttack.toLowerCase())) {
                enemy.setEnemyHealth(enemy.getEnemyHealth() - dualWieldingDamage);
                playerHealth =- enemy.getEnemyWeapon().getDamage();
            }

            if (enemy.hasEnemyDied() == ReturnMessage.ENEMY_DEAD && playerHealth <= 0) {
                return ReturnMessage.PLAYER_DEAD;

            } else if (enemy.hasEnemyDied() == ReturnMessage.ENEMY_DEAD && playerHealth > 0) {
                enemyKilled = enemy;
                return ReturnMessage.ENEMY_KILLED;

            } else if (playerHealth <= 0 && enemy.hasEnemyDied() == ReturnMessage.ENEMY_ALIVE) {
                return ReturnMessage.PLAYER_DEAD;

            } else
                return ReturnMessage.BATTLE_ONGOING_DUAL_WIELDING_ATTACK;

        }
        return ReturnMessage.CANT_FIND;
    }

    //Hjælpe metode for variation 2 med dual wielding attack hvor weapon2 ingen ammo har.
    public ReturnMessage attackSequenceUseWeapon1Weapon2NoAmmo(String enemyToAttack) {
        for (Enemy enemy : currentRoom.getListOfEnemies()) {
            if (enemy.getEnemyName().toLowerCase().contains(enemyToAttack.toLowerCase())) {
                enemy.setEnemyHealth(enemy.getEnemyHealth() - weaponDamage);
                playerHealth =- enemy.getEnemyWeapon().getDamage();
            }

            if (enemy.hasEnemyDied() == ReturnMessage.ENEMY_DEAD && playerHealth <= 0) {
                return ReturnMessage.PLAYER_DEAD;

            } else if (enemy.hasEnemyDied() == ReturnMessage.ENEMY_DEAD && playerHealth > 0) {
                enemyKilled = enemy;
                return ReturnMessage.ENEMY_KILLED;

            } else if (playerHealth <= 0 && enemy.hasEnemyDied() == ReturnMessage.ENEMY_ALIVE) {
                return ReturnMessage.PLAYER_DEAD;

            } else
                return ReturnMessage.BATTLE_ONGOING_USE_WEAPON1_WEAPON2_NO_AMMO;

        }
        return ReturnMessage.CANT_FIND;
    }

    //Hjælpe metode for variation 3 med dual wielding attack hvis weapon1 ingen ammo har.
    public ReturnMessage attackSequenceUseWeapon2Weapon1NoAmmo(String enemyToAttack) {
        for (Enemy enemy : currentRoom.getListOfEnemies()) {
            if (enemy.getEnemyName().toLowerCase().contains(enemyToAttack.toLowerCase())) {
                enemy.setEnemyHealth(enemy.getEnemyHealth() - weaponDamage);
                playerHealth =- enemy.getEnemyWeapon().getDamage();
            }

            if (enemy.hasEnemyDied() == ReturnMessage.ENEMY_DEAD && playerHealth <= 0) {
                return ReturnMessage.PLAYER_DEAD;

            } else if (enemy.hasEnemyDied() == ReturnMessage.ENEMY_DEAD && playerHealth > 0) {
                enemyKilled = enemy;
                return ReturnMessage.ENEMY_KILLED;

            } else if (playerHealth <= 0 && enemy.hasEnemyDied() == ReturnMessage.ENEMY_ALIVE) {
                return ReturnMessage.PLAYER_DEAD;

            } else
                return ReturnMessage.BATTLE_ONGOING_USE_WEAPON2_WEAPON1_NO_AMMO;

        }
        return ReturnMessage.CANT_FIND;
    }

    //Hjælpe metode for variation 4 hvor weapon1 er equipped og weapon2 ikke er equipped.
    public ReturnMessage attackSequenceUseWeapon1Weapon2NotEquipped(String enemyToAttack) {
        for (Enemy enemy : currentRoom.getListOfEnemies()) {
            if (enemy.getEnemyName().toLowerCase().contains(enemyToAttack.toLowerCase())) {
                enemy.setEnemyHealth(enemy.getEnemyHealth() - weaponDamage);
                enemyHealth = enemy.enemyHealth;
                playerHealth -= enemy.getEnemyWeapon().getDamage();
            }

            if (enemy.hasEnemyDied() == ReturnMessage.ENEMY_DEAD && playerHealth <= 0) {
                return ReturnMessage.PLAYER_DEAD;

            } else if (enemy.hasEnemyDied() == ReturnMessage.ENEMY_DEAD && playerHealth > 0) {
                enemyKilled = enemy;
                return ReturnMessage.ENEMY_KILLED;

            } else if (playerHealth <= 0 && enemy.hasEnemyDied() == ReturnMessage.ENEMY_ALIVE) {
                return ReturnMessage.PLAYER_DEAD;

            } else
                return ReturnMessage.BATTLE_ONGOING_USE_WEAPON1_WEAPON2_NOT_EQUIPPED;

        }
        return ReturnMessage.CANT_FIND;
    }

    //Hjælpe metode for variation 5 hvor weapon2 er equipped og weapon1 ikke er equipped.
    public ReturnMessage attackSequenceUseWeapon2Weapon1NotEquipped(String enemyToAttack) {
        for (Enemy enemy : currentRoom.getListOfEnemies()) {
            if (enemy.getEnemyName().toLowerCase().contains(enemyToAttack.toLowerCase())) {
                enemy.setEnemyHealth(enemy.getEnemyHealth() - weaponDamage);
                enemyHealth = enemy.enemyHealth;
                playerHealth =- enemy.getEnemyWeapon().getDamage();
            }

            if (enemy.hasEnemyDied() == ReturnMessage.ENEMY_DEAD && playerHealth <= 0) {
                return ReturnMessage.PLAYER_DEAD;

            } else if (enemy.hasEnemyDied() == ReturnMessage.ENEMY_DEAD && playerHealth > 0) {
                enemyKilled = enemy;
                return ReturnMessage.ENEMY_KILLED;

            } else if (playerHealth <= 0 && enemy.hasEnemyDied() == ReturnMessage.ENEMY_ALIVE) {
                return ReturnMessage.PLAYER_DEAD;

            } else
                return ReturnMessage.BATTLE_ONGOING_USE_WEAPON2_WEAPON1_NOT_EQUIPPED;

        }
        return ReturnMessage.CANT_FIND;
    }

    public Enemy getEnemyKilled() {
        return enemyKilled;
    }

    //Denne attack-metode angriber en faktisk enemy.
    public ReturnMessage attack(String enemyToAttack) {
        if (checkWeapon1 == ReturnMessage.WEAPON_EQUIPPED && checkWeapon2 == ReturnMessage.WEAPON_EQUIPPED) { //If both weapons are equiped
            if (equippedWeapon1.getRemainingUsages() > 0 && equippedWeapon2.getRemainingUsages() > 0) { //If they both have ammo
                equippedWeapon1.setRemainingUsages(equippedWeapon1.getRemainingUsages() - 1);
                equippedWeapon2.setRemainingUsages(equippedWeapon2.getRemainingUsages() - 1);
                dualWieldingDamage = equippedWeapon1.getDamage() + equippedWeapon2.getDamage();
                return attackSequenceDualWielding(enemyToAttack);

            } else if (equippedWeapon1.getRemainingUsages() == 0 && equippedWeapon2.getRemainingUsages() == 0) { //If neither weapon has ammo
                return ReturnMessage.NO_AMMO;

            } else if (equippedWeapon1.getRemainingUsages() == 0 && equippedWeapon2.getRemainingUsages() > 0) {
                equippedWeapon2.setRemainingUsages(equippedWeapon2.getRemainingUsages() - 1);
                weaponDamage = equippedWeapon2.getDamage();
                return attackSequenceUseWeapon1Weapon2NoAmmo(enemyToAttack);


            } else if (equippedWeapon2.getRemainingUsages() == 0 && equippedWeapon1.getRemainingUsages() > 0) {
                equippedWeapon1.setRemainingUsages(equippedWeapon1.getRemainingUsages() - 1);
                weaponDamage = equippedWeapon1.getDamage();
                return attackSequenceUseWeapon2Weapon1NoAmmo(enemyToAttack);

            }
        }

        if (checkWeapon1 == ReturnMessage.WEAPON_EQUIPPED) {
            if (equippedWeapon1.getRemainingUsages() > 0) {
                weaponDamage = equippedWeapon1.getDamage();
                equippedWeapon1.setRemainingUsages(equippedWeapon1.getRemainingUsages() - 1);
                return attackSequenceUseWeapon1Weapon2NotEquipped(enemyToAttack);

            } else if (equippedWeapon1.getRemainingUsages() == 0) {
                return ReturnMessage.NO_AMMO;
            }
        }

        if (checkWeapon2 == ReturnMessage.WEAPON_EQUIPPED) {
            if (equippedWeapon2.getRemainingUsages() > 0) {
                weaponDamage = equippedWeapon2.getDamage();
                equippedWeapon2.setRemainingUsages(equippedWeapon2.getRemainingUsages() - 1);
                return attackSequenceUseWeapon2Weapon1NotEquipped(enemyToAttack);

            } else if (equippedWeapon2.getRemainingUsages() == 0) {
                return ReturnMessage.NO_AMMO;
            }
        }

        return ReturnMessage.WEAPON_NOT_EQUIPPED;
    }

    public int getDualWieldingDamage() {
        return dualWieldingDamage;
    }

    public int getDamageDone() {
        return weaponDamage;
    }

    public int getEnemyHealth() {
        return enemyHealth;
    }

    public int getPlayerHealth() {
        return playerHealth;
    }

    //udvidelse: Shields
    public ReturnMessage shield() {
        if (checkWeapon1 == ReturnMessage.WEAPON_EQUIPPED) {
            if (equippedWeapon1.getRemainingUsages() > 0) {
                equippedWeapon1.getDamage(); // Skal udskiftes når der skal tilføjes fjender
                equippedWeapon1.setRemainingUsages(equippedWeapon1.getRemainingUsages() - 1);
                return ReturnMessage.ATTACK1;
            } else if (equippedWeapon1.getRemainingUsages() == 0) {
                return ReturnMessage.NO_AMMO;
            }
        }
        return ReturnMessage.WEAPON_NOT_EQUIPPED;
    }

    public ReturnMessage equipWeapon1(String itemName) {
        for (Item item : inventory) {
            if (item.getITEM_NAME().toLowerCase().contains(itemName.toLowerCase())) {
                if (item instanceof Weapon) {
                    attemptEquipWeapon1 = (Weapon) item; //attemptEquipWeapon1 bliver kun hentet af get-metode, hvis weapon slot 1 ikke er tomt.
                    if (equippedWeapon1 != null) {
                        return ReturnMessage.WEAPON_SLOT_UNAVAILABLE;
                    } else if (equippedWeapon2 != null) {
                        if (equippedWeapon2.getITEM_NAME().toLowerCase().contains(itemName.toLowerCase())) {
                            return ReturnMessage.WEAPON_ALREADY_EQUIPPED;
                        } else {
                            equippedWeapon1 = (Weapon) item;
                            checkWeapon1 = ReturnMessage.WEAPON_EQUIPPED;
                            return ReturnMessage.WEAPON_EQUIPPED;
                        }
                    } else {
                        equippedWeapon1 = (Weapon) item;
                        checkWeapon1 = ReturnMessage.WEAPON_EQUIPPED;
                        return ReturnMessage.WEAPON_EQUIPPED;
                    }
                } else {
                    return ReturnMessage.IS_NOT_A_WEAPON;
                }
            }
        }
        return ReturnMessage.CANT_FIND;
    }

    public Weapon getAttemptEquipWeapon1() {
        return attemptEquipWeapon1;
    }

    public Weapon getEquippedWeapon1() {
        return equippedWeapon1;
    }

    //Udvidelse: dual wielding
    public ReturnMessage equipWeapon2(String itemName) {
        for (Item item : inventory) {
            if (item.getITEM_NAME().toLowerCase().contains(itemName.toLowerCase())) {
                if (item instanceof Weapon) {
                    attemptEquipWeapon2 = (Weapon) item; //attemptEquipWeapon1 bliver kun hentet af get-metode, hvis weapon slot 1 ikke er tomt.
                    if (equippedWeapon2 != null) {
                        return ReturnMessage.WEAPON_SLOT_UNAVAILABLE;
                    } else if (equippedWeapon1 != null) {
                        if (equippedWeapon1.getITEM_NAME().toLowerCase().contains(itemName.toLowerCase())) {
                            return ReturnMessage.WEAPON_ALREADY_EQUIPPED;
                        } else {
                            equippedWeapon2 = (Weapon) item;
                            checkWeapon2 = ReturnMessage.WEAPON_EQUIPPED;
                            return ReturnMessage.WEAPON_EQUIPPED;
                        }
                    } else {
                        equippedWeapon2 = (Weapon) item;
                        checkWeapon2 = ReturnMessage.WEAPON_EQUIPPED;
                        return ReturnMessage.WEAPON_EQUIPPED;
                    }
                } else {
                    return ReturnMessage.IS_NOT_A_WEAPON;
                }
            }
        }
        return ReturnMessage.CANT_FIND;
    }

    public Weapon getAttemptEquipWeapon2() {
        return attemptEquipWeapon2;
    }

    public Weapon getEquippedWeapon2() {
        return equippedWeapon2;
    }

    public boolean unequipWeapon(String chosenWeaponToUnequip) {
        if (checkWeapon1 == ReturnMessage.WEAPON_EQUIPPED) {
            if (equippedWeapon1.getITEM_NAME().toLowerCase().contains(chosenWeaponToUnequip.toLowerCase())) {
                unequippedWeapon = equippedWeapon1;
                checkWeapon1 = null;
                equippedWeapon1 = null;
                return true;
            }
        }
        if (checkWeapon2 == ReturnMessage.WEAPON_EQUIPPED) {
            if (equippedWeapon2.getITEM_NAME().toLowerCase().contains(chosenWeaponToUnequip.toLowerCase())) {
                unequippedWeapon = equippedWeapon2;
                checkWeapon2 = null;
                equippedWeapon2 = null;
                return true;
            }
        }
        return false;
    }

    public Weapon getUnequippedWeapon() {
        return unequippedWeapon;
    }

    public ArrayList<Enemy> getListOfEnemies() {
        return currentRoom.getListOfEnemies();
    }

    public void resetInventoryOnPlayerDeath() {
        //Removes all items
        for (Item item : inventory) {
            inventory.remove(item);
        }
    }

}