package hmrc.pages;

import hmrc.driver.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OrderSummary extends BasePage {

    @FindBy(css = "#cart_summary")
    private WebElement order_summary;

    public String getTheItemPrice() {
        waitForWebElement(order_summary);
        String price = order_summary.findElement(By.xpath("//tbody/tr/td[4]")).getText();
        return price.replace("$", "");
    }
}
