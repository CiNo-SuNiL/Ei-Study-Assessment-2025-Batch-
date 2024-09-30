import java.time.LocalTime;

public class CancelRoomCommand implements Command {
    private ConferenceRoom room;
    private LocalTime startTime;
    private LocalTime endTime;
    private User user;
    public CancelRoomCommand(ConferenceRoom room, LocalTime startTime, LocalTime endTime, User user) {
        this.room = room;
        this.startTime = startTime;
        this.endTime = endTime;
        this.user=user;
    }
    
    @Override
    public void execute() {
        room.cancelBooking(startTime, endTime, user);
    }
}
