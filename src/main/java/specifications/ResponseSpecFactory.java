package specifications;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;

public class ResponseSpecFactory {
    private static ResponseSpecification responseSpecification;
    private ResponseSpecFactory() {

    }
    public static ResponseSpecification getResponseSpecification() {
        if (responseSpecification == null) {
            responseSpecification = new ResponseSpecBuilder().
                    expectContentType(ContentType.JSON).
                    build();
        }
        return responseSpecification;
    }
}
