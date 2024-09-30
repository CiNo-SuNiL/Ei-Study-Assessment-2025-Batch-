import java.util.ArrayList;
import java.util.List;

public class OfficeConfig {
    private static OfficeConfig instance;
    private List<ConferenceRoom> rooms;

    private OfficeConfig() {
        rooms = new ArrayList<>();
    }

    public static OfficeConfig getInstance() {
        if (instance == null) {
            instance = new OfficeConfig();
        }
        return instance;
    }

    public void configureRooms(int numRooms) {
        System.out.print("Office configured with " + numRooms + " meeting rooms: ");
        for (int i = 1; i <= numRooms; i++) {
            rooms.add(new ConferenceRoom("Room " + i));
            System.out.print("Room "+i);
            if(i==numRooms){
                System.out.println(".");
            }
            else{
                System.out.print(", ");
            }
        }
        System.out.println();
        
    }

    public ConferenceRoom getRoom(int roomId) {
        if (roomId > 0 && roomId <= rooms.size()) {
            return rooms.get(roomId - 1);
        }
        System.out.println("Room "+roomId+" does not exist.");
        System.out.println();
        return null;
    }

    public List<ConferenceRoom> getRooms() {
        return rooms;
    }
}
