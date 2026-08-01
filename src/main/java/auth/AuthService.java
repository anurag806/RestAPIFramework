package auth;

import config.ConfigManager;
import io.restassured.response.Response;
import pojo.AuthRequest;
import pojo.AuthResponse;
import routes.Routes;
import services.BaseService;
import specifications.RequestSpecFactory;

import static io.restassured.RestAssured.given;

public class AuthService extends BaseService {

    public AuthService() {
        requestSpecification = RequestSpecFactory.getRequestSpecification(
                ConfigManager.getProperty("booker.base.url"));
    }

    public AuthResponse generateToken() {

        // Create authentication request payload
        AuthRequest authRequest = new AuthRequest(
                ConfigManager.getProperty("booker.username"),
                ConfigManager.getProperty("booker.password")
        );

        // Send POST request to /auth
        response = given()
                .spec(requestSpecification)
                .body(authRequest)
                .when()
                .post(Routes.AUTH);

        response.then().log().all();

        // Convert JSON response to AuthResponse POJO
        return response.as(AuthResponse.class);
    }
}