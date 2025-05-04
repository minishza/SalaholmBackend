package salah.api.salaholm.scraper;

import lombok.AllArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.springframework.stereotype.Component;
import salah.api.salaholm.config.Constants;
import salah.api.salaholm.model.Prayer;
import salah.api.salaholm.repository.PrayerRepository;

import java.time.Duration;
import java.time.Year;
import java.util.List;
import java.util.NoSuchElementException;

@Component
@AllArgsConstructor
public class PrayerScraper {
    private final ChromeDriver chromeWebDriver;

    private final PrayerRepository prayerRepository;


    public void scrapePrayerTimesFromIslamiskaSite() {
        chromeWebDriver.get(Constants.ISLAMISKA_URL);
        FluentWait<ChromeDriver> fluentWait = new FluentWait<>(chromeWebDriver)
                .withTimeout(Duration.ofSeconds(1))
                .pollingEvery(Duration.ofMillis(250))
                .ignoring(StaleElementReferenceException.class)
                .ignoring(NoSuchElementException.class);

        List<WebElement> cityDropdown = chromeWebDriver.findElements(By.cssSelector(Constants.ISLAMISKA_CITIES_DROPDOWN));

        for (int i = 0; i < cityDropdown.size(); i++) {

            int finalI = i;
            fluentWait.until(d -> {
                cityDropdown.get(finalI).click();

                return chromeWebDriver.findElements(By.id("ifis_bonetider"));
            });

            String city = cityDropdown.get(i).getText();
            scrapeMonthlyIslamiska(fluentWait, city);
        }

    }

    private void scrapeMonthlyIslamiska(FluentWait<ChromeDriver> prayerWait, String city) {
        By prayerTableSelector = By.cssSelector("tbody tr");

        List<WebElement> monthSelector = chromeWebDriver.findElements(By.cssSelector(Constants.ISLAMISKA_MONTH_DROPDOWN));

        for (int month = 1; month <= 12; month++) {
            String previousTable = chromeWebDriver.findElement(prayerTableSelector).getText();
            monthSelector.get(month - 1).click();

            prayerWait.until(d -> {
                String currentTable = chromeWebDriver.findElement(prayerTableSelector).getText();
                return !previousTable.equals(currentTable);
            });

            String monthName = monthSelector.get(month - 1).getText();
            List<Prayer> prayerRows = chromeWebDriver.findElements(By.cssSelector("tbody tr"))
                    .stream()
                    .map(element -> {
                        String[] prayerData = element.getText().split(" ");
                        return Prayer.builder()
                                .hijri("WIP")
                                .georgian(monthName + prayerData[0] + Year.now())
                                .fajr(prayerData[1])
                                .sunrise(prayerData[2])
                                .zuhr(prayerData[3])
                                .asr(prayerData[4])
                                .maghrib(prayerData[5])
                                .isha(prayerData[6])
                                .city(city)
                                .build();
                    }
                    ).toList();

            prayerRepository.saveAll(prayerRows);
        }
    }


}
