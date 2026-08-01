package services;

import config.ConfigManager;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pojo.ProductResponse;
import routes.Routes;
import specifications.RequestSpecFactory;


import java.util.List;

import static io.restassured.RestAssured.given;


public class ProductService  extends  BaseService{
    private  static final Logger logger= LogManager.getLogger(ProductService.class);
    public ProductService() {
        requestSpecification= RequestSpecFactory.getRequestSpecification(ConfigManager.getProperty("fakestore.base.url"));
    }

    public List<ProductResponse> getAllProducts()
    {
        Response response = given()
                .spec(requestSpecification)
                .log().all()
                .when()
                .get(Routes.PRODUCTS);

        logger.info("Fetching all products");

        if (response.statusCode() == 200) {

            return response.jsonPath().getList("", ProductResponse.class);

        } else {

            throw new IllegalStateException(
                    "Get Products failed with status "
                            + response.statusCode()
                            + " Response : "
                            + response.asString()
            );
        }
    }
}
