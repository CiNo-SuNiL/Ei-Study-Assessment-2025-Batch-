import java.time.LocalTime;

public class BookRoomCommand implements Command {
    private ConferenceRoom room;
    private LocalTime startTime;
    private int duration;
    private LocalTime endTime;
    private User user;

    public BookRoomCommand(ConferenceRoom room, LocalTime startTime,int duration, LocalTime endTime, User loginUser) {
        this.room = room;
        this.startTime = startTime;
        this.duration = duration;
        this.endTime = endTime;
        this.user=loginUser;
    }

    @Override
    public void execute() {
        room.setBooked(true);
        room.bookRoom(startTime, endTime, user);
        System.out.println(room.getRoomNumber() + " booked from " + startTime + " for " + duration + " minutes.");
        System.out.println();
    }
}
