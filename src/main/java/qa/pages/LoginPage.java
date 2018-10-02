package qa.pages;

import core.utils.PageUtils;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Getter
public class LoginPage extends Page {

    @FindBy(xpath = "//h2[text()='Returning Customer']")
    private WebElement headerLogin;

    public LoginPage(RemoteWebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    protected Pages getPage() {
        return Pages.LOGIN_PAGE;
    }

    public void waitToBeLoaded() {
        PageUtils.waitUntilVisible(By.xpath("//h2[text()='Returning Customer']"));
    }

}
