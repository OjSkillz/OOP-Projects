import java.util.ArrayList;
import java.util.List;

public class Hotel {
    private String name;
    private List<Room> rooms;

    public Hotel(String name) {
        this.name = name;
        rooms = new ArrayList<>();
    }

    public void addRooms(int numberOfRooms) {
        int threshold = 101 + numberOfRooms;
        for (int roomNumber = 101; roomNumber < threshold; roomNumber++) {
            String roomType = "Single";
            if (roomNumber > (threshold/4) && roomNumber <= threshold - (threshold/4)) {
                roomType = "Double";
            }
            else if (roomNumber > threshold - (threshold/4)) roomType = "Suite";
            double pricePerNight = roomType.equals("Single") ? 2500.00 : roomType.equals("Double") ? 3000.00 : 10000.00;
            Room room = new Room(roomType, roomNumber, pricePerNight);
            rooms.add(room);
        }
    }

    public Room findAvailableRooms(String roomType) {
        for (Room room: rooms) {
            if (room.isAvailable() && room.getRoomType().equals(roomType)) {
                return room;
            }
        } return null;
    }

    public void displayAvailableRooms() {
        for (Room room: rooms) {
            if (room.isAvailable())
                System.out.println(room);
        }
    }




    public String getGuestDetails() {
        return guest.getDetails();
    }

}
