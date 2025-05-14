package salah.api.salaholm.scraper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.springframework.stereotype.Component;
import salah.api.salaholm.mapper.PrayerMapper;
import salah.api.salaholm.model.Prayer;
import salah.api.salaholm.repository.PrayerRepository;

import java.time.Duration;
import java.util.List;

import static salah.api.salaholm.config.Constants.*;

@Component
@Slf4j
@RequiredArgsConstructor
public class PrayerScraper {
    private final ChromeDriver chromeWebDriver;
    private final PrayerRepository prayerRepository;
    private final FluentWait<ChromeDriver> waitForPrayersToLoad;
    private final PrayerMapper prayerMapper;

    public void getPrayerTimesFromIslamiska() {
        chromeWebDriver.get(ISLAMISKA_CONNECTION_URL);
        List<WebElement> cityOptionsList = getOptionsList(ISLAMISKA_CITIES_OPTIONS);

        for (WebElement city: cityOptionsList) {
            city.click();

            String cityName = city.getText();
            getMonthlyCityPrayerFromIslamiska(cityName);
        }

        log.info("Prayers Scraped From {} ", ISLAMISKA_CONNECTION_URL);
    }

    private void getMonthlyCityPrayerFromIslamiska(String city) {
        List<WebElement> monthOptionsList = getOptionsList(ISLAMISKA_MONTH_OPTIONS);

        for (WebElement month: monthOptionsList) {
            String monthName = month.getText();
            log.info("{} of city {}", monthName, city);

            String previousTable = getPrayerTableInContext();

            month.click();
            waitForPrayersToLoad.until(d -> !previousTable.equals(getPrayerTableInContext()));
            waitForPrayersToLoad();

            List<Prayer> prayerRows = chromeWebDriver
                    .findElements(By.cssSelector(ISLAMISKA_PRAYERS_TABLE))
                    .stream()
                    .map(element -> prayerMapper.toPrayer(element, city, monthName))
                    .toList();

            prayerRepository.saveAll(prayerRows);
        }
    }

    private String getPrayerTableInContext() {
        WebElement prayerTable = waitForPrayersToLoad.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(ISLAMISKA_PRAYERS_TABLE)));
        return prayerTable.getText();
    }
    private List<WebElement> getOptionsList(String option) {
        return chromeWebDriver.findElements(By.cssSelector(option));
    }
    private void waitForPrayersToLoad() {
        chromeWebDriver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
    }

}
