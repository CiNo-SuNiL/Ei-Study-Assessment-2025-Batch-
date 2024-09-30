public class AC implements Observer {
    @Override
    public void update(boolean isOccupied) {
        if (isOccupied) {
            System.out.println("AC turned on.");
        } else {
            System.out.println("AC turned off.");
        }
    }
}
