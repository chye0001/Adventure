public class RangedWeapon extends Weapon {

    RangedWeapon(String itemName, int damage, int remainingUsages) {
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
    public void setRemainingUsages(int remainingUsages){
        this.remainingUsages = remainingUsages;
    }
}
