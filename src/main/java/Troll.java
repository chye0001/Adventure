public class Troll extends Enemy{

    private Troll troll;

    Troll(String enemyName, int enemyHealth, Weapon enemyWeapon, Room currentRoom) {
        super(enemyName, enemyHealth, enemyWeapon, currentRoom);
    }

    @Override
    public String getEnemyName() {
        return enemyName;
    }

    @Override
    public int getEnemyHealth() {
        return enemyHealth;
    }

    @Override
    public void setEnemyHealth(int enemyHealth){
        this.enemyHealth = enemyHealth;
    }

    @Override
    public Weapon getEnemyWeapon() {
        return enemyWeapon;
    }

    @Override
    public ReturnMessage hasEnemyDied(){
        if (enemyHealth > 0) {
            return ReturnMessage.ENEMY_ALIVE;

        } else {
            currentRoom.dropItemInRoom(enemyWeapon);
            currentRoom.removeEnemyFromRoom(troll);
            return ReturnMessage.ENEMY_DEAD;
        }
    }
}
