package salah.api.salaholm.mapper;

import org.openqa.selenium.WebElement;
import salah.api.salaholm.model.Prayer;

public interface PrayerMapperInterface {
    Prayer toPrayer(WebElement webElement, String city, String month);
}