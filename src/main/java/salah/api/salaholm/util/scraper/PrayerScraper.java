package salah.api.salaholm.util.scraper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Component;
import salah.api.salaholm.entity.calendar.PrayerCalendar;
import salah.api.salaholm.entity.location.Location;
import salah.api.salaholm.entity.prayer.Prayers;
import salah.api.salaholm.exception.LocationNotFoundException;
import salah.api.salaholm.mapper.PrayerMapper;
import salah.api.salaholm.util.Constants;
import salah.api.salaholm.util.RetryWait;
import salah.api.salaholm.util.parser.LocationProvider;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static salah.api.salaholm.util.Constants.ISLAMISKA_CONNECTION_URL;
import static salah.api.salaholm.util.Constants.ISLAMISKA_PRAYERS_TABLE;

@Component
@Slf4j
@RequiredArgsConstructor
public class PrayerScraper {
    private final ChromeDriver chromeWebDriver;
    private final RetryWait retryWait;
    private final PrayerMapper prayerMapper;
    private final LocationProvider locationProvider;

    public Location getAnnualPrayersByLocation(String fromCity) {
        connectToIslamiskaForbundetSite();

        List<WebElement> cityOptionsList = getIslamiskaMonthsList(Constants.ISLAMISKA_CITIES_OPTIONS);
        Optional<Location> location = Optional.empty();

        for (int i = 0; i < cityOptionsList.size(); i++) {
            cityOptionsList = getIslamiskaMonthsList(Constants.ISLAMISKA_CITIES_OPTIONS);
            WebElement city = cityOptionsList.get(i);
            String currentCityName = city.getText();

            if (fromCity.equalsIgnoreCase(currentCityName)) {
                city.click();
                location = getAnnualPrayers(currentCityName);
                break;
            }
        }

        return location.orElseThrow(() -> new LocationNotFoundException(
                        String.format("Location was not found with %s inside getLocationDTO", fromCity)
        ));
    }


    private Optional<Location> getAnnualPrayers(String city) {
        connectToIslamiskaForbundetSite();
        Location location = locationProvider.prepareLocationBuilder(city);
        List<PrayerCalendar> prayerCalendars = new ArrayList<>();
        Prayers prayers = new Prayers();

        for (int i = 0; i < 12; i++) {
            List<WebElement> months = getIslamiskaMonthsList(Constants.ISLAMISKA_MONTH_OPTIONS);
            WebElement month = months.get(i);

            String monthName = month.getText();
            log.info("{} of city {}", monthName, city);

            month.click();

            List<PrayerCalendar> prayerRows = getPrayerTable()
                    .stream()
                    .map(element -> {
                        Prayers p = prayerMapper.toPrayers(element, city, monthName);
                        p.setLocation(location);
                        return p;
                    })
                    .toList();

        }
        prayers.setLocation(location);
        location.setPrayers(prayers);


        log.info("Prayers Scraped From {} ", ISLAMISKA_CONNECTION_URL);

        return Optional.of(location);
    }

    private List<WebElement> getIslamiskaMonthsList(String options) {
        By optionBy = By.cssSelector(options);
        return retryWait.retryForLoop(optionBy);
    }

    private List<String> getPrayerTable() {
        By prayerTableSelector = By.cssSelector(ISLAMISKA_PRAYERS_TABLE);

        new WebDriverWait(chromeWebDriver, Duration.ofSeconds(3))
                .until(ExpectedConditions.presenceOfElementLocated(prayerTableSelector));

        return retryWait.retry(prayerTableSelector);
    }

    private void connectToIslamiskaForbundetSite() {
        chromeWebDriver.get(ISLAMISKA_CONNECTION_URL);
    }

}
