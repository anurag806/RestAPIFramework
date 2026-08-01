package routes;

public class Routes {

    private Routes(){

    }
    // Restful Booker API
    public static final String AUTH = "/auth";
    public static final String BOOKING = "/booking";
    public static final String GET_BOOKING="/booking/{id}";
    public static final String UPDATE_BOOKING = "/booking/{id}";
    public static final String DELETE_BOOKING="/booking/{id}";
    // FakeStore API
    public static final String PRODUCTS = "/products";
    public static final String PRODUCT_BY_ID = "/products/{id}";


}
