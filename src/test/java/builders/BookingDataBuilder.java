package builders;

import net.datafaker.Faker;
import pojo.BookingDates;
import pojo.BookingRequest;

import java.util.HashMap;
import java.util.Map;

public class BookingDataBuilder {

    public static BookingRequest defaultBooking(){

        BookingDates bookingDates = new BookingDates(
                "2026-12-10",
                "2026-12-12"
        );
        BookingRequest bookingRequest = new BookingRequest();
        bookingRequest.setFirstname("Anurag");
        bookingRequest.setLastname("pandey");
        bookingRequest.setTotalprice(5000);
        bookingRequest.setDepositpaid(true);
        bookingRequest.setAdditionalneeds("i am winner");
        bookingRequest.setBookingdates(bookingDates);
        return bookingRequest;
    }
    public static BookingRequest updatedBooking() {

        BookingDates bookingDates = new BookingDates(
                "2026-12-20",
                "2026-12-25"
        );

        BookingRequest bookingRequest = new BookingRequest();

        bookingRequest.setFirstname("Rahul");
        bookingRequest.setLastname("Sharma");
        bookingRequest.setTotalprice(5000);
        bookingRequest.setDepositpaid(false);
        bookingRequest.setBookingdates(bookingDates);
        bookingRequest.setAdditionalneeds("Lunch");

        return bookingRequest;
    }
    public  static Map<String,Object> patchBookingPayload(){
        Map<String,Object> payload = new HashMap<>();
        payload.put("firstname","swati");
        payload.put("lastname","shukla");
        payload.put("totalprice",5000);
        return payload;
    }
}
