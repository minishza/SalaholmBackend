package salah.api.salaholm.scraper;

import lombok.RequiredArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.FluentWait;
import org.springframework.stereotype.Component;
import salah.api.salaholm.config.Constants;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

@Component
@RequiredArgsConstructor
public class PrayerScraper {
    private final ChromeDriver chromeWebDriver = new ChromeDriver(
            new ChromeOptions()
                    .addArguments("--headless")
                    .addArguments("--disable-gpu")
                    .addArguments("--disable-dev-shm-usage")
                    .addArguments("--no-sandbox")
                    .addArguments("start-maximized")
                    .addArguments("--disable-extensions")
    );

    public void scrapePrayerTimesFromIslamiskaSite() {
        chromeWebDriver.get(Constants.ISLAMISKA_URL);
        FluentWait<ChromeDriver> fluentWait = new FluentWait<>(chromeWebDriver)
                .withTimeout(Duration.ofSeconds(1))
                .pollingEvery(Duration.ofMillis(200))
                .ignoring(StaleElementReferenceException.class)
                .ignoring(NoSuchElementException.class);

        List<WebElement> monthSelector = chromeWebDriver.findElements(By.cssSelector(Constants.ISLAMISKA_CITIES_DROPDOWN));

        for (int i = 0; i < monthSelector.size(); i++) {

            int finalI = i;
            fluentWait.until(d -> {
                monthSelector.get(finalI).click();

                return chromeWebDriver.findElements(By.id("ifis_bonetider"));
            });

            System.out.println(monthSelector.get(i).getText());
            scrapeMonthlyIslamiska(fluentWait);
        }

    }

    private void scrapeMonthlyIslamiska(FluentWait<ChromeDriver> prayerWait) {
        By prayerTableSelector = By.cssSelector("tbody tr");

        List<WebElement> monthSelector = chromeWebDriver.findElements(By.cssSelector(Constants.ISLAMISKA_MONTH_DROPDOWN));

        for (int month = 1; month <= 12; month++) {
            String previousTable = chromeWebDriver.findElement(prayerTableSelector).getText();
            monthSelector.get(month - 1).click();

            prayerWait.until(d -> {
                String currentTable = chromeWebDriver.findElement(prayerTableSelector).getText();
                return !previousTable.equals(currentTable);
            });

            List<WebElement> prayerRows = chromeWebDriver.findElements(By.cssSelector("tbody tr"));

            System.out.println(month);
            prayerRows.forEach(row -> {
                System.out.println(row.getText());
            });
        }
    }


}
