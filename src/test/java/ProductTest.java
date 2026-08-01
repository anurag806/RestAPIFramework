import builders.ProductDataBuilder;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojo.ProductRequest;
import pojo.ProductResponse;
import services.ProductService;

import java.util.List;

public class ProductTest extends BaseTest {
    @Test
    public void getAllProduct() {
        ProductService productService = new ProductService();

        List<ProductResponse> products =
                productService.getAllProducts();

        System.out.println(products.size());

        System.out.println(products.get(0).getTitle());
    }
    @Test
    public void getProductById() {
        ProductService productService = new ProductService();
        ProductResponse product = productService.getProductById(1);
        System.out.println(product.getTitle());
    }
@Test
public void addProduct() {
    ProductService productService = new ProductService();
    ProductRequest productRequest = ProductDataBuilder.defaultProduct();

    ProductResponse response = productService.createProduct(productRequest);

    Assert.assertTrue(response.getId() > 0);
    Assert.assertEquals(response.getTitle(), productRequest.getTitle());
    Assert.assertEquals(response.getPrice(), productRequest.getPrice());
    Assert.assertEquals(response.getCategory(), productRequest.getCategory());
    Assert.assertEquals(response.getDescription(), productRequest.getDescription());
    Assert.assertEquals(response.getImage(), productRequest.getImage());

}

    @Test
    public void updateProductTest() {

        ProductService productService = new ProductService();

        ProductRequest request = ProductDataBuilder.updatedProduct();

        ProductResponse response =
                productService.updateProduct(1, request);

        Assert.assertEquals(response.getTitle(), request.getTitle());
        Assert.assertEquals(response.getPrice(), request.getPrice());
        Assert.assertEquals(response.getCategory(), request.getCategory());
        Assert.assertEquals(response.getDescription(), request.getDescription());
        Assert.assertEquals(response.getImage(), request.getImage());
    }
    @Test
    public void deleteProductTest() {

        ProductService productService = new ProductService();

        Response response = productService.deleteProduct(1);

        Assert.assertEquals(response.statusCode(), 200);
    }
}
