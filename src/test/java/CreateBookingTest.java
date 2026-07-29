import org.testng.annotations.Test;
import pojo.BookingDates;
import pojo.BookingRequest;
import pojo.BookingResponse;
import services.BookingService;

public class CreateBookingTest {

    @Test
    public void createBookingTest() {
        BookingService bookingService = new BookingService();

        BookingDates dates = new BookingDates("2026-12-10", "2026-12-12");
        BookingRequest requestPayload = new BookingRequest(
                "Anurag",
                "Pandey",
                1200,
                true,
                dates,
                "Breakfast"
        );

        BookingResponse bookingResponse = bookingService.createBooking(requestPayload);
        System.out.println("Created Booking ID: " + bookingResponse.getBookingid());
    }
}