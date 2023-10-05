public class MeeleWeapon extends Weapon {

    MeeleWeapon(String itemName, int damage, int remainingUsages) {
        super(itemName, damage, remainingUsages);
    }

    @Override
    public int getDamage(){
        return damage;
    }

    @Override
    public int getRemainingUsages() {
        return remainingUsages;
    }
    @Override
    public void setRemainingUsages(int remainingUsage){
        this.remainingUsages = remainingUsage;
    }
}