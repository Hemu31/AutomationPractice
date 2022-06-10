package hmrc.pages;

import hmrc.driver.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DressesPage extends BasePage {

    @FindBy(css = "div.right-block")
    private List<WebElement> items;

    @FindBy(css = "div.left-block")
    private List<WebElement> images;

    @FindBy(xpath = "//a[@title='Add to cart']")
    private List<WebElement> addToCarts;

    @FindBy(xpath = "//a[@title='Proceed to checkout']")
    WebElement proceedToCheckOut;

    public void addCostlyItemToCart() {
        int loop = 0;
        String maxPrice = getMaxPrice();
        for (WebElement item : items) {
            String temp = item.getText().split("\\R")[1].replace("$", "");
            if (temp.contains(maxPrice)) {
                Actions action = new Actions(driver);
                action.moveToElement(images.get(loop));

                JavascriptExecutor executor = (JavascriptExecutor) driver;
                executor.executeScript("arguments[0].click();", addToCarts.get(loop));
                break;
            }
            loop++;
        }
    }

    public String getMaxPrice() {
        List<Double> prices = new ArrayList<>();
        for (WebElement item : items) {
            String price = item.getText().split("\\R")[1].replace("$", "");
            if (price.contains("%")) {
                price = price.split(" ")[0];
            }
            prices.add(Double.parseDouble(price));
        }

//        Collections.sort(prices);
//        System.out.println("Lowest: "+ prices.get(0));
//        Collections.reverse(prices);
//        System.out.println("Highest: "+ prices.get(0));

        return String.valueOf(prices.stream().max(Double::compare).get());
    }

    public void clickOnProceedToCheckout() {
        waitForWebElement(proceedToCheckOut);

        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", proceedToCheckOut);
    }
}
