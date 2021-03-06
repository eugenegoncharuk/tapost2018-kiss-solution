package qa.pages;

import core.utils.PageUtils;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Getter
public class UserLoginPage extends Page {

    @FindBy(xpath = "//h2[text()='Returning Customer']")
    private WebElement headerLogin;

    @FindBy(xpath = "//input[@id='input-email']")
    private WebElement emailInput;

    @FindBy(xpath = "//input[@id='input-password']")
    private WebElement passwordInput;

    @FindBy(xpath = "//input[@value='Login']")
    private WebElement loginButton;

    @FindBy(xpath = "//a[text()='Edit Account']")
    private WebElement editAccount;

    @FindBy(xpath = "//input[@id='input-firstname']")
    private WebElement firstNameInput;

    @FindBy(xpath = "//input[@id='input-lastname']")
    private WebElement lastNameInput;

    public UserLoginPage(RemoteWebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    protected String getPage() {
        return Pages.LOGIN_PAGE;
    }

    public void waitToBeLoaded() {
        PageUtils.waitUntilVisible(By.xpath("//h2[text()='Returning Customer']"));
    }

}
