package qa.pages;

import core.utils.PageUtils;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

@Getter
public class SearchPage extends Page {

    @FindBy(xpath = "//input[@id='input-search']")
    private WebElement searchInputField;

    @FindBy(xpath = "//select[@class='form-control']")
    private WebElement categoryDropdown;

    @FindBy(xpath = "//input[@name='sub_category']")
    private WebElement subCategoryCheckbox;

    @FindBy(xpath = "//input[@id='description']")
    private WebElement descriptionCheckbox;

    @FindBy(xpath = "//input[@id='button-search']")
    private WebElement searchButton;

    @FindBy(xpath = "//p[contains(text(),'cart is empty')]")
    private WebElement emptySearchResultMessage;

    @FindBy(xpath = "//div[contains(@class, 'product-layout')]")
    private List<WebElement> searchResults;

    private String DROPDOWN_SELECTOR_LOCATOR = "//select[@class='form-control']//option[text()='%s']";

    public SearchPage(RemoteWebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void selectFromDropdown(String category) {
        PageUtils.getElementWhenVisible(By.xpath(String.format(DROPDOWN_SELECTOR_LOCATOR, category))).click();
    }

    public int getSearchResultsCount() {
        List<WebElement> result = getSearchResults();
        return result.size();
    }

    public void sendKeys(String input) {
        Actions actions = new Actions(getDriver());
        actions.sendKeys(input).perform();
    }

    public void waitToBeLoaded() {
        PageUtils.waitUntilVisible(By.xpath("//h1"));
    }

    @Override
    protected Pages getPage() {
        return Pages.SEARCH_PAGE;
    }

}
