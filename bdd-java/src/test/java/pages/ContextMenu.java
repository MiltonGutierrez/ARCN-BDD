package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.FindBy;

public class ContextMenu{
    
    WebDriver webDriver;
    
    @FindBy(id = "hot-spot")
    WebElement hotSpot;

    public ContextMenu(WebDriver driver){
        webDriver = driver;
    }

    public void rightClick(){
        hotSpot.contextClick();
    }


}