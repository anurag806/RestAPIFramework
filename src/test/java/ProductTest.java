import builders.ProductDataBuilder;
import dataproviders.ProductDataProvider;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojo.ProductRequest;
import pojo.ProductResponse;
import services.ProductService;
import utils.AssertionHelper;
import java.util.List;

public class ProductTest extends BaseTest {

    @Test
    public void getAllProduct() {

        ProductService productService = new ProductService();

        List<ProductResponse> products =
                productService.getAllProducts();

        AssertionHelper.verifyNotNull(
                products,
                "Products list should not be null");

        AssertionHelper.verifyFalse(
                products.isEmpty(),
                "Products list should not be empty");

        AssertionHelper.verifyNotNull(
                products.get(0).getTitle(),
                "Product title should not be null");
    }

    @Test(dataProvider = "productIds",
            dataProviderClass = ProductDataProvider.class)
    public void getProductById(int productId) {

        ProductService productService = new ProductService();

        ProductResponse product =
                productService.getProductById(productId);

        AssertionHelper.verifyEquals(
                product.getId(),
                productId,
                "Product ID mismatch");

        AssertionHelper.verifyNotNull(
                product.getTitle(),
                "Title should not be null");

        AssertionHelper.verifyNotNull(
                product.getCategory(),
                "Category should not be null");
    }

    @Test
    public void addProduct() {

        ProductService productService = new ProductService();

        ProductRequest request =
                ProductDataBuilder.defaultProduct();

        ProductResponse response =
                productService.createProduct(request);

        AssertionHelper.verifyTrue(
                response.getId() > 0,
                "Product ID should be greater than 0");

        AssertionHelper.verifyProduct(
                response,
                request);
    }

    @Test
    public void updateProductTest() {

        ProductService productService = new ProductService();

        ProductRequest request =
                ProductDataBuilder.updatedProduct();

        ProductResponse response =
                productService.updateProduct(1, request);

        AssertionHelper.verifyProduct(
                response,
                request);
    }

    @Test
    public void deleteProductTest() {

        ProductService productService =
                new ProductService();

        Response response =
                productService.deleteProduct(1);

        AssertionHelper.verifyStatusCode(
                response.statusCode(),
                200);
    }
}