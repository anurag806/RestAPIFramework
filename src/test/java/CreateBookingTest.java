import org.testng.Assert;
import org.testng.annotations.Test;
import pojo.BookingDates;
import pojo.BookingRequest;
import pojo.BookingResponse;
import services.BookingService;
import utils.TestData;




public class CreateBookingTest {
    BookingService bookingService = new BookingService();
    @Test
    public void createBookingTest() {
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
        int bookid=bookingResponse.getBookingid();
        Assert.assertTrue(
                bookid > 0,
                "Booking ID was not generated"
        );
        TestData.setBookingId(bookid);
        System.out.println("bookingid->"+bookid);
    }
    @Test(dependsOnMethods ="createBookingTest" )
    public void GetBookingTest(){
        int bookingid=TestData.getBookingId();
        BookingRequest response =
                bookingService.getBookingRequest(bookingid);
        Assert.assertEquals(response.getFirstname(), "Anurag");
        Assert.assertEquals(response.getLastname(), "Pandey");
        Assert.assertEquals(response.getTotalprice(), 1200);
        Assert.assertTrue(response.isDepositpaid());
        Assert.assertEquals(response.getAdditionalneeds(), "Breakfast");
    }
}