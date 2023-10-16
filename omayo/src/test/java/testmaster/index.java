package testmaster;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Set;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Ignore;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import pages.IndexPage;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class index {
	WebDriver driver = null;
	ExtentHtmlReporter htmlReporter;
	ExtentReports extent;
	String projectPath = System.getProperty("user.dir");
	
	@BeforeSuite
	public void setup(){
		htmlReporter = new ExtentHtmlReporter("extent.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
	}
	
	@Parameters("browserName")
	@BeforeTest
	public void setUpTest(String browserName) throws InterruptedException{

		//System.out.println("Browser name is : "+browserName+"\n Thread id : "+Thread.currentThread().getId());

		if(browserName.equalsIgnoreCase("chrome")){

			System.setProperty("webdriver.chrome.driver", projectPath+"/driver/chromedriver/chromedriver");
			ChromeOptions chromeOptions = new ChromeOptions();
		    chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
			driver = new ChromeDriver(chromeOptions);
			//System.out.println("driver started");
			
		}
		else if (browserName.equalsIgnoreCase("firefox")){
			System.setProperty("webdriver.gecko.driver", projectPath+"/driver/geckodriver/geckodriver");
			driver = new FirefoxDriver();
		}
		else if (browserName.equalsIgnoreCase("safari")){
			System.setProperty("webdriver.gecko.driver", projectPath+"/driver/safari/safaridriver");
			driver = new SafariDriver();
		}
	}
	
	@AfterTest
	public void tearDownTest(){
		
		//driver.close();
		driver.quit();
		//System.out.println( "Test Completed");

	}
	
	@AfterSuite
	public void tearDown(){
		extent.flush();
	}
	
	//TestCase1: In index page, click the home button on the menu bar; should be redirect to index page with http;
	@Ignore
	@Parameters("browserName")
	@Test
	public void testCase1(String browserName) throws InterruptedException, IOException, AWTException{
		String ActualResult = "";
		String ExpectedResult = "http://omayo.blogspot.com/";
		
		driver.get("https://omayo.blogspot.com/");
		Reporter.log("Opened the browser and direct to index page URL (" + driver.getCurrentUrl() +")<br>");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.visibilityOf(IndexPage.menu_home(driver)));
		IndexPage.menu_home(driver).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		ActualResult = driver.getCurrentUrl();
		Reporter.log("Redirect to target URL (" + ActualResult +")<br>");
		Robot robot = new Robot();
		try{
			AssertJUnit.assertEquals(ActualResult, ExpectedResult);
			Reporter.log("Test Passed, ActualUrl: "+ActualResult+", ExpectedUrl: "+ExpectedResult);
			BufferedImage screenShot = robot.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
			ImageIO.write(screenShot, "JPG", new File(projectPath+"/screenshot/Passed_TestCase1_"+browserName+".jpg"));
		}
		catch(AssertionError e){
			Reporter.log("Test Failed, ActualUrl: "+ActualResult+", ExpectedUrl: "+ExpectedResult);
			BufferedImage screenShot = robot.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
			ImageIO.write(screenShot, "JPG", new File(projectPath+"/screenshot/Failed_TestCase1_"+browserName+".jpg"));
		}
	}
	
	//TestCase2: In index page, select drop down list option "doc1"; should selected "doc1" option;
	@Ignore
	@Parameters("browserName")
	@Test
	public void testCase2(String browserName) throws AWTException, IOException {
		String ActualResult;
		String ExpectedResult = "doc1";
		
		driver.get("https://omayo.blogspot.com/");
		Reporter.log("Opened the browser and direct to index page URL (" + driver.getCurrentUrl() +")<br>");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.visibilityOf(IndexPage.dropdownlist_drop1(driver)));
		Reporter.log("debug point 1<br>");
		Select objSelect = new Select(driver.findElement(By.id("drop1")));
		Reporter.log("debug point 2<br>");
		objSelect.selectByVisibleText("doc1");
		Reporter.log("debug point 3<br>");
		Reporter.log("The 'drop1' of the drop down list selected 'doc1'<br>");
		ActualResult = objSelect.getFirstSelectedOption().getText();
		Reporter.log("Selected option " + ActualResult);
		try{
			AssertJUnit.assertEquals(ActualResult, ExpectedResult);
			Reporter.log("Test Passed, ActualUrl: "+ActualResult+", ExpectedUrl: "+ExpectedResult);
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File(projectPath+"/screenshot/Passed_TestCase2_"+browserName+".jpg"));
		}
		catch(AssertionError e){
			Reporter.log("Test Failed, ActualUrl: "+ActualResult+", ExpectedUrl: "+ExpectedResult);
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File(projectPath+"/screenshot/Failed_TestCase2_"+browserName+".jpg"));
		}
	}
	
	//TestCase3: In index page, get the textbox1 value; the value should same with expected result;
	@Ignore
	@Parameters("browserName")
	@Test
	public void testCase3(String browserName) throws IOException{
		String ActualResult;
		String ExpectedResult = "Selenium WebDriver";
		
		driver.get("https://omayo.blogspot.com/");
		Reporter.log("Opened the browser and direct to index page URL (" + driver.getCurrentUrl() +")<br>");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.visibilityOf(IndexPage.textboxpreloadedtext_textbox1(driver)));
		Actions actions = new Actions(driver);
		actions.moveToElement(IndexPage.textboxpreloadedtext_textbox1(driver));
		actions.perform();
		ActualResult = IndexPage.textboxpreloadedtext_textbox1(driver).getAttribute("value");
		Reporter.log(ActualResult+"<br>");
		try{
			AssertJUnit.assertEquals(ActualResult, ExpectedResult);
			Reporter.log("Test Passed, ActualUrl: "+ActualResult+", ExpectedUrl: "+ExpectedResult);
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File(projectPath+"/screenshot/Passed_TestCase3_"+browserName+".jpg"));
		}
		catch(AssertionError e){
			Reporter.log("Test Failed, ActualUrl: "+ActualResult+", ExpectedUrl: "+ExpectedResult);
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File(projectPath+"/screenshot/Failed_TestCase3_"+browserName+".jpg"));
		}
	}
	
	//TestCase4: In index page, click the id link2 to open URL on new tab; should opened new tab and directed to http://selenium143.blogspot.com/;
	@Ignore
	@Parameters("browserName")
	@Test
	public void testCase4(String browserName) throws IOException, AWTException, InterruptedException{
		String ActualResult;
		String ExpectedResult = "http://selenium143.blogspot.com/";
		
		driver.get("https://omayo.blogspot.com/");
		Reporter.log("Opened the browser and direct to index page URL (" + driver.getCurrentUrl() +")<br>");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.visibilityOf(IndexPage.urlopennewwindow_seleniumtutorial(driver)));
		Actions action = new Actions(driver);
		action.moveToElement(IndexPage.urlopennewwindow_seleniumtutorial(driver)).click().perform();
		new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.numberOfWindowsToBe(2));
		Reporter.log("Opened target URL (" + ExpectedResult +") at new tab<br>");
		ActualResult = driver.getCurrentUrl();
		
		//Thread.sleep(5000);
		Robot robot = new Robot();
		try{
			AssertJUnit.assertEquals(ActualResult, ExpectedResult);
			Reporter.log("Test Passed, ActualUrl: "+ActualResult+", ExpectedUrl: "+ExpectedResult+" ,and total tab: "+driver.getWindowHandles().size());
			BufferedImage screenShot = robot.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
			ImageIO.write(screenShot, "JPG", new File(projectPath+"/screenshot/Passed_TestCase4_"+browserName+".jpg"));
		}
		catch(AssertionError e){
			Reporter.log("Test Failed, ActualUrl: "+ActualResult+", ExpectedUrl: "+ExpectedResult+" ,and total tab: "+driver.getWindowHandles().size());
			BufferedImage screenShot = robot.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
			ImageIO.write(screenShot, "JPG", new File(projectPath+"/screenshot/Passed_TestCase4_"+browserName+".jpg"));
		}
	}
	
	//@Ignore
	@Test
	public void testSomethink() throws InterruptedException{
	    try {
	    	driver.get("https://omayo.blogspot.com/");
	    	System.out.println("Page Title is : "+driver.getTitle());
	    	String parent_window = driver.getWindowHandle();
	    	((JavascriptExecutor) driver).executeScript("window.open('http://www.yahoo.com.hk/');");
	    	new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.numberOfWindowsToBe(2));
	    	Thread.sleep(5000);
	    	System.out.println("Page Title is : "+driver.getTitle());
	    	Set<String> allWindows_1 = driver.getWindowHandles();
	    	
	      
	    }
	    catch(AssertionError e){
	    	
	    }
	}
}
