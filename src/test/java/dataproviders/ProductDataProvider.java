package dataproviders;

import org.testng.annotations.DataProvider;

public class ProductDataProvider {
    @DataProvider (name = "productIds")
    public Object[][] productIds() {
        return new Object[][] {
                {1},
                {2},
        };
    }
}
