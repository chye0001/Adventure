public class Item {

    private final String ITEM_NAME;

    public Item(String ITEM_NAME){
        this.ITEM_NAME = ITEM_NAME;
    }

    public String getITEM_NAME(){
        return ITEM_NAME;
    }

    public String toString() {
        return ITEM_NAME;
    }
}