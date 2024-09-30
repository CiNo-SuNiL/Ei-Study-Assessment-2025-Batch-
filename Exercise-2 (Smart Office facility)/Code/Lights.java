public class Lights implements Observer {
    @Override
    public void update(boolean isOccupied) {
        if (isOccupied) {
            System.out.println("Lights turned on.");
        } else {
            System.out.println("Lights turned off.");
        }
    }
}
