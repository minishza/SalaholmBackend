package salah.api.salaholm.scraper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import salah.api.salaholm.entity.Prayer;
import salah.api.salaholm.mapper.PrayerMapper;
import salah.api.salaholm.repository.PrayerRepository;
import salah.api.salaholm.util.Constants;
import salah.api.salaholm.util.RetryWait;

import java.time.Duration;
import java.util.List;

import static salah.api.salaholm.util.Constants.*;

@Component
@Slf4j
@RequiredArgsConstructor
public class PrayerScraper {
    private final ChromeDriver chromeWebDriver;
    private final PrayerRepository prayerRepository;
    private final RetryWait retryWait;
    private final PrayerMapper prayerMapper;

    private void getCityPrayerTimesFromIslamiska(String fromCity) {
        connectToIslamiskaForbundetSite();
        List<WebElement> cityOptionsList = getIslamiskaMonthsList(Constants.ISLAMISKA_CITIES_OPTIONS);
        for (WebElement city: cityOptionsList) {
            city.click();

            String currentCityName = city.getText();
            getIslamiskaAnnualPrayers(currentCityName);

            if (fromCity.equalsIgnoreCase(currentCityName)) {
                log.info("Last city scraped");
                break;
            }
        }

    }



    @Async
    public void getIslamiskaAnnualPrayers(String city) {
        connectToIslamiskaForbundetSite();

        for (int i = 0; i < 12; i++) {
            WebElement month = getIslamiskaMonthsList(Constants.ISLAMISKA_MONTH_OPTIONS).get(i);

            String monthName = month.getText();
            log.info("{} of city {}", monthName, city);

            getIslamiskaMonthsList(Constants.ISLAMISKA_MONTH_OPTIONS).get(i).click();

            List<Prayer> prayerRows = getPrayerTable()
                    .stream()
                    .map(element -> {
                        Prayer p = prayerMapper.toPrayer(element, city, monthName);
                        if (p == null) throw new IllegalStateException("Null prayer generated"); //add custom exception
                        return p;
                    })
                    .toList();

            prayerRepository.saveAll(prayerRows);
        }

        log.info("Prayers Scraped From {} ", ISLAMISKA_CONNECTION_URL);
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
