public class CancelAllBookings implements Command {
    private ConferenceRoom room;

    public CancelAllBookings(ConferenceRoom room) {
        this.room = room;
    }

    @Override
    public void execute() {
        room.cancelBooking();
        room.setBooked(false);
    }
}