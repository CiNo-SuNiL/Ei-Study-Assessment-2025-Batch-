import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.time.LocalTime;

public class ConferenceRoom {
    private String roomNum;
    private boolean isBooked;
    private boolean isOccupied;
    private int maxCapacity;
    private List<Booking> bookings;
    private List<Observer> observers;

    public ConferenceRoom(String roomNum) {
        this.roomNum = roomNum;
        this.isBooked = false;
        this.isOccupied = false;
        this.maxCapacity=0;
        this.bookings = new ArrayList<>();
        this.observers = new ArrayList<>();
    }

    public String getRoomNumber() {
        return roomNum;
    }

    public void setMaxCapacity(int maxCapacity) {
        if (maxCapacity <= 0) {
            throw new IllegalArgumentException("Invalid capacity. Please enter a valid positive number.");
        }
        this.maxCapacity = maxCapacity;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(isOccupied);
        }
    }

    public void setOccupancy(int occupants) {
        if (occupants >= 2) {
            this.isOccupied = true;
            notifyObservers();
            System.out.println(roomNum+ " is now occupied by " + occupants + " persons. AC and lights turned on.");
            System.out.println();
        }else if (occupants==0){
            this.isOccupied = false;
            notifyObservers();
            System.out.println(roomNum+ " is now unoccupied. AC and lights turned off.");
            System.out.println();
        }       
        else {
            this.isOccupied = false;
            notifyObservers();
            System.out.println(roomNum+ " occupancy insufficient to mark as occupied.");
            System.out.println();
        }
    }

    public boolean isBooked() {
        return isBooked;
    }

    public void setBooked(boolean booked) {
        isBooked = booked;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void setOccupied(boolean occupied) {
        this.isOccupied = occupied;
    }

    
    public boolean isTimeSlotAvailable(LocalTime requestedStart, LocalTime requestedEnd) {
        for (Booking booking : bookings) {
            boolean isBeforeExistingBooking = requestedEnd.isBefore(booking.getStartTime()) || requestedEnd.equals(booking.getStartTime());
            boolean isAfterExistingBooking = requestedStart.isAfter(booking.getEndTime()) || requestedStart.equals(booking.getEndTime());
            
            if (!(isBeforeExistingBooking || isAfterExistingBooking)) {
                return false; // Time slot overlaps with an existing booking
            }
        }
        return true;
        
    }

    public void bookRoom(LocalTime startTime, LocalTime endTime, User user) {
        setBooked(true);
        Booking newBooking = new Booking(startTime, endTime, user);
        bookings.add(newBooking);
    }

    public void cancelBooking(LocalTime startTime, LocalTime endTime, User user) {
        if(bookings.size()!=0){
            for (Booking booking : bookings) {
                if (booking.getStartTime().equals(startTime) && booking.getEndTime().equals(endTime)) {
                    if(booking.getUser().equals(user)){
                        bookings.remove(booking);
                        System.out.println("Booking for " + roomNum + " at "+ startTime+" to "+endTime+" is cancelled successfully.");
                        System.out.println();
                        if(bookings.size()==0){
                            setBooked(false);
                        }
                        return;
                    }
                    else{
                        System.out.println("You have not booked during the specified time.");
                        System.out.println();
                        return;
                    }
                    
                }
            }
            System.out.println(roomNum + " is not booked from "+startTime+" to "+endTime+". Cannot cancel booking.");
            System.out.println();
            return;
        }
        System.out.println(roomNum + " is not booked. Cannot cancel booking.");
        System.out.println();
        
    }
    public void cancelBooking() {
        if(bookings.size()!=0){
            bookings.clear();
            System.out.println("Booking for "+ roomNum+" cancelled successfully.");
            System.out.println();
        }
        else{
            System.out.println(roomNum + " is not booked. Cannot cancel booking.");
            System.out.println();
        }
        
    }
    public void showBooking(){
        if(bookings.size()!=0){
            System.out.println("All Bookings for "+roomNum+ " :");
            bookings.sort(Comparator.comparing(Booking::getStartTime));
            for(Booking booking:bookings){
                System.out.println("Start Time :"+booking.getStartTime()+"  End Time :"+booking.getEndTime());
            }
        System.out.println();
        }
        else{
            System.out.println(roomNum+" is not booked.");
            System.out.println();
        }
    }
    public void roomStatus(){
        if(isOccupied==true){
            System.out.println(roomNum+" is now occupied.");
            System.out.println();
        }
        else{
            if(isBooked==false){
                System.out.println(roomNum+ " is not booked.");
                System.out.println();
                return;
            }
            System.out.println(roomNum+" is now unoccupied.");
            System.out.println();
        }
    }
}