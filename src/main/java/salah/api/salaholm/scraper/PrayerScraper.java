package salah.api.salaholm.scraper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.springframework.stereotype.Component;
import salah.api.salaholm.model.Prayer;
import salah.api.salaholm.repository.PrayerRepository;

import java.time.Duration;
import java.time.LocalDate;
import java.time.Year;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import static salah.api.salaholm.config.Constants.*;

@Component
@Slf4j
@RequiredArgsConstructor
public class PrayerScraper {
    private final ChromeDriver chromeWebDriver;
    private final PrayerRepository prayerRepository;
    private final FluentWait<ChromeDriver> waitForPrayersToLoad;

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

            List<Prayer> prayerRows = chromeWebDriver.findElements(By.cssSelector(ISLAMISKA_PRAYERS_TABLE))
                    .stream()
                    .map(element -> {
                                String[] prayerData = element.getText().split(" ");
                                return Prayer.builder()

                                        .build();
                    }
                    ).toList();
        }
    }

    private Prayer mapElementToPrayer(WebElement monthlyPrayerElement, String city) {
        String[] prayerData = parseWebElement(monthlyPrayerElement);
        LocalDate prayerDay = toDate(prayerData, monthlyPrayerElement);

        return Prayer.builder()

                .city(city)
                .build();
    }

    private LocalDate toDate(String[] data, WebElement monthlyPrayerElement) {
        int date = Integer.parseInt(data[0]);
        String monthName = monthlyPrayerElement.getText();
        LocalDate prayerDay = LocalDate.of(Year.now().getValue(), toMonth(monthName), date);

        prayerDay.getDayOfWeek().getDisplayName(TextStyle.FULL, new Locale("en"));

        return prayerDay;
    }
    private int toMonth(String monthName) {
        Map<String, Integer> swedishMonthMap = Map.ofEntries(
                Map.entry("januari", 1),
                Map.entry("februari", 2),
                Map.entry("mars", 3),
                Map.entry("april", 4),
                Map.entry("maj", 5),
                Map.entry("juni", 6),
                Map.entry("juli", 7),
                Map.entry("augusti", 8),
                Map.entry("september", 9),
                Map.entry("oktober", 10),
                Map.entry("november", 11),
                Map.entry("december", 12)
        );

        return swedishMonthMap.get(monthName.toLowerCase());
    }
    private String[] parseWebElement(WebElement prayerElement) {
        return prayerElement.getText().split(" ");
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
