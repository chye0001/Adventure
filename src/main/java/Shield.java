public class Shield extends Weapon { // Udvidelse: Shields

    private int shieldHealth;

    public Shield (String itemName, int damage, int remainingUsages, int shieldHealth) {
        super(itemName, damage, remainingUsages);
        this.shieldHealth = shieldHealth;
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

    public int getShieldHealth() {
        return shieldHealth;
    }
    public void setShieldHealth(int updateShieldHealth) {
        this.shieldHealth = updateShieldHealth;
    }
}
