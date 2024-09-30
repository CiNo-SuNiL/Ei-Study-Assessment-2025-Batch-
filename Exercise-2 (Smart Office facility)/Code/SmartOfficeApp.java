import java.util.Scanner;
import java.time.LocalTime;


public class SmartOfficeApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        OfficeConfig office = OfficeConfig.getInstance();
        AuthService authService = new AuthService();
        int UserIsLoggedIn=0;

        while (true) {
            System.out.println("******************************");
            System.out.println("  Welcome to Smart Office App  ");
            System.out.println("******************************");
            System.out.println("'Help'- Command can be used for listing all the availabe commands");
            System.out.println();
            System.out.println("Login or Signup");
            System.out.print("Enter Command :");
            String logorsign=scanner.nextLine();
            if(logorsign.equals("Login")){
                System.out.println();
                System.out.println("Please login:");
                System.out.print("Username: ");
                String username = scanner.nextLine();
                System.out.print("Password: ");
                String password = scanner.nextLine();

                authService.login(username, password);
                UserIsLoggedIn=1;
            }
            else if(logorsign.equals("Signup")){
                System.out.println();
                System.out.print("Enter new username: ");
                String newusername = scanner.nextLine();
                System.out.print("Enter new Password: ");
                String newpassword = scanner.nextLine();
                System.out.print("Enter new Password: ");
                String repassword = scanner.nextLine();
                if(newpassword.equals(repassword)){
                    if(authService.checkUser(newusername)){
                        System.out.println(newusername+" already exist.");
                        System.out.println();
                    }
                    else{
                        authService.addUser(newusername, newpassword);
                        System.out.println("User added succesfully.");
                        System.out.println();
                        continue;
                    }
                }
                else{
                    System.out.println("Password doesn't match.");
                    continue;
                }
            }
            

        while (UserIsLoggedIn==1) {
            System.out.print("Enter command: ");
            String command = scanner.nextLine();
            String[] commandParts = command.split(" ");

            try {
                switch (commandParts[0]) {
                    case "Config":

                        if (authService.isAdmin()) {
                        if (commandParts[1].equals("room") && commandParts[2].equals("count")) {
                            int numRooms = Integer.parseInt(commandParts[3]);
                            office.configureRooms(numRooms);
                        } else if (commandParts[1].equals("room") && commandParts[2].equals("max") && commandParts[3].equals("capacity")) {
                            int roomNum = Integer.parseInt(commandParts[4]);
                            int capacity = Integer.parseInt(commandParts[5]);
                            if(capacity>0){
                                ConferenceRoom room = office.getRoom(roomNum);
                                room.setMaxCapacity(capacity);
                                System.out.println("Room " + roomNum + " maximum capacity set to " + capacity + ".");
                                System.out.println();
                            }
                            else{
                                System.out.println("Invalid capacity. Please enter a valid positive number.");
                                System.out.println();
                            }
                            
                        }
                        else{
                            System.out.println("Invalid command.");
                        }
                    }
                    else{
                        System.out.println("Access Denied: Only Admins can configure rooms.");
                        System.out.println();
                    }
                        break;

                    case "Add":
                        if (commandParts[1].equals("occupant")) {
                            int roomNum = Integer.parseInt(commandParts[2]);
                            int occupants = Integer.parseInt(commandParts[3]);
                            ConferenceRoom occRoom = office.getRoom(roomNum);
                            if (occRoom != null) {
                                occRoom.setOccupancy(occupants);
                            }
                        }
                        break;

                    case "Block":
                        try{
                            if (commandParts[1].equals("room")) {
                                int roomNum = Integer.parseInt(commandParts[2]);
                                String s = commandParts[3];
                                int duration = Integer.parseInt(commandParts[4]);

                                LocalTime startTime = LocalTime.parse(s);
                                LocalTime endTime = startTime.plusMinutes(duration);

                                ConferenceRoom roomToBook = office.getRoom(roomNum);

                                if (roomToBook != null) {
                                    if (endTime.isBefore(startTime)) {
                                        System.out.println("Time duration cannot be negative");
                                        System.out.println();
                                    } else if (roomToBook.isTimeSlotAvailable(startTime,endTime)) {
                                        User loginUser=authService.getLoggedInUser();
                                        Command bookCommand = new BookRoomCommand(roomToBook, startTime,duration, endTime,loginUser);
                                        CommandInvoker invoker = new CommandInvoker();
                                        invoker.setCommand(bookCommand);
                                        invoker.executeCommand();
                                    } 
                                    else {
                                        System.out.println("Room " + roomNum + " is already booked during this time. Cannot book.");
                                        System.out.println();
                                    }
                                }
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid room number. Please enter a valid room number.");
                            System.out.println();
                        }
                        
                        break;

                    case "Cancel":
                        if (commandParts[1].equals("room")) {
                            int roomNum = Integer.parseInt(commandParts[2]);
                            
                            if(commandParts.length==3){
                                if (authService.isAdmin()){
                                    ConferenceRoom roomToCancel = office.getRoom(roomNum);
                                    if (roomToCancel != null) {
                                        Command cancelCommand = new CancelAllBookings(roomToCancel);
                                        CommandInvoker invoker = new CommandInvoker();
                                        invoker.setCommand(cancelCommand);
                                        invoker.executeCommand();
                                    }
                                }
                                else{
                                    System.out.println("Only Admin can cancel all bookings.");
                                    System.out.println();
                                }
                            }
                            else if (commandParts.length==5){
                                String s = commandParts[3];
                                int duration = Integer.parseInt(commandParts[4]);
                                LocalTime startTime = LocalTime.parse(s);
                                LocalTime endTime = startTime.plusMinutes(duration);
                                ConferenceRoom roomToCancel = office.getRoom(roomNum);
                                if (roomToCancel != null) {
                                    User loginUser=authService.getLoggedInUser();
                                    Command cancelCommand = new CancelRoomCommand(roomToCancel, startTime, endTime, loginUser);
                                    CommandInvoker invoker = new CommandInvoker();
                                    invoker.setCommand(cancelCommand);
                                    invoker.executeCommand();
                                }
                            }
                            else{
                                System.out.println("Invalid Command.");
                                System.out.println();
                            } 
                        }
                        break;

                    case "Help":
                        System.out.println("Available Commands:");
                        System.out.println("──────────────────────────────");
                        System.out.println("  1. Config room count x");
                        System.out.println("      - Where 'x' specifies the number of meeting rooms.");
                        System.out.println();
                        System.out.println("  2. Config room max capacity x y");
                        System.out.println("      - Where 'x' is the room number, and 'y' is the max capacity of the room.");
                        System.out.println();
                        System.out.println("  3. Add occupant x y");
                        System.out.println("      - Where 'x' is the room number, and 'y' is the number of persons.");
                        System.out.println();
                        System.out.println("  4. Block room x y z");
                        System.out.println("      - Where 'x' is the room number.");
                        System.out.println("      - 'y' is the time in 'HH:MM' format.");
                        System.out.println("      - 'z' is the duration for booking (in minutes).");
                        System.out.println();
                        System.out.println("  5. Cancel room x");
                        System.out.println("      - Where 'x' is the room number.");
                        System.out.println();
                        System.out.println("  6. Room status x");
                        System.out.println("      - Where 'x' is the room number.");
                        System.out.println();
                        System.out.println("  7. Show booking x");
                        System.out.println("      - Where 'x' is the room number.");
                        System.out.println();
                        System.out.println("  8. Exit");
                        System.out.println("      - For exiting the application.");
                        System.out.println();
                        System.out.println("******************************");
                        System.out.println();
                        break;
                    
                    case "Room":
                        if (commandParts[1].equals("status")) {
                            int roomNum = Integer.parseInt(commandParts[2]);
                            ConferenceRoom roomcheck=office.getRoom(roomNum);
                            if(roomcheck!=null){
                                roomcheck.roomStatus();
                            }

                        }
                        else{
                            System.out.println("Invalid Command.");
                            System.out.println();
                        }
                        break;
                    
                    case "Booking":
                        if (commandParts[1].equals("room")) {
                            int roomNum = Integer.parseInt(commandParts[2]);
                            ConferenceRoom roomBookings = office.getRoom(roomNum);
                            if (roomBookings != null) {
                                Command showCommand = new ShowRoomBookings(roomBookings);
                                CommandInvoker invoker = new CommandInvoker();
                                invoker.setCommand(showCommand);
                                invoker.executeCommand();
                            }
                        }
                        else{
                            System.out.println("Invalid Command.");
                        }
                        break;

                    case "Logout":
                        authService.logout();
                        UserIsLoggedIn=0;
                        break;
                        

                    case "Exit":
                        System.out.println("Exiting Smart Office...");
                        scanner.close();
                        System.exit(0);
                        break;

                    default:
                        System.out.println("Invalid command. Please try again.");
                        System.out.println();
                        break;
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
}
