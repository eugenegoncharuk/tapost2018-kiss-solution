package qa.pages;

import core.utils.PageUtils;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

@Getter
public class UserRegistrationPage extends Page {

    @FindBy(xpath = "//input[@id='input-firstname']")
    private WebElement firstNameInput;

    @FindBy(xpath = "//input[@id='input-lastname']")
    private WebElement lastNameInput;

    @FindBy(xpath = "//input[@id='input-email']")
    private WebElement emailInput;

    @FindBy(xpath = "//input[@id='input-telephone']")
    private WebElement phoneInput;

    @FindBy(xpath = "//input[@id='input-password']")
    private WebElement passwordInput;

    @FindBy(xpath = "//input[@id='input-confirm']")
    private WebElement confirmPasswordInput;

    @FindBy(xpath = "//input[@name='agree']")
    private WebElement agreePolicyCheckbox;

    @FindBy(xpath = "//input[@value='Continue']")
    private WebElement continueButton;

    @FindBy(xpath = "//p")
    private List<WebElement> resultMessages;

    public UserRegistrationPage(RemoteWebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    protected String getPage() {
        return Pages.REGISTER_PAGE;
    }

    public void waitToBeLoaded() {
        PageUtils.waitUntilVisible(By.xpath("//h1[text()='Account']"));
    }
}
