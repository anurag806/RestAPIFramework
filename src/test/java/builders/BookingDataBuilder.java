package builders;

import net.datafaker.Faker;
import pojo.BookingDates;
import pojo.BookingRequest;

import java.util.HashMap;
import java.util.Map;

public class BookingDataBuilder {
    private static  Faker faker = new Faker();
    public static BookingRequest defaultBooking() {

        BookingDates bookingDates = new BookingDates(
                "2026-12-10",
                "2026-12-12"
        );

        BookingRequest bookingRequest = new BookingRequest();

        bookingRequest.setFirstname(faker.name().firstName());
        bookingRequest.setLastname(faker.name().lastName());
        bookingRequest.setTotalprice(faker.number().numberBetween(1000, 10000));
        bookingRequest.setDepositpaid(faker.bool().bool());
        bookingRequest.setBookingdates(bookingDates);
        bookingRequest.setAdditionalneeds("Breakfast");

        return bookingRequest;
    }
    public static BookingRequest updatedBooking() {

        BookingDates bookingDates = new BookingDates(
                "2026-12-20",
                "2026-12-25"
        );

        BookingRequest bookingRequest = new BookingRequest();

        bookingRequest.setFirstname(faker.name().firstName());
        bookingRequest.setLastname(faker.name().lastName());
        bookingRequest.setTotalprice(faker.number().numberBetween(1,1000));
        bookingRequest.setDepositpaid(faker.bool().bool());
        bookingRequest.setBookingdates(bookingDates);
        bookingRequest.setAdditionalneeds("lunch");

        return bookingRequest;
    }
    public  static Map<String,Object> patchBookingPayload(){
        Map<String,Object> payload = new HashMap<>();
        payload.put("firstname",faker.name().firstName());
        payload.put("lastname",faker.name().lastName());
        payload.put("totalprice",faker.number().numberBetween(1,1000));
        return payload;
    }
}
