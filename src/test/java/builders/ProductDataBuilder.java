package builders;
import net.datafaker.Faker;
import pojo.ProductRequest;

public class ProductDataBuilder {
    private static Faker faker = new Faker();
    static String[] categories = {
            "electronics",
            "jewelery",
            "men's clothing",
            "women's clothing"
    };


    public  static ProductRequest defaultProduct() {
        ProductRequest productRequest=new ProductRequest();
        productRequest.setTitle(faker.commerce().productName());
        productRequest.setDescription(faker.book().title());
        productRequest.setPrice(faker.number().numberBetween(1,1000));
        productRequest.setImage(faker.internet().image());
        productRequest.setCategory(
                categories[faker.random().nextInt(categories.length)]
        );
        return productRequest;
    }
    public static ProductRequest updatedProduct() {

        ProductRequest productRequest = new ProductRequest();

        productRequest.setTitle(faker.commerce().productName());
        productRequest.setDescription(faker.book().title());
        productRequest.setPrice(faker.number().numberBetween(1001, 5000));
        productRequest.setImage(faker.internet().image());

        productRequest.setCategory(
                categories[faker.random().nextInt(categories.length)]
        );

        return productRequest;
    }
}
