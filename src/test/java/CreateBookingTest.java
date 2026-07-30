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
    @Test(dependsOnMethods = "GetBookingTest")
    public void updateBookingTest() {

        int bookingId = TestData.getBookingId();

        BookingDates updatedDates = new BookingDates(
                "2026-12-20",
                "2026-12-25"
        );

        BookingRequest updatedRequest = new BookingRequest(
                "Rahul",
                "Sharma",
                5000,
                false,
                updatedDates,
                "Lunch"
        );

        BookingRequest updatedResponse =
                bookingService.updateBooking(bookingId, updatedRequest);

        // Validate PUT Response
        Assert.assertEquals(updatedResponse.getFirstname(), "Rahul");
        Assert.assertEquals(updatedResponse.getLastname(), "Sharma");
        Assert.assertEquals(updatedResponse.getTotalprice(), 5000);
        Assert.assertFalse(updatedResponse.isDepositpaid());
        Assert.assertEquals(updatedResponse.getBookingdates().getCheckin(), "2026-12-20");
        Assert.assertEquals(updatedResponse.getBookingdates().getCheckout(), "2026-12-25");
        Assert.assertEquals(updatedResponse.getAdditionalneeds(), "Lunch");

        // Verify using GET API
        BookingRequest getResponse = bookingService.getBookingRequest(bookingId);

        Assert.assertEquals(getResponse.getFirstname(), "Rahul");
        Assert.assertEquals(getResponse.getLastname(), "Sharma");
        Assert.assertEquals(getResponse.getTotalprice(), 5000);
        Assert.assertFalse(getResponse.isDepositpaid());
        Assert.assertEquals(getResponse.getBookingdates().getCheckin(), "2026-12-20");
        Assert.assertEquals(getResponse.getBookingdates().getCheckout(), "2026-12-25");
        Assert.assertEquals(getResponse.getAdditionalneeds(), "Lunch");

        System.out.println("Booking Updated Successfully -> " + bookingId);
    }

}