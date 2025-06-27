package salah.api.salaholm.util;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.*;

import java.util.List;

@Slf4j
public class RetryWait {
    private final WebDriver driver;

    public RetryWait(WebDriver driver) {
        this.driver = driver;
    }

    public List<WebElement> retryWhileLoop(By locator) {
        return retryWhileLoop(locator, 5);
    }

    public List<WebElement> retryWhileLoop(By locator, int timesToRetry) {
        List<WebElement> foundElement = null;
        int repeat = 0;
        while(repeat <= timesToRetry) {
            try {
                foundElement = driver.findElements(locator);
                break;
            } catch(StaleElementReferenceException exc) {
                exc.printStackTrace();
            }
            repeat++;
        }

        if (foundElement == null) {
            throw new NotFoundException("The web element was not located");
        }

        return foundElement;
    }

    public List<String> retry(By locator) {
        List<String> prayers;
        int repeat = 6;
        for (int retry = 1; retry < repeat; retry++) {
            try {
                prayers = driver.findElements(locator)
                        .stream()
                        .map(WebElement::getText)
                        .toList();
                return prayers;
            } catch (StaleElementReferenceException e) {
                log.error("Stale element reference found, STATUS : Retrying {} of {}", retry, repeat);
            }
        }

        throw new RuntimeException("Prayer table not found");
    }
}
