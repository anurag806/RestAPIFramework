package specifications;
import config.ConfigManager;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class RequestSpecFactory {

    private RequestSpecFactory() {}

    public static RequestSpecification getRequestSpecification() {
        return new RequestSpecBuilder()
                .setBaseUri(ConfigManager.getProperty("base.url"))
                .setContentType(ContentType.JSON)
                // Explicit clean Accept header to bypass bot/Cloudflare checks
                .addHeader("Accept", "application/json")
                .build();
    }
}