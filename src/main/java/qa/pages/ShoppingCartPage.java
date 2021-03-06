package qa.pages;

import core.utils.PageUtils;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Getter
public class ShoppingCartPage extends Page {

    @FindBy(xpath = "//h1")
    private WebElement title;

    @FindBy(xpath = "//div[@id='cart']")
    private WebElement shoppingCart;

    @FindBy(xpath = "//div[contains(@class,'btn-block')]//input[@class='form-control']")
    private WebElement cartQuantityInputField;

    @FindBy(xpath = "(//button[contains(@class, 'btn-primary')])[1]")
    private WebElement cartUpdateInputButton;

    @FindBy(xpath = "//h4[@class='panel-title']//a[contains(text(),'Gift')]")
    private WebElement giftVoucher;

    @FindBy(xpath = "//input[@id='input-voucher']")
    private WebElement giftVoucherInputField;

    @FindBy(xpath = "//input[@id='button-voucher']")
    private WebElement giftVoucherSubmitButton;

    @FindBy(xpath = "//div[contains(@class,'alert-dismissible')]")
    private WebElement warningMessage;

    @FindBy(xpath = "//a[@class='btn btn-primary' and contains(text(), 'Checkout')]")
    private WebElement checkoutButton;

    @FindBy(xpath = "//div[contains(@class,'col-sm-4')]//tbody//tr[last()]//td[last()]")
    private WebElement productPriceWithDiscount;

    public ShoppingCartPage(RemoteWebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void waitToBeLoaded() {
        PageUtils.waitUntilVisible(By.xpath("//h1"));
    }

    @Override
    protected String getPage() {
        return Pages.SHOPPING_CART_PAGE;
    }

}
