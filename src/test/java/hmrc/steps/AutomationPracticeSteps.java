package hmrc.steps;

import hmrc.driver.BasePage;
import hmrc.pages.DressesPage;
import hmrc.pages.OrderSummary;
import io.cucumber.java8.En;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import hmrc.pages.HomePage;
public class AutomationPracticeSteps implements En {

    private Logger log = LoggerFactory.getLogger(AutomationPracticeSteps.class);

    HomePage homePage = new HomePage();
    DressesPage dressesPage = new DressesPage();
    OrderSummary orderSummary = new OrderSummary();

    String maxPrice = "";
    public AutomationPracticeSteps() {

        Given("I open the automation practice site", () -> {
            homePage.openUrl(BasePage.getProperty("baseUrl"));
        });

        Then("I assert the page title to be {string}", (String title) -> {
            Assert.assertEquals("Page name not matched", title, homePage.getPageTitle());
        });

        When("I click on the Dresses tab", () -> {
            homePage.clickOnDressTab();
        });

        Then("I add costly dress to cart", () -> {
            maxPrice = dressesPage.getMaxPrice();
            System.out.println("Max Item Price: "+maxPrice);
            dressesPage.addCostlyItemToCart();
        });

        And("I click on proceed to checkout", () -> {
            dressesPage.clickOnProceedToCheckout();
        });

        Then("I validate the price in the order summary page", () -> {
            Assert.assertEquals("Fail: Wrong item added to cart", maxPrice, orderSummary.getTheItemPrice());
        });
    }

}
