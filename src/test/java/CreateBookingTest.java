import org.testng.Assert;
import org.testng.annotations.Test;
import pojo.BookingDates;
import pojo.BookingRequest;
import pojo.BookingResponse;
import services.BookingService;
import utils.TestData;

import java.util.HashMap;
import java.util.Map;

public class CreateBookingTest {

    BookingService bookingService = new BookingService();

    @Test
    public void createBookingTest() {

        BookingDates dates = new BookingDates(
                "2026-12-10",
                "2026-12-12"
        );

        BookingRequest requestPayload = new BookingRequest(
                "Anurag",
                "Pandey",
                1200,
                true,
                dates,
                "Breakfast"
        );

        BookingResponse bookingResponse =
                bookingService.createBooking(requestPayload);

        int bookingId = bookingResponse.getBookingid();

        Assert.assertTrue(bookingId > 0,
                "Booking ID was not generated");

        TestData.setBookingId(bookingId);

        System.out.println("Booking Id -> " + bookingId);
    }

    @Test(dependsOnMethods = "createBookingTest")
    public void getBookingTest() {

        int bookingId = TestData.getBookingId();

        BookingRequest response =
                bookingService.getBookingRequest(bookingId);

        Assert.assertEquals(response.getFirstname(), "Anurag");
        Assert.assertEquals(response.getLastname(), "Pandey");
        Assert.assertEquals(response.getTotalprice(), 1200);
        Assert.assertTrue(response.isDepositpaid());
        Assert.assertEquals(response.getBookingdates().getCheckin(), "2026-12-10");
        Assert.assertEquals(response.getBookingdates().getCheckout(), "2026-12-12");
        Assert.assertEquals(response.getAdditionalneeds(), "Breakfast");
    }

    @Test(dependsOnMethods = "getBookingTest")
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

        Assert.assertEquals(updatedResponse.getFirstname(), "Rahul");
        Assert.assertEquals(updatedResponse.getLastname(), "Sharma");
        Assert.assertEquals(updatedResponse.getTotalprice(), 5000);
        Assert.assertFalse(updatedResponse.isDepositpaid());
        Assert.assertEquals(updatedResponse.getBookingdates().getCheckin(), "2026-12-20");
        Assert.assertEquals(updatedResponse.getBookingdates().getCheckout(), "2026-12-25");
        Assert.assertEquals(updatedResponse.getAdditionalneeds(), "Lunch");

        BookingRequest getResponse =
                bookingService.getBookingRequest(bookingId);

        Assert.assertEquals(getResponse.getFirstname(), "Rahul");
        Assert.assertEquals(getResponse.getLastname(), "Sharma");
        Assert.assertEquals(getResponse.getTotalprice(), 5000);
        Assert.assertFalse(getResponse.isDepositpaid());
        Assert.assertEquals(getResponse.getAdditionalneeds(), "Lunch");

        System.out.println("Booking Updated Successfully -> " + bookingId);
    }

    @Test(dependsOnMethods = "updateBookingTest")
    public void patchBookingTest() {

        int bookingId = TestData.getBookingId();

        Map<String, Object> payload = new HashMap<>();

        payload.put("firstname", "Swati");
        payload.put("lastname", "Shukla");
        payload.put("totalprice", 7000);

        BookingRequest patchResponse =
                bookingService.patchBooking(bookingId, payload);

        Assert.assertEquals(patchResponse.getFirstname(), "Swati");
        Assert.assertEquals(patchResponse.getLastname(), "Shukla");
        Assert.assertEquals(patchResponse.getTotalprice(), 7000);

        BookingRequest getResponse =
                bookingService.getBookingRequest(bookingId);

        Assert.assertEquals(getResponse.getFirstname(), "Swati");
        Assert.assertEquals(getResponse.getLastname(), "Shukla");
        Assert.assertEquals(getResponse.getTotalprice(), 7000);

        // Fields not patched should remain unchanged
        Assert.assertFalse(getResponse.isDepositpaid());
        Assert.assertEquals(getResponse.getBookingdates().getCheckin(), "2026-12-20");
        Assert.assertEquals(getResponse.getBookingdates().getCheckout(), "2026-12-25");
        Assert.assertEquals(getResponse.getAdditionalneeds(), "Lunch");

        System.out.println("Booking Patched Successfully -> " + bookingId);
    }
}