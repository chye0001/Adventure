public class Consumable extends Item{

    protected int healthPoints;

    public Consumable(String itemName, int healthPoints){
        super(itemName);
        this.healthPoints = healthPoints;
    }

    public int getHealthPoints() {
        return healthPoints;
    }
}
