package retailers.fenwick;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import retailers.Retailers;

import java.util.List;

public class Fenwick implements Retailers {
    private WebDriver driver;

    public Fenwick(String url) {
        this.driver = new ChromeDriver();
        driver.get(url);

    }

    public boolean isAvailable(int size) {
        String title;
        List<WebElement> sizeElements = this.driver.findElements(By.className("b-pdp_swatches-link"));

        for (WebElement singleSize : sizeElements) {
            title = singleSize.getAttribute("title");

            if (title.contains(String.valueOf(size)) && (!title.contains("not available"))) return true;
        }
        return false;
    }

    public void closeWebDriver() {
        driver.quit();
    }

    public Double findPrice() {
        WebElement price = this.driver.findElement(By.className("js-item-price"));
        return Double.parseDouble(price.getText().substring(1));
    }
}
