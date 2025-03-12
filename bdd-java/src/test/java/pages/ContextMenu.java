package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

public class ContextMenu{
    
    WebDriver webDriver;
    
    @FindBy(id = "hot-spot")
    WebElement hotSpot;

    public ContextMenu(WebDriver driver){
        this.webDriver = driver;
        PageFactory.initElements(driver, this);
    }

    public void rightClick(){
        Actions actions = new Actions(webDriver);
        actions.contextClick(hotSpot).perform();
    }

}