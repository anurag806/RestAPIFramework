package specifications;

import config.ConfigManager;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class  RequestSpecFactory {
    private static RequestSpecification requestSpecification;
    private RequestSpecFactory() {

}
public static  RequestSpecification getRequestSpecification() {
    if (requestSpecification == null) {
        requestSpecification= new RequestSpecBuilder()
                .setBaseUri(ConfigManager.getProperty("base.url"))
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .build();
    }
    return requestSpecification;
}

}
