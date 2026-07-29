import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class directBookingTest {
    @Test
    public void directBookingTest(){

        given()
                .baseUri("https://restful-booker.herokuapp.com")
                .contentType(ContentType.JSON)
                .body("""
            {
              "firstname":"Anurag",
              "lastname":"Pandey",
              "totalprice":1200,
              "depositpaid":true,
              "bookingdates":{
                 "checkin":"2026-12-10",
                 "checkout":"2026-12-12"
              },
              "additionalneeds":"Breakfast"
            }
            """)
                .when()
                .post("/booking")
                .then()
                .log().all();
    }
}
