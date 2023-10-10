public abstract class Enemy {

    protected String enemyName;
    protected int enemyHealth;
    protected Weapon enemyWeapon;
    protected Room currentRoom;

    public Enemy(String enemyName, int enemyHealth, Weapon enemyWeapon, Room currentRoom) {
        this.enemyName = enemyName;
        this.enemyHealth = enemyHealth;
        this.enemyWeapon = enemyWeapon;
        this.currentRoom = currentRoom;
    }

    public abstract int getEnemyHealth();
    public abstract void setEnemyHealth(int enemyHealth);

    public abstract String getEnemyName();

    public abstract Weapon getEnemyWeapon();

    public abstract ReturnMessage hasEnemyDied();

    public String toString() {
        return enemyName + "\n" + enemyHealth + "hp";
    }

}
