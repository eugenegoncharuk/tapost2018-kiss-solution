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
public class UserPersonalAreaPage extends Page {

    @FindBy(xpath = "//div[@id='content']//li")
    private List<WebElement> personalAreaSubMenu;

    public UserPersonalAreaPage(RemoteWebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    protected String getPage() {
        return Pages.PERSONAL_AREA_PAGE;
    }

    public void waitToBeLoaded() {
        PageUtils.waitUntilVisible(By.xpath("//h2"));
    }




}
