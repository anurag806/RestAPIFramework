package utils;

import pojo.BookingRequest;

public class TestData {
    private static int bookingId;
    private static BookingRequest booking;
    public static int getBookingId() {
        return bookingId;
    }
    public static void setBookingId(int id){
        bookingId=id;
    }
    public static void setBooking(BookingRequest booking) {
        TestData.booking = booking;
    }

    public static BookingRequest getBooking() {
        return booking;
    }
}
