public class Troll extends Enemy{

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
            return ReturnMessage.ENEMY_DEAD;
        }
    }

    @Override
    public void enemyDead(Enemy enemyKilled) {
        currentRoom.dropItemInRoom(enemyWeapon);
        currentRoom.removeEnemyFromRoom(enemyKilled);
    }
}
