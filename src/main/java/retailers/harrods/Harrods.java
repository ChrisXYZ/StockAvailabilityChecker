package retailers.harrods;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import retailers.Retailers;

import java.util.List;


public class Harrods implements Retailers {
    private WebDriver driver;

    public Harrods(String url) {
        this.driver = new ChromeDriver();
        driver.get(url);
    }

    public boolean isAvailable(int size) {
        String text;
        WebElement sizeElements = driver.findElement(By.id("size"));
        Select select = new Select(sizeElements);
        for (WebElement selectionChoice : select.getOptions()) {
            text = selectionChoice.getText();
            if (text.contains(String.valueOf(size))) return true;
        }
        return false;
    }

    public void closeWebDriver() {
        driver.quit();
    }

    public Double findPrice() {
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            System.out.println("Interrupted Exception when doing a thread.sleep in harrods" + e);
        }
        List<WebElement> priceElements = driver.findElements(By.className("css-ytumd6"));

        for(WebElement singlePriceElement : priceElements){
            if(singlePriceElement.getText().contains("£")) return Double.parseDouble(singlePriceElement.getText().substring(1));
        }
        return -1.0;
    }
}
