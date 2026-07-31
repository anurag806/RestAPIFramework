import builders.BookingDataBuilder;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojo.BookingRequest;
import pojo.BookingResponse;
import services.BookingService;
import utils.AssertionHelper;
import utils.TestData;
import java.util.Map;

public class CreateBookingTest extends  BaseTest {



    @Test
    public void createBookingTest() {

        BookingRequest requestPayload =
                BookingDataBuilder.defaultBooking();

        BookingResponse bookingResponse =
                bookingService.createBooking(requestPayload);

        AssertionHelper.verifyTrue(
                bookingResponse.getBookingid() > 0,
                "Booking Id was not generated");

        TestData.setBookingId(
                bookingResponse.getBookingid());

        // Save expected request for next tests
        TestData.setBooking(requestPayload);

        System.out.println("Booking Created -> "
                + bookingResponse.getBookingid());
    }

    @Test(dependsOnMethods = "createBookingTest")
    public void getBookingTest() {

        int bookingId = TestData.getBookingId();

        BookingRequest actualBooking =
                bookingService.getBookingRequest(bookingId);

        BookingRequest expectedBooking =
                TestData.getBooking();

        AssertionHelper.verifyBooking(
                actualBooking,
                expectedBooking);

        System.out.println("Booking Fetched Successfully -> "
                + bookingId);
    }

    @Test(dependsOnMethods = "getBookingTest")
    public void updateBookingTest() {

        int bookingId = TestData.getBookingId();

        BookingRequest updatedRequest =
                BookingDataBuilder.updatedBooking();

        BookingRequest updatedResponse =
                bookingService.updateBooking(
                        bookingId,
                        updatedRequest);

        AssertionHelper.verifyBooking(
                updatedResponse,
                updatedRequest);

        // Update expected object
        TestData.setBooking(updatedRequest);

        BookingRequest getResponse =
                bookingService.getBookingRequest(
                        bookingId);

        AssertionHelper.verifyBooking(
                getResponse,
                updatedRequest);

        System.out.println("Booking Updated Successfully -> "
                + bookingId);
    }

    @Test(dependsOnMethods = "updateBookingTest")
    public void patchBookingTest() {

        int bookingId = TestData.getBookingId();

        Map<String, Object> payload =
                BookingDataBuilder.patchBookingPayload();

        BookingRequest patchResponse =
                bookingService.patchBooking(
                        bookingId,
                        payload);

        AssertionHelper.verifyEquals(
                patchResponse.getFirstname(),
                payload.get("firstname"),
                "Firstname mismatch");

        AssertionHelper.verifyEquals(
                patchResponse.getLastname(),
                payload.get("lastname"),
                "Lastname mismatch");

        AssertionHelper.verifyEquals(
                patchResponse.getTotalprice(),
                payload.get("totalprice"),
                "Totalprice mismatch");

        BookingRequest getResponse =
                bookingService.getBookingRequest(
                        bookingId);

        AssertionHelper.verifyEquals(
                getResponse.getFirstname(),
                payload.get("firstname"),
                "Firstname mismatch");

        AssertionHelper.verifyEquals(
                getResponse.getLastname(),
                payload.get("lastname"),
                "Lastname mismatch");

        AssertionHelper.verifyEquals(
                getResponse.getTotalprice(),
                payload.get("totalprice"),
                "Totalprice mismatch");

        // Verify unchanged fields
        BookingRequest expectedBooking =
                TestData.getBooking();

        AssertionHelper.verifyEquals(
                getResponse.isDepositpaid(),
                expectedBooking.isDepositpaid(),
                "DepositPaid mismatch");

        AssertionHelper.verifyEquals(
                getResponse.getBookingdates().getCheckin(),
                expectedBooking.getBookingdates().getCheckin(),
                "Checkin mismatch");

        AssertionHelper.verifyEquals(
                getResponse.getBookingdates().getCheckout(),
                expectedBooking.getBookingdates().getCheckout(),
                "Checkout mismatch");

        AssertionHelper.verifyEquals(
                getResponse.getAdditionalneeds(),
                expectedBooking.getAdditionalneeds(),
                "AdditionalNeeds mismatch");

        // Update expected object
        expectedBooking.setFirstname(
                (String) payload.get("firstname"));
        expectedBooking.setLastname(
                (String) payload.get("lastname"));
        expectedBooking.setTotalprice(
                (Integer) payload.get("totalprice"));

        TestData.setBooking(expectedBooking);

        System.out.println("Booking Patched Successfully -> "
                + bookingId);
    }

    @Test(dependsOnMethods = "patchBookingTest")
    public void deleteBookingTest() {

        int bookingId = TestData.getBookingId();

        Response response =
                bookingService.deleteBooking(
                        bookingId);

        AssertionHelper.verifyStatusCode(
                response.statusCode(),
                201);

        try {

            bookingService.getBookingRequest(
                    bookingId);

            AssertionHelper.fail(
                    "Booking still exists after delete.");

        } catch (IllegalStateException e) {

            System.out.println(
                    "Booking deleted successfully.");
        }
    }
}