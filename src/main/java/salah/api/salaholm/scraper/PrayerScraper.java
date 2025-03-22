package salah.api.salaholm.scraper;

import lombok.RequiredArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Component;
import salah.api.salaholm.config.Constants;

import java.time.Duration;
import java.util.List;

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

    public void scrapeDataFromIslamiskaForbundet() {
        chromeWebDriver.get(Constants.ISLAMISKA_FORBUNDET_URL);
        
        webDriverTimeout(500);
        
        closeIslamiskaAppPopup();

        List<WebElement> monthDropdownSelector = chromeWebDriver.findElements(By.cssSelector(Constants.ISLAMISKA_MONTH_DROPDOWN));

        for (int month = 0; month <= 12; month++) {
            int finalMonth = month;
            new WebDriverWait(chromeWebDriver, Duration.ofSeconds(1))
                    .ignoring(StaleElementReferenceException.class)
                    .until((WebDriver driver) -> {
                        monthDropdownSelector.get(finalMonth).click();
                        webDriverTimeout(600);

                        //scrape data
                        return true;
                    });
        }
    }

    private void closeIslamiskaAppPopup() {
        chromeWebDriver.findElement(By.className(Constants.ISLAMISKA_POPUP_CLASS)).click();
    }

    private void webDriverTimeout(int milliseconds) {
        chromeWebDriver.manage().timeouts().implicitlyWait(Duration.ofMillis(milliseconds));
    }
}
