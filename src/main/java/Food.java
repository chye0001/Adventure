public class Food extends Item{

    protected int healthPoints;

    Food(String itemName, int healthPoints) {
        super(itemName);
        this.healthPoints = healthPoints;
    }
    public int getHealthPoints(){
        return healthPoints;
    }


}
