package utils;

import org.testng.Assert;
import pojo.BookingRequest;
import pojo.ProductRequest;
import pojo.ProductResponse;

public class AssertionHelper {

    public static void verifyBooking(BookingRequest actual,
                                     BookingRequest expected) {

        Assert.assertEquals(actual.getFirstname(),
                expected.getFirstname(),
                "Firstname mismatch");

        Assert.assertEquals(actual.getLastname(),
                expected.getLastname(),
                "Lastname mismatch");

        Assert.assertEquals(actual.getTotalprice(),
                expected.getTotalprice(),
                "Total Price mismatch");

        Assert.assertEquals(actual.isDepositpaid(),
                expected.isDepositpaid(),
                "Deposit Paid mismatch");

        Assert.assertEquals(
                actual.getBookingdates().getCheckin(),
                expected.getBookingdates().getCheckin(),
                "Check-in mismatch");

        Assert.assertEquals(
                actual.getBookingdates().getCheckout(),
                expected.getBookingdates().getCheckout(),
                "Check-out mismatch");

        Assert.assertEquals(
                actual.getAdditionalneeds(),
                expected.getAdditionalneeds(),
                "Additional Needs mismatch");
    }

    public static void verifyEquals(Object actual,
                                    Object expected,
                                    String message) {
        Assert.assertEquals(actual, expected, message);
    }

    public static void verifyTrue(boolean condition,
                                  String message) {
        Assert.assertTrue(condition, message);
    }

    public static void verifyFalse(boolean condition,
                                   String message) {
        Assert.assertFalse(condition, message);
    }

    public static void verifyStatusCode(int actual,
                                        int expected) {
        Assert.assertEquals(actual, expected,
                "Status Code mismatch");
    }

    public static void fail(String message) {
        Assert.fail(message);
    }
    public static void verifyProduct(ProductResponse actual, ProductRequest expected) {
        Assert.assertEquals(actual.getTitle(),expected.getTitle());
        Assert.assertEquals(actual.getDescription(),expected.getDescription());
        Assert.assertEquals(actual.getCategory(),expected.getCategory());
        Assert.assertEquals(actual.getPrice(),expected.getPrice());
        Assert.assertEquals(actual.getImage(),expected.getImage());

    }
    public  static void verifyNotNull(Object actual, String message) {
        Assert.assertNotNull(actual, message);
    }
}