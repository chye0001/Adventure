public class Enemy {
    private String enemyName;

    private String description;

    private int enemyhealthpoints;

    private int damage;

    private Room room;

    private Weapon weapon;


    public Enemy(String enemyName, String description, int enemyhealthpoints, int damage, Room room, Weapon weapon) {
        this.enemyName = enemyName;
        this.description = description;
        this.enemyhealthpoints = enemyhealthpoints;
        this.damage = damage;
        this.room = room;
        this.weapon = weapon;
    }

    public String getEnemyName() {
        return enemyName;
    }

    public String getDescription() {
        return description;
    }


    public int getEnemyHp() {
        return enemyhealthpoints;
    }

    public int getDamage() {
        return damage;
    }

    public Room getRoom() {
        return room;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public boolean enemyDied() {
        if (enemyhealthpoints < 1) {
            room.addItemToRoom(weapon);
            room.removeEnemy(this);
            return true;
        }else {
            return false;
        }
    }

}
