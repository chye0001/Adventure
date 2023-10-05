public abstract class Weapon extends Item {

    protected int damage;
    int remainingUsages;


    public Weapon(String itemName, int damage, int remainingUsages) {
        super(itemName);
        this.damage = damage;
        this.remainingUsages = remainingUsages;
    }

    public abstract int getDamage();

    public abstract int getRemainingUsages();

    public abstract void setRemainingUsages(int remainingUsages);

}
