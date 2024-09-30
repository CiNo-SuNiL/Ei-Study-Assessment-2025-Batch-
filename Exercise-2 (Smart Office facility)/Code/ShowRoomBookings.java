
public class ShowRoomBookings implements Command {
    private ConferenceRoom room;
    public ShowRoomBookings(ConferenceRoom room) {
        this.room = room;
    }
    
    @Override
    public void execute() {
        room.showBooking();
    }
}