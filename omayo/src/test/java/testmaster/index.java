package testmaster;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
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
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
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
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class index {
	WebDriver driver = null;
	ExtentHtmlReporter htmlReporter;
	ExtentReports extent;
	String projectPath = System.getProperty("user.dir");

	@BeforeSuite
	public void setup() {
		htmlReporter = new ExtentHtmlReporter("extent.html");
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
	}

	@Parameters("browserName")
	@BeforeTest
	public void setUpTest(String browserName) throws InterruptedException {

		// System.out.println("Browser name is : "+browserName+"\n Thread id :
		// "+Thread.currentThread().getId());

		if (browserName.equalsIgnoreCase("chrome")) {

			System.setProperty("webdriver.chrome.driver", projectPath + "/driver/chromedriver/chromedriver");
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
			driver = new ChromeDriver(chromeOptions);
			// driver = new ChromeDriver();
			System.out.println("driver started");

		} else if (browserName.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "/driver/geckodriver/geckodriver");
			driver = new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("safari")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "/driver/safari/safaridriver");
			driver = new SafariDriver();
		}
	}

	@AfterTest
	public void tearDownTest() {
		// driver.close();
		driver.quit();
		// System.out.println( "Test Completed");
	}

	@AfterMethod
	@AfterSuite
	public void tearDown() {
		extent.flush();
	}

	// TestCase1: In index page, click the home button on the menu bar; should be
	// redirect to index page with http;
	@Ignore
	@Parameters("browserName")
	@Test
	public void testCase1(String browserName) throws InterruptedException, IOException, AWTException {
		String ActualResult = "";
		String ExpectedResult = "http://omayo.blogspot.com/";

		driver.get("https://omayo.blogspot.com/");
		Reporter.log("Opened the browser and direct to index page URL (" + driver.getCurrentUrl() + ")<br>");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.visibilityOf(IndexPage.menu_home(driver)));
		IndexPage.menu_home(driver).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		ActualResult = driver.getCurrentUrl();
		Reporter.log("Redirect to target URL (" + ActualResult + ")<br>");
		Robot robot = new Robot();
		try {
			AssertJUnit.assertEquals(ActualResult, ExpectedResult);
			Reporter.log("Test Passed, ActualUrl: " + ActualResult + ", ExpectedUrl: " + ExpectedResult);
			BufferedImage screenShot = robot
					.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
			ImageIO.write(screenShot, "JPG",
					new File(projectPath + "/screenshot/Passed_TestCase1_" + browserName + ".jpg"));
		} catch (AssertionError e) {
			Reporter.log("Test Failed, ActualUrl: " + ActualResult + ", ExpectedUrl: " + ExpectedResult);
			BufferedImage screenShot = robot
					.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
			ImageIO.write(screenShot, "JPG",
					new File(projectPath + "/screenshot/Failed_TestCase1_" + browserName + ".jpg"));
		}
	}

	// TestCase2: In index page, select drop down list option "doc 1"; should
	// selected "doc1" option;
	@Ignore
	@Parameters("browserName")
	@Test
	public void testCase2(String browserName) throws AWTException, IOException, InterruptedException {
		String ActualResult;
		String ExpectedResult = "doc1";

		driver.get("https://omayo.blogspot.com/");
		Reporter.log("Opened the browser and direct to index page URL (" + driver.getCurrentUrl() + ")<br>");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.visibilityOf(IndexPage.dropdownlist_drop1(driver)));
		Select objSelect = new Select(driver.findElement(By.id("drop1")));
		objSelect.selectByVisibleText("doc 1");
		Reporter.log("The 'drop1' of the drop down list selected 'doc1'<br>");
		ActualResult = objSelect.getFirstSelectedOption().getText();
		Reporter.log("Selected option " + ActualResult);
		try {
			AssertJUnit.assertEquals(ActualResult, ExpectedResult);
			Reporter.log("Test Passed, ActualUrl: " + ActualResult + ", ExpectedUrl: " + ExpectedResult);
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File(projectPath + "/screenshot/Passed_TestCase2_" + browserName + ".jpg"));
			Thread.sleep(5000);
		} catch (AssertionError e) {
			Reporter.log("Test Failed, ActualUrl: " + ActualResult + ", ExpectedUrl: " + ExpectedResult);
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File(projectPath + "/screenshot/Failed_TestCase2_" + browserName + ".jpg"));
			Thread.sleep(5000);
		}
	}

	// TestCase3: In index page, get the textbox1 value; the value should same with
	// expected result;
	@Ignore
	@Parameters("browserName")
	@Test
	public void testCase3(String browserName) throws IOException {
		String ActualResult;
		String ExpectedResult = "Selenium WebDriver";

		driver.get("https://omayo.blogspot.com/");
		Reporter.log("Opened the browser and direct to index page URL (" + driver.getCurrentUrl() + ")<br>");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.visibilityOf(IndexPage.textboxpreloadedtext_textbox1(driver)));
		Actions actions = new Actions(driver);
		actions.moveToElement(IndexPage.textboxpreloadedtext_textbox1(driver)).perform();
		ActualResult = IndexPage.textboxpreloadedtext_textbox1(driver).getAttribute("value");
		Reporter.log(ActualResult + "<br>");
		try {
			AssertJUnit.assertEquals(ActualResult, ExpectedResult);
			Reporter.log("Test Passed, ActualUrl: " + ActualResult + ", ExpectedUrl: " + ExpectedResult);
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File(projectPath + "/screenshot/Passed_TestCase3_" + browserName + ".jpg"));
		} catch (AssertionError e) {
			Reporter.log("Test Failed, ActualUrl: " + ActualResult + ", ExpectedUrl: " + ExpectedResult);
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File(projectPath + "/screenshot/Failed_TestCase3_" + browserName + ".jpg"));
		}
	}

	// TestCase4: In index page, click the id link2 to open URL on new tab; should
	// opened new tab and directed to http://selenium143.blogspot.com/;
	@Ignore
	@Parameters("browserName")
	@Test
	public void testCase4(String browserName) throws IOException, AWTException, InterruptedException {
		String ActualResult;
		String ExpectedResult = "http://selenium143.blogspot.com/";

		driver.get("https://omayo.blogspot.com/");
		Reporter.log("Opened the browser and direct to index page URL (" + driver.getCurrentUrl() + ")<br>");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.visibilityOf(IndexPage.urlopennewwindow_seleniumtutorial(driver)));
		Actions action = new Actions(driver);

		Reporter.log("Opened target URL (" + ExpectedResult + ") at new tab<br>");
		// Store the ID of the original window
		String originalWindow = driver.getWindowHandle();

		// Check we don't have other windows open already
		assert driver.getWindowHandles().size() == 1;

		// Click the link which opens in a new window

		action.moveToElement(IndexPage.urlopennewwindow_seleniumtutorial(driver)).click().perform();
		new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.numberOfWindowsToBe(2));
		// Wait for the new window or tab
		wait.until(ExpectedConditions.numberOfWindowsToBe(2));

		// Loop through until we find a new window handle
		for (String windowHandle : driver.getWindowHandles()) {
			if (!originalWindow.contentEquals(windowHandle)) {
				driver.switchTo().window(windowHandle);
				break;
			}
		}

		// Wait for the new tab to finish loading content
		wait.until(ExpectedConditions.titleIs("Selenium143"));

		ActualResult = driver.getCurrentUrl();
		Robot robot = new Robot();
		try {
			AssertJUnit.assertEquals(ActualResult, ExpectedResult);
			Reporter.log("Test Passed, ActualUrl: " + ActualResult + ", ExpectedUrl: " + ExpectedResult
					+ " ,and total tab: " + driver.getWindowHandles().size());
			BufferedImage screenShot = robot
					.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
			ImageIO.write(screenShot, "JPG",
					new File(projectPath + "/screenshot/Passed_TestCase4_" + browserName + ".jpg"));
		} catch (AssertionError e) {
			Reporter.log("Test Failed, ActualUrl: " + ActualResult + ", ExpectedUrl: " + ExpectedResult
					+ " ,and total tab: " + driver.getWindowHandles().size());
			BufferedImage screenShot = robot
					.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
			ImageIO.write(screenShot, "JPG",
					new File(projectPath + "/screenshot/Passed_TestCase4_" + browserName + ".jpg"));
		}
	}

	// TestCase5: In index page, check the unordered list is correct or not; it
	// should included Apple, Banana, Grapes, Mango, Orange, Pomegranate;
	@Ignore
	@Parameters("browserName")
	@Test
	public void testCase5(String browserName) throws IOException, AWTException, InterruptedException {
		String[] ActualResult = new String[6];
		String[] ExpectedResult = { "Apple", "Banana", "Grapes", "Mango", "Orange", "Pomegranate" };

		// Open target testing website
		driver.get("https://omayo.blogspot.com/");
		Reporter.log("Opened the browser and direct to index page URL (" + driver.getCurrentUrl() + ")<br>");
		// Wait the unordered list is shown out
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='HTML26']/div[1]/ul/li")));
		// Scroll to target elements
		Actions actions = new Actions(driver);
		actions.moveToElement(driver.findElement(By.xpath("//*[@id='HTML26']/div[1]/ul/li[6]"))).perform();
		// Push the target list to array of actual result
		List<WebElement> allFruit = driver.findElements(By.xpath("//*[@id='HTML26']/div[1]/ul/li"));
		int i = 0;
		for (WebElement fruit : allFruit) {
			ActualResult[i] = fruit.getText();
			// System.out.println(i+"."+ActualResult[i]);
			i++;
		}
		// invoking sort() method of the ActualResult
		Arrays.sort(ActualResult);
		try {
			// Assert result
			AssertJUnit.assertArrayEquals(ActualResult, ExpectedResult);
			Reporter.log("Test Passed, ActualResult: " + ActualResult + ", ExpectedResult: " + ExpectedResult);
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File(projectPath + "/screenshot/Passed_TestCase5_" + browserName + ".jpg"));
		} catch (AssertionError e) {
			Reporter.log("Test Failed, ActualResult: " + ActualResult + ", ExpectedResult: " + ExpectedResult);
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File(projectPath + "/screenshot/Failed_TestCase5_" + browserName + ".jpg"));
		}
	}

	// TestCase6: In index page, wait the timerButton enable and click the alert OK
	// button on window alert message; it should be closed;
	@Ignore
	@Parameters("browserName")
	@Test
	public void testCase6(String browserName) throws InterruptedException, IOException, AWTException {
		String ActualResult;
		String ExpectedResult = "Hello";

		// Direct to testing website
		driver.get("https://omayo.blogspot.com/");
		Reporter.log("Opened the browser and direct to index page URL (" + driver.getCurrentUrl() + ")<br>");
		// Waiting the timerButton is clickable
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.elementToBeClickable(IndexPage.timerenablebutton_timerButton(driver)));
		// Driver move to timerButton
		Actions actions = new Actions(driver);
		actions.moveToElement(IndexPage.timerenablebutton_timerButton(driver)).perform();
		// Click the timerButton
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", IndexPage.timerenablebutton_timerButton(driver));
		// Wait for the alert to be displayed and store it in a variable
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		// Store the alert in a variable for result assert
		ActualResult = alert.getText();
		Robot robot = new Robot();
		try {
			// Assert result
			AssertJUnit.assertEquals(ActualResult, ExpectedResult);
			Reporter.log("Test Passed, ActualUrl: " + ActualResult + ", ExpectedUrl: " + ExpectedResult);
			// Capture full window screen
			BufferedImage screenShot = robot
					.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
			ImageIO.write(screenShot, "JPG",
					new File(projectPath + "/screenshot/Passed_TestCase6_" + browserName + ".jpg"));
			// Press the OK button
			alert.accept();
		} catch (AssertionError e) {
			Reporter.log("Test Failed, ActualUrl: " + ActualResult + ", ExpectedUrl: " + ExpectedResult);
			// Capture full window screen
			BufferedImage screenShot = robot
					.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
			ImageIO.write(screenShot, "JPG",
					new File(projectPath + "/screenshot/Failed_TestCase6_" + browserName + ".jpg"));
			// Press the OK button
			alert.accept();
		}
	}

	// TestCase7: In index page, click the text "check this" button, then the dte of
	// checkbox will enable and checked it; checkbox should be checked;
	@Ignore
	@Parameters("browserName")
	@Test
	public void testCase7(String browserName) throws InterruptedException, IOException, AWTException {
		boolean ActualResult = false;
		boolean ExpectedResult = true;

		// Direct to testing website
		driver.get("https://omayo.blogspot.com/");
		Reporter.log("Opened the browser and direct to index page URL (" + driver.getCurrentUrl() + ")<br>");
		// Move to "Check this" button
		Actions action = new Actions(driver);
		action.moveToElement(IndexPage.button_checkthis(driver)).perform();
		// Waiting the "Check this" button is visibility
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.visibilityOf(IndexPage.button_checkthis(driver)));
		// Click the text "Check this" button
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", IndexPage.button_checkthis(driver));
		// Wait the id dte checkbox enabled in 10 seconds after clicking on "Check this"
		// button
		wait.until(ExpectedConditions.elementToBeClickable(IndexPage.checkbox_dte(driver)));
		// Checked the id dte checkbox
		IndexPage.checkbox_dte(driver).click();
		// Get the id dte checkbox status
		ActualResult = IndexPage.checkbox_dte(driver).isEnabled();
		try {
			// Assert result
			AssertJUnit.assertEquals(ActualResult, ExpectedResult);
			Reporter.log("Test Passed, ActualUrl: " + ActualResult + ", ExpectedUrl: " + ExpectedResult);
			// Browser capture screen
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File(projectPath + "/screenshot/Passed_TestCase7_" + browserName + ".jpg"));
		} catch (AssertionError e) {
			Reporter.log("Test Failed, ActualUrl: " + ActualResult + ", ExpectedUrl: " + ExpectedResult);
			// Browser capture screen
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile,
					new File(projectPath + "/screenshot/Failed_TestCase7 _" + browserName + ".jpg"));
		}
	}

	// TestCase8: In index page, mouse over the Blogs on menu, then show the
	// sub-menu and select Selenium143; The page should directed to
	// "https://selenium143.blogspot.com/";
	@Ignore
	@Parameters("browserName")
	@Test
	public void testCase8(String browserName) throws InterruptedException, IOException, AWTException {
		String ActualResult;
		String ExpectedResult = "http://selenium143.blogspot.com/";

		// Direct to testing website
		driver.get("https://omayo.blogspot.com/");
		Reporter.log("Opened the browser and direct to index page URL (" + driver.getCurrentUrl() + ")<br>");
		// Waiting the id blogsmenu span is visibility
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.visibilityOf(IndexPage.menu_blogs(driver)));
		// Mouse over the id blogsmenu span
		Actions action = new Actions(driver);
		action.moveToElement(IndexPage.menu_blogs(driver)).perform();
		// Waiting the sub-menu and Selenium143 show out
		wait.until(ExpectedConditions
				.visibilityOf(driver.findElement(By.xpath("//span[contains(text(),'Selenium143')]"))));
		// Click the Selenium143 on the sub-menu
		driver.findElement(By.xpath("//span[contains(text(),'Selenium143')]")).click();
		// Wait for the new tab to finish loading content
		wait.until(ExpectedConditions.titleIs("Selenium143"));
		// Get current website URL
		ActualResult = driver.getCurrentUrl();
		try {
			// Assert result
			AssertJUnit.assertEquals(ActualResult, ExpectedResult);
			Reporter.log("Test Passed, ActualUrl: " + ActualResult + ", ExpectedUrl: " + ExpectedResult);
			// Browser capture screen
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File(projectPath + "/screenshot/Passed_TestCase8_" + browserName + ".jpg"));
		} catch (AssertionError e) {
			Reporter.log("Test Failed, ActualUrl: " + ActualResult + ", ExpectedUrl: " + ExpectedResult);
			// Browser capture screen
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile,
					new File(projectPath + "/screenshot/Failed_TestCase8 _" + browserName + ".jpg"));
		}
	}

	// TestCase9: In index page, get the text area content and verifty it; It should
	// be same with expected result;
	@Ignore
	@Parameters("browserName")
	@Test
	public void testCase9(String browserName) throws InterruptedException, IOException, AWTException {
		String ActualResult;
		String ExpectedResult = "The cat was playing in the garden.";

		// Direct to testing website
		driver.get("https://omayo.blogspot.com/");
		Reporter.log("Opened the browser and direct to index page URL (" + driver.getCurrentUrl() + ")<br>");
		// Waiting the "cat playing" textarea is visibility
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.visibilityOf(IndexPage.textarea_catplaying(driver)));
		// Move to "cat playing" textarea
		Actions action = new Actions(driver);
		action.moveToElement(IndexPage.textarea_catplaying(driver)).perform();
		// Get the "cat playing" textarea content
		ActualResult = IndexPage.textarea_catplaying(driver).getText();
		System.out.println(ActualResult);
		try {
			// Assert result
			AssertJUnit.assertEquals(ActualResult, ExpectedResult);
			Reporter.log("Test Passed, ActualUrl: " + ActualResult + ", ExpectedUrl: " + ExpectedResult);
			// Browser capture screen
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File(projectPath + "/screenshot/Passed_TestCase9_" + browserName + ".jpg"));
		} catch (AssertionError e) {
			Reporter.log("Test Failed, ActualUrl: " + ActualResult + ", ExpectedUrl: " + ExpectedResult);
			// Browser capture screen
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile,
					new File(projectPath + "/screenshot/Failed_TestCase9 _" + browserName + ".jpg"));
		}
	}

	// TestCase10: In index page, input wrong username and password; Login error
	// alert should be shown out;
	@Ignore
	@Parameters("browserName")
	@Test
	public void testCase10(String browserName) throws InterruptedException, IOException, AWTException {
		String ActualResult;
		String ExpectedResult = "Error Password or Username";

		// Direct to testing website
		driver.get("https://omayo.blogspot.com/");
		Reporter.log("Opened the browser and direct to index page URL (" + driver.getCurrentUrl() + ")<br>");
		// Waiting the id userid input is visibility
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.visibilityOfAllElements(
				Arrays.asList(IndexPage.loginpage_input_username(driver), IndexPage.loginpage_input_password(driver),
						IndexPage.loginpage_button_login(driver), IndexPage.loginpage_button_cancel(driver))));
		// Move to id userid input
		Actions action = new Actions(driver);
		action.moveToElement(IndexPage.loginpage_input_username(driver)).perform();
		// Input wrong username and password
		IndexPage.loginpage_input_username(driver).sendKeys("aaa");
		IndexPage.loginpage_input_password(driver).sendKeys("bbb");
		// Click the login button
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", IndexPage.loginpage_button_login(driver));
		// Wait for the login error alert to be displayed
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		// Store the alert in a variable for result assert
		ActualResult = alert.getText();
		Robot robot = new Robot();
		try {
			// Assert result
			AssertJUnit.assertEquals(ActualResult, ExpectedResult);
			Reporter.log("Test Passed, ActualUrl: " + ActualResult + ", ExpectedUrl: " + ExpectedResult);
			// Capture full window screen
			BufferedImage screenShot = robot
					.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
			ImageIO.write(screenShot, "JPG",
					new File(projectPath + "/screenshot/Passed_TestCase10_" + browserName + ".jpg"));
			// Press the OK button
			alert.accept();
		} catch (AssertionError e) {
			Reporter.log("Test Failed, ActualUrl: " + ActualResult + ", ExpectedUrl: " + ExpectedResult);
			// Capture full window screen
			BufferedImage screenShot = robot
					.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
			ImageIO.write(screenShot, "JPG",
					new File(projectPath + "/screenshot/Failed_TestCase10_" + browserName + ".jpg"));
			// Press the OK button
			alert.accept();
		}
	}

	// TestCase11: In index page, capture the image element and compare with correct
	// it; It should be same;
	@Ignore
	@Parameters("browserName")
	@Test
	public void testCase11(String browserName) throws InterruptedException, IOException, AWTException {
		BufferedImage ActualImage;
		BufferedImage ExpectedImage = ImageIO
				.read(new File(System.getProperty("user.dir") + "/test item/image/checkimage.png"));

		// Direct to testing website
		driver.get("https://omayo.blogspot.com/");
		Reporter.log("Opened the browser and direct to index page URL (" + driver.getCurrentUrl() + ")<br>");
		// Waiting the image element img is visibility
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.visibilityOf(IndexPage.image_element(driver)));
		// Set the window size for capture target image
		driver.manage().window().setSize(new Dimension(1280, 1080));
		// Get the target image
		WebElement element = IndexPage.image_element(driver);
		File actualFile = element.getScreenshotAs(OutputType.FILE);
		BufferedImage actualImage = ImageIO.read(actualFile);
		FileUtils.copyFile(actualFile, new File(projectPath + "/screenshot/dashboardActual.png"));
		// Set compare expected image
		File expectedFile = new File(projectPath + "/screenshot/checkimage.png");
		BufferedImage expectedImage = ImageIO.read(expectedFile);
		// Compare two images for visual testing
		ImageDiff diff = new ImageDiffer().makeDiff(actualImage, expectedImage);
		BufferedImage diffImage = diff.getMarkedImage();

		try {
			// Assert result
			AssertJUnit.assertTrue(diff.hasDiff());
			Reporter.log("Test Passed");
			// Capture window screen
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile,
					new File(projectPath + "/screenshot/Passed_TestCase11_" + browserName + ".jpg"));
		} catch (AssertionError e) {
			Reporter.log("Test Failed");
			// Capture window screen
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile,
					new File(projectPath + "/screenshot/Failed_TestCase11_" + browserName + ".jpg"));
		}
	}

	// TestCase12: In index page, click the id prompt button then show the prompt
	// box, input your name and click the ok button; The prompt box should be
	// closed;
	@Ignore
	@Parameters("browserName")
	@Test
	public void testCase12(String browserName) throws InterruptedException, IOException, AWTException {
		String ActualResult;
		String ExpectedResult = "What is your name?";

		// Direct to testing website
		driver.get("https://omayo.blogspot.com/");
		Reporter.log("Opened the browser and direct to index page URL (" + driver.getCurrentUrl() + ")<br>");
		// Waiting the id prompt button is visibility
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.visibilityOf(IndexPage.getpromptbutton_prompt(driver)));
		// Move to show the id prompt button
		Actions actions = new Actions(driver);
		actions.moveToElement(IndexPage.getpromptbutton_prompt(driver)).perform();
		// Click the id prompt button
		IndexPage.getpromptbutton_prompt(driver).click();
		// Thread.sleep(3000);
		// Wait for the prompt box to be displayed and store it in a variable
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		// Chrome driver is not support prompt alert input text, so if browser is not
		// chrome then input the name
		if (browserName != "chrome") {
			// input your name
			alert.sendKeys("KaKaKaKa");
		}
		// Get the alert message for reuslt compare
		ActualResult = alert.getText();
		Thread.sleep(3000);
		Robot robot = new Robot();
		try {
			// Assert result
			AssertJUnit.assertEquals(ActualResult, ExpectedResult);
			Reporter.log("Test Passed, ActualUrl: " + ActualResult + ", ExpectedUrl: " + ExpectedResult);
			// Capture full window screen
			BufferedImage screenShot = robot
					.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
			ImageIO.write(screenShot, "JPG",
					new File(projectPath + "/screenshot/Passed_TestCase12_" + browserName + ".jpg"));
			// Click the ok button
			alert.accept();
		} catch (AssertionError e) {
			Reporter.log("Test Failed");
			// Capture full window screen
			BufferedImage screenShot = robot
					.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
			ImageIO.write(screenShot, "JPG",
					new File(projectPath + "/screenshot/Failed_TestCase12_" + browserName + ".jpg"));
			// Click the ok button
			alert.accept();
		}
	}

	// TestCase13: In index page, check select a vehicle of radio button and select
	// car of radio button; Should selected car of radio button;
	@Ignore
	@Parameters("browserName")
	@Test
	public void testCase13(String browserName) throws InterruptedException, IOException, AWTException {
		String ActualResult = null;
		String ExpectedResult = "Car";

		// Direct to testing website
		driver.get("https://omayo.blogspot.com/");
		Reporter.log("Opened the browser and direct to index page URL (" + driver.getCurrentUrl() + ")<br>");
		// Waiting those radio button show out
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.visibilityOfAllElements(IndexPage.selectavehicleradiobutton_vehicle(driver)));
		// Move to show the select a vehicle of radio button
		Actions actions = new Actions(driver);
		actions.moveToElement(IndexPage.selectvehicleradiobutton_car(driver)).perform();
		// Check which one selected
		List RadioButton = IndexPage.selectavehicleradiobutton_vehicle(driver);
		for (int i = 0; i < RadioButton.size(); i++) {
			String val = ((WebElement) RadioButton.get(i)).getAttribute("value");
			if (val.equalsIgnoreCase("car")) {
				((WebElement) RadioButton.get(i)).click();
				ActualResult = ((WebElement) RadioButton.get(i)).getAttribute("value");
				break;
			}
		}

		try {
			// Assert result
			AssertJUnit.assertEquals(ActualResult, ExpectedResult);
			Reporter.log("Test Passed, ActualUrl: " + ActualResult + ", ExpectedUrl: " + ExpectedResult);
			// Capture window screen
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile,
					new File(projectPath + "/screenshot/Passed_TestCase13_" + browserName + ".jpg"));
		} catch (AssertionError e) {
			Reporter.log("Test Failed, ActualUrl: " + ActualResult + ", ExpectedUrl: " + ExpectedResult);
			// Capture window screen
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile,
					new File(projectPath + "/screenshot/Passed_TestCase13_" + browserName + ".jpg"));
		}
	}

	// TestCase14: In index page, checked select multiple options of all checkboxes; Should checked all checkboxes;
	//@Ignore
	@Parameters("browserName")
	@Test
	public void testCase14(String browserName) throws InterruptedException, IOException, AWTException {
		int ActualResult = 0;
		int ExpectedResult = 4;

		// Direct to testing website
		driver.get("https://omayo.blogspot.com/");
		Reporter.log("Opened the browser and direct to index page URL (" + driver.getCurrentUrl() + ")<br>");
		// Waiting those checkboxes show out
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.visibilityOfAllElements(IndexPage.checkboxselectmultipleoptions_accessories(driver)));
		// Move to show the select multiple options of checkboxes
		Actions actions = new Actions(driver);
		actions.moveToElement(IndexPage.buttondelayedbuttondropdown_Dropdown(driver)).perform();
		// Check which one selected
		List checkboxes = IndexPage.checkboxselectmultipleoptions_accessories(driver);
		for (int i = 0; i < checkboxes.size(); i++) {
			if(!((WebElement) checkboxes.get(i)).isSelected())
			{
				((WebElement) checkboxes.get(i)).click();
			}
			ActualResult += 1;
		}

		try {
			// Assert result
			AssertJUnit.assertEquals(ActualResult, ExpectedResult);
			Reporter.log("Test Passed, ActualUrl: " + ActualResult + ", ExpectedUrl: " + ExpectedResult);
			// Capture window screen
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile,
					new File(projectPath + "/screenshot/Passed_TestCase14_" + browserName + ".jpg"));
		} catch (AssertionError e) {
			Reporter.log("Test Failed, ActualUrl: " + ActualResult + ", ExpectedUrl: " + ExpectedResult);
			// Capture window screen
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile,
					new File(projectPath + "/screenshot/Passed_TestCase14_" + browserName + ".jpg"));
		}
	}

	// For test code
	@Ignore
	@Test
	public void testSomethink() throws InterruptedException, IOException {
		try {

			System.setProperty("webdriver.chrome.driver", projectPath + "/driver/chromedriver/chromedriver");
			ChromeOptions options = new ChromeOptions();
			// options.setBinary("/Users/kacochan/git/omayo/omayo/driver/chromedriver/chromedriver");
			WebDriver driver = new ChromeDriver(options);
			// driver.get("https://www.google.com/");

			// WebDriver driver = new ChromeDriver();
			driver.get("https://omayo.blogspot.com/");
			WebElement element = IndexPage.image_element(driver);
			driver.manage().window().setSize(new Dimension(1280, 1080));
			File scrFile = element.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File(projectPath + "/screenshot/testSomethink.jpg"));

			Thread.sleep(5000);
			driver.quit();

		} catch (AssertionError e) {

		}
	}
}
