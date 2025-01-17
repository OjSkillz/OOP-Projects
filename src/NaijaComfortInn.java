import java.util.ArrayList;
import java.util.List;

public class HotelSystem {
    private List<Admin> admins;
    private List<Booking> bookings;

    public HotelSystem() {
        admins = new ArrayList<>();
        bookings = new ArrayList<>();
    }

    public void addAdmin(Admin admin) {
        admins.add(admin);
    }

    public void addBooking(Booking booking) {
        bookings.add(booking);
    }

    public void displayBookings(String adminId, String adminPassword) {
        if(isAdmin(adminId, adminPassword)) {
            for (Booking booking : bookings) {
                System.out.println(booking.toString());
            }
        }
    }

    public boolean isAdmin(String adminId, String adminPassword) {
        for (Admin admin : admins) {
            if (adminId.equals(admin.getAdminId()) && adminPassword.equals(admin.getAdminPassword())) {
                return true;
            }
        } return false;
    }
}

