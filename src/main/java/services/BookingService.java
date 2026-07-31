package services;
import auth.TokenManager;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pojo.BookingRequest;
import pojo.BookingResponse;
import routes.Routes;
import specifications.RequestSpecFactory;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class BookingService extends BaseService {
    private static final Logger logger =
            LogManager.getLogger(BookingService.class);

    public BookingResponse createBooking(BookingRequest bookingRequest) {

        Response response = given()
                .spec(RequestSpecFactory.getRequestSpecification())
                .body(bookingRequest)
                .log().all()
                .when()
                .post(Routes.BOOKING);
logger.info("booking request created");
        System.out.println("STATUS => " + response.statusCode());
        System.out.println("BODY => " + response.asString());

        // Validate HTTP Status before trying to parse the JSON
        if (response.getStatusCode() == 200) {
            return response.as(BookingResponse.class);
        } else {
            throw new IllegalStateException(
                    "API call failed with status " + response.getStatusCode() + ": " + response.asString()
            );
        }
    }

    public BookingRequest getBookingRequest(int bookingid) {
        Response response1 = given().spec(RequestSpecFactory.getRequestSpecification())
                .pathParam("id", bookingid).log().all()
                .when().get(Routes.GET_BOOKING);
        logger.info("booking request getting");

        if (response1.statusCode() == 200) {
            return response1.as(BookingRequest.class);
        } else {
            throw new IllegalStateException(
                    "Get Booking failed with status "
                            + response1.statusCode()
            );
        }
    }
    public BookingRequest updateBooking(int BookingId,BookingRequest bookingRequest) {
        Response response=given().spec(RequestSpecFactory.getRequestSpecification())
                .pathParam("id",BookingId)
                .header("Cookie","token="+ TokenManager.getToken())
                .body(bookingRequest)
                .log().all()
                .when()
                .put(Routes.UPDATE_BOOKING);
        logger.info("booking request updating");
        if (response.statusCode() == 200) {
            return response.as(BookingRequest.class);
        } else {
            throw new IllegalStateException(
                    "Get Booking failed with status "
                            + response.statusCode()
            );
        }
    }
    public  BookingRequest patchBooking(int BookingId, Map<String,Object> Payload){
        Response response=given().spec(RequestSpecFactory.getRequestSpecification())
                .pathParam("id",BookingId)
                .header("Cookie","token="+TokenManager.getToken())
                .body(Payload).log().all().when().patch(Routes.UPDATE_BOOKING);
        logger.info("booking request patching");
        if (response.statusCode() == 200) {
            return response.as(BookingRequest.class);
        }
        else {
            throw new IllegalStateException(
                    "Patch Booking failed with status "
                            + response.statusCode()
                            + " Response : "
                            + response.asString()
            );
    }
}
public Response deleteBooking(int BookingId){
        Response response=given().spec(RequestSpecFactory.getRequestSpecification())
                .header("Cookie","token="+TokenManager.getToken())
                .pathParam("id",BookingId).log().all().when().delete(Routes.DELETE_BOOKING);
    logger.info("booking request deleting");
        System.out.println("STATUS => " + response.statusCode());
    System.out.println("BODY => " + response.asString());
    return  response;
}
}