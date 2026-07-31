import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import services.BookingService;

public class BaseTest {
    protected BookingService bookingService;
    @BeforeMethod(alwaysRun = true)
    public void setup(){
        bookingService = new BookingService();
    }
}
