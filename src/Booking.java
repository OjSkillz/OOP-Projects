import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Bookings {
    private Guest guest;
    private Room room;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private double payment;
    private final double FESTIVE_PERIOD_SURCHARGE = 0.2;

    public Bookings(Guest guest, Room room) {
        this.guest = guest;
        this.room = room;
        room.bookARoom();

    }

    public void setCheckInDate(String checkInDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.checkInDate = LocalDate.parse(checkInDate, formatter);
    }
    public String getCheckInDate() {
        return String.valueOf(checkInDate);
    }

    public void calculatePayment(String roomType, int numberOfNights, String festivePeriod) {
            if (room.getRoomType().equalsIgnoreCase(roomType) && festivePeriod.equalsIgnoreCase("yes")) {
                payment = ( room.getPricePerNight() + (room.getPricePerNight() * FESTIVE_PERIOD_SURCHARGE)) * numberOfNights;
            }
            else if (festivePeriod.equalsIgnoreCase("no")) payment = room.getPricePerNight() * numberOfNights;
    }

    public String getCheckOutDate(int numberOfNights) {
       checkOutDate = checkInDate.plusDays(numberOfNights);
       return String.valueOf(checkOutDate);
    }

    public double getPayment() {
        return payment;
    }

    public void cancelBooking(String bookingReference) {
        if (room.getBookingReference().equals(bookingReference)) {
            room.cancelBooking();
            System.out.println("\033[1mReservation Canceled Successfully.\033[0m");
            System.out.printf("Room %d is now available", room.getRoomNumber());
        }

    }
}
