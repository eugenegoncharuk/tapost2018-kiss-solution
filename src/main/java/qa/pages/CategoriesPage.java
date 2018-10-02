package qa.pages;

import core.utils.PageUtils;
import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Getter
public class CategoriesPage extends Page {

    @FindBy(xpath = "//*[@id=\"content\"]/h2")
    private WebElement categoryTitle;

    private String categoryNumStr;

    public CategoriesPage(RemoteWebDriver driver, String categoryNumStr) {
        super(driver);
        this.categoryNumStr = categoryNumStr;
        PageFactory.initElements(driver, this);
    }

    @Override
    protected String getPage() {
        return Pages.CATEGORY_PAGE + categoryNumStr;
    }

    public void waitToBeLoaded() {
        PageUtils.waitUntilVisible(categoryTitle);
    }

}
