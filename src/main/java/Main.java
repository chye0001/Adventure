public class Main {
    public static void main(String[] args) {

        AdventureController controller = new AdventureController();
        UserInterface ui = new UserInterface(controller);
        ui.startGame();
    }
}