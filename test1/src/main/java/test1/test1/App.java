package test1.test1;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;  

/**
 * Hello world!
 *
 */
public class App 
{
	WebDriver driver = null;
	  @BeforeTest
	public void initializeDriver()
	{
		 System.out.println( "Hello World!" );
	        // Create a new instance of the Firefox driver
	        // Notice that the remainder of the code relies on the interface, 
	        // not the implementation.
	        System.setProperty("webdriver.chrome.driver","D:\\vijiworkspace\\chromedriver_win32\\chromedriver.exe"
	        		+ "");
	        driver = new ChromeDriver(); //FirefoxDriver();

	}
	  @Test
    public  void openPage()
    {
       
        // And now use this to visit Google
        driver.get("https://www.guru99.com");
        // Alternatively the same thing can be done like this
        // driver.navigate().to("http://www.google.com");

        // Find the text input element by its name
      //  WebElement element = driver.findElement(By.name("q"));

        // Enter something to search for
       // element.sendKeys("testing techniques!");

        // Now submit the form. WebDriver will find the form for us from the element
        //element.submit();

//      /  Google's search is rendered dynamically with JavaScript.
 //       Wait for the page to load, timeout after 10 seconds
      WebElement myDynamicElement = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.id("java_technologies")));
      

        Reporter.log("Page loaded and title is: " + driver.getTitle());
        
        
        Utilies utilies = new Utilies();
  		try {
  			utilies.takeSnapShot(driver, "D:\\\\vijiworkspace\\\\TestFiles\\Homepage.jpeg");
  		} catch (Exception e1) {
  			// TODO Auto-generated catch block
  			e1.printStackTrace();
  		}
          // Check the title of the page
          System.out.println("Page title is: " + driver.getTitle());
    	ArrayList<InputData> datatoWrite = new ArrayList<InputData>();
       // xpath = //h3[@class='rc']/a
        List<WebElement> linkElements = driver.findElements(By.xpath("//*[@id='java_technologies']//li/a")); //*[@id='rso']//div/h3/a"));
        Reporter.log("Links in page is : " + linkElements.size());
        for (WebElement webElement : linkElements)
        {
        	 
        	System.out.println("Links" + webElement.isDisplayed() + webElement.getText());
        	System.out.println("Links" + webElement.getAttribute("href"));
        	if(webElement.isDisplayed())
        	{
        		InputData e = new InputData();
        		e.setLink(webElement.getAttribute("href"));
        		datatoWrite.add(e);
        		
        	}
		}
        
        //Write the links in Excel
        if(datatoWrite.size() >0)
        {
        	 Reporter.log("Links detai;s : " + datatoWrite);
        	ExcelOperation excelOperation = new ExcelOperation();
        	excelOperation.InitiateExcel(datatoWrite);
        }
        System.out.println("Links" + linkElements.size());
        // Should see: "cheese! - Google Search"
        System.out.println("Page title is: " + driver.getTitle());
        
      

    }
	  @AfterTest
    public void endTest()
    {
    	  //Close the browser
		  Reporter.log("closing browser");
		  driver.quit();
    }
}
