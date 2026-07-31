import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import services.BookingService;

public class BaseTest {
    protected BookingService bookingService;
    @BeforeMethod
    public void setup(){
        BookingService bookingService = new BookingService();
    }
}
