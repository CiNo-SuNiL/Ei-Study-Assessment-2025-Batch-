import java.time.LocalTime;

public class Booking {
    private LocalTime startTime;
    private LocalTime endTime;
    private User user;

    public Booking(LocalTime startTime, LocalTime endTime, User user) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.user=user;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }
    public User getUser(){
        return user;
    }
}
