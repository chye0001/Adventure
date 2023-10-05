public class Consumable extends Item{

    protected int healthPoints;

    public Consumable(String itemName, int healthPoints){
        super(itemName); // super kalder superklassens konstruktør, som anvendes til at instanserer atributen, of fører denne videre til subklassen.
        this.healthPoints = healthPoints;
    }

    public int getHealthPoints() {
        return healthPoints;
    }
}