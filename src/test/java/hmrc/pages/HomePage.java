package hmrc.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import hmrc.driver.BasePage;

public class HomePage extends BasePage {

    @FindBy(css = "#block_top_menu > ul > li:nth-child(2) > a")
    private WebElement menu_Dresses;

    public void clickOnDressTab() {
         menu_Dresses.click();
    }

}
