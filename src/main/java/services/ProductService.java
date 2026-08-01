package services;
import config.ConfigManager;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pojo.ProductRequest;
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
    public ProductResponse getProductById(int id)
    {
        Response response=given().spec(requestSpecification).pathParam("id",id)
                .log().all()
                .when().get(Routes.PRODUCT_BY_ID);
        if (response.statusCode() == 200) {
            return response.as(ProductResponse.class);
        }
        else  {
            throw new IllegalStateException( response.statusCode() + " Response : ");
        }
    }
    public ProductResponse createProduct(ProductRequest productRequest)
    {

        Response response=given().spec(requestSpecification).body(productRequest).when().post(Routes.PRODUCTS);
        logger.info("Creating Product");
        if (response.statusCode() == 200 || response.statusCode() == 201) {
            return response.as(ProductResponse.class);
        }
        else  {
            throw new IllegalStateException(
                    "Create Product failed with status "
                            + response.statusCode()
                            + " Response : "
                            + response.asString()
            );
        }
    }
    public ProductResponse updateProduct(int productId, ProductRequest productRequest) {

        Response response = given()
                .spec(requestSpecification)
                .pathParam("id", productId)
                .body(productRequest)
                .log().all()
                .when()
                .put(Routes.PRODUCT_BY_ID);

        logger.info("Updating Product with id : {}", productId);

        if (response.statusCode() == 200) {

            return response.as(ProductResponse.class);

        } else {

            throw new IllegalStateException(
                    "Update Product failed with status "
                            + response.statusCode()
                            + " Response : "
                            + response.asString()
            );
        }
    }
}
