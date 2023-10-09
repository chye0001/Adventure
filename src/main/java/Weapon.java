public abstract class Weapon extends Item {

    protected int damage;
    protected int remainingUsages;


    public Weapon(String itemName, int damage, int remainingUsages) { //Potentiel bug, itemName hedder ITEM_NAME i Item klassen.
        super(itemName);
        this.damage = damage;
        this.remainingUsages = remainingUsages;
    }

    public abstract int getDamage();

    public abstract int getRemainingUsages();

    public abstract void setRemainingUsages(int remainingUsages);

}
