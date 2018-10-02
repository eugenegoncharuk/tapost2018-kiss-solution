package qa.pages;

import core.utils.PageUtils;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Getter
public class BrowseCategoriesPage extends Page {

    @FindBy(xpath = "//*[@id=\"content\"]/h2")
    private WebElement categoryTitle;

    @FindBy(xpath = "//*[@id=\"input-limit\"]")
    private WebElement inputLimitDropDown;

    @FindBy(xpath = "//select[@class='product-thumb']")
    private WebElement products;

    @FindBy(xpath = "//a[@class='list-group-item active']")
    private WebElement activeLeftMenu;

    public static final String TOP_MENU_SUBCATEGORY_SELECTOR = "//div[@class='dropdown-inner']//ul//li//a[contains(text(), '%s')]";
    @FindBy(xpath = "//div[@class='swiper-viewport']")
    private WebElement slidingViewPort;

    private static final String PAGING_DROPDOWN_SELECTOR_LOCATOR = "//select[@class='form-control']//option[text()='%s']";

    public static final String TOP_MENU_CATEGORY_SELECTOR = "//a[@class='dropdown-toggle' and contains(text(), '%s')]";

    public static final String TOP_MENU_CATEGORY_SHOW_ALL = "//li[@class='dropdown']//div[@class='dropdown-menu']//a[@class='see-all' and contains(text(), 'Show All %s')]";

    private String pageNumStr;

    public BrowseCategoriesPage(RemoteWebDriver driver, String pageNumStr) {
        super(driver);
        PageFactory.initElements(driver, this);
        this.pageNumStr = pageNumStr;
    }

    @Override
    protected String getPage() {
        return Pages.CATEGORY_PAGE + pageNumStr;
    }

    /**
     * For future use to manipulate with menus
     * @param pc
     */
    public void clickOnSubcategory(String pc) {
        WebElement clickableSubcategory = driver.findElement(By.xpath(TOP_MENU_SUBCATEGORY_SELECTOR));
        clickableSubcategory.click();
    }

    /**
     * For future use to manipulate with menus
     * @param category
     */
    public WebElement getShowAllForCategory(String category) {
        String xpathShowAll = String.format(TOP_MENU_CATEGORY_SHOW_ALL, category);
        WebElement clickableSubcategory = driver.findElement(By.xpath(xpathShowAll));
        return clickableSubcategory;
    }

    public void waitToBeLoaded() {
        PageUtils.waitUntilVisible(categoryTitle);
    }

    public void selectFromDropdown(String limit) {
        PageUtils.getElementWhenVisible(By.xpath(String.format(PAGING_DROPDOWN_SELECTOR_LOCATOR, limit))).click();
    }

    public int getProductElementsCount() {
        return driver.findElements(By.xpath("//div[@class='product-thumb']")).size();
    }

    /**
     * For future use to manipulate with menus
     * @param name
     */
    public WebElement getCategoryMenuByName(String name) {
        String formatted = String.format("//a[contains(text(), '%s')]", name);
        return driver.findElement(By.xpath(formatted));
    }
}
