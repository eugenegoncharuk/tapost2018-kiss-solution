package qa.pages;

import core.utils.PageUtils;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Getter
public class CategoriesPage extends Page {

    @FindBy(xpath = "//*[@id=\"content\"]/h2")
    private WebElement categoryTitle;

    @FindBy(xpath = "//*[@id=\"input-limit\"]")
    private WebElement inputLimitDropDown;

    @FindBy(xpath = "//select[@class='product-thumb']")
    private WebElement products;

    private String categoryNumStr;

    private String DROPDOWN_SELECTOR_LOCATOR = "//select[@class='form-control']//option[text()='%s']";

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

    public void selectFromDropdown(String limit) {
        PageUtils.getElementWhenVisible(By.xpath(String.format(DROPDOWN_SELECTOR_LOCATOR, limit))).click();
    }

    public int getProductElementsCount() {
        return driver.findElements(By.xpath("//select[@class='product-thumb']")).size();
    }
}
