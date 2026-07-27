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

    public AuthResponse generateToken() {

        // Create authentication request payload
        AuthRequest authRequest = new AuthRequest(
                ConfigManager.getProperty("username"),
                ConfigManager.getProperty("password")
        );

        // Send POST request to /auth
        response = given()
                .spec(RequestSpecFactory.getRequestSpecification())
                .body(authRequest)
                .when()
                .post(Routes.AUTH);

        // Convert JSON response to AuthResponse POJO
        return response.as(AuthResponse.class);
    }
}