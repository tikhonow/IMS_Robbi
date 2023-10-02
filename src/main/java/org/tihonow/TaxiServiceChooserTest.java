package org.tihonow;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaxiServiceChooserTest {

    @Test
    public void testFirstCase() throws Exception {
        List<String> taxiServices = Arrays.asList(
                "Yandex Taxi, 3-8, 330-350, 25-27, any",
                "Uber, 7-10, 270-350, 20-26, card",
                "Taksovichkoff, 15-17, 100-110, 48-50, cash"
        );

        List<String> result = TaxiServiceChooser.findSuitableServices(taxiServices, 5, 331, 25, "cash");
        assertEquals(Arrays.asList("Yandex Taxi"), result);  // Should return an empty list
    }

    @Test
    public void testSecondCase() throws Exception {
        List<String> taxiServices = Arrays.asList(
                "Yandex Taxi, 3-8, 330-350, 25-27, any",
                "Uber, 7-10, 270-350, 20-26, card",
                "Taksovichkoff, 15-17, 100-110, 48-50, cash"
        );

        List<String> result = TaxiServiceChooser.findSuitableServices(taxiServices, 8, 340, 25, "card");
        assertEquals(Arrays.asList("Yandex Taxi", "Uber"), result);
    }

    @Test
    public void testThirdCase() throws Exception {
        List<String> taxiServices = Arrays.asList(
                "Yandex Taxi, 3-8, 330-350, 25-27, any",
                "Uber, 7-10, 270-350, 20-26, card",
                "Taksovichkoff, 15-17, 100-110, 48-50, cash"
        );

        List<String> result = TaxiServiceChooser.findSuitableServices(taxiServices, 3, 100, 20, "any");
        assertEquals(Arrays.asList(), result);  // Should return an empty list
    }
}