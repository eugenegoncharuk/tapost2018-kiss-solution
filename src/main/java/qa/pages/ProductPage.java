package qa.pages;

import core.utils.PageUtils;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Getter
public class ProductPage extends Page {

    private String page;

    @FindBy(xpath = "//h1")
    private WebElement title;

    @FindBy(xpath = "//div[@id='cart']")
    private WebElement shoppingCart;

    @FindBy(xpath = "//button[@id='button-cart']")
    private WebElement addToCartButton;

    @FindBy(xpath = "//input[@id='input-quantity']")
    private WebElement quantityInputField;

    @FindBy(xpath = "//div[@class='text-danger']")
    private WebElement requiredFieldMessage;

    @FindBy(xpath = "//p[@class='text-right']//strong[contains(text(),'View Cart')]")
    private WebElement viewCartLink;

    @FindBy(xpath = "//div[contains(@class,'btn-block')]//input[@class='form-control']")
    private WebElement cartQuantityInputField;

    @FindBy(xpath = "(//button[contains(@class, 'btn-primary')])[1]")
    private WebElement cartUpdateInputButton;

    public ProductPage(RemoteWebDriver driver, String page) {
        super(driver);
        PageFactory.initElements(driver, this);
        this.page = page;
    }

    public void waitToBeLoaded() {
        PageUtils.waitUntilVisible(By.xpath("//h1"));
    }

    @Override
    protected String getPage() {
        return page;
    }

}
