import builders.ProductDataBuilder;
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

}
