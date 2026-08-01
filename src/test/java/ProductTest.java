import org.testng.annotations.Test;
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

}
