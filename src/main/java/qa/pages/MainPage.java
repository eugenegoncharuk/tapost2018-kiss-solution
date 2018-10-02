package qa.pages;

import core.utils.PageUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MainPage extends Page {

    public MainPage(RemoteWebDriver driver) {
        super(driver);
    }

    private String MENU_SELECTOR = "//li[class='dropdown'] a[contains(text(), '%s')]";

    @Override
    protected String getPage() {
        return Pages.MAIN_PAGE;
    }

    @Override
    public void waitToBeLoaded() {
    }

    public WebElement getTopMenuElement(String text) {
        return PageUtils.getElementWhenVisible(By.xpath(String.format(MENU_SELECTOR, text)));
    }
}
