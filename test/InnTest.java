import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HotelTest {

@Test
    public void testThatNCIWorks() {
        Hotel nci = new Hotel();
        nci.setName("Francis Olumba");
        assertEquals("Francis Olumba", nci.getName());

    }

    @Test
    public void testThatNCICanBookARoom() {
        Hotel nci = new Hotel();
        nci.bookARoom("Francis Olumba", "09067041100", "soojo06@gmail.com", "Single", 3);
        String expected = "Name: Francis Olumba" + "\n" + "Phone Number: 09067041100" + "\n" + "Email: soojo06@gmail.com" + "\n";
        assertEquals(expected, nci.getGuestDetails());
    }

}