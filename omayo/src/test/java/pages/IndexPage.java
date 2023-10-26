package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class IndexPage {
	
	private static WebElement element = null;
	private static List<WebElement> listofelements = null;
	
	public static WebElement menu_home(WebDriver driver){
		
		element = driver.findElement(By.id("home"));
		return element;
		
	}
	
	public static WebElement menu_blogs(WebDriver driver){
		
		element = driver.findElement(By.id("blogsmenu"));
		return element;
		
	}
	
	public static WebElement url_selenium143(WebDriver driver){
		
		element = driver.findElement(By.id("selenium143"));
		return element;
		
	}
	
	public static WebElement dropdownlist_drop1(WebDriver driver){
		
		element = driver.findElement(By.id("drop1"));
		return element;
		
	}
	
	public static WebElement textboxpreloadedtext_textbox1(WebDriver driver){
		
		element = driver.findElement(By.id("textbox1"));
		return element;
		
	}

	public static WebElement urlopennewwindow_seleniumtutorial(WebDriver driver){
		
		element = driver.findElement(By.linkText("SeleniumTutorial"));
		return element;
		
	}

	public static WebElement enabledbutton_but2(WebDriver driver){
		
		element = driver.findElement(By.id("but2"));
		return element;
		
	}
	
	public static WebElement disabledbutton_but1(WebDriver driver){
		
		element = driver.findElement(By.id("but1"));
		return element;
		
	}
	
	public static WebElement disabledtextbox_tb2(WebDriver driver){
		
		element = driver.findElement(By.id("tb2"));
		return element;
		
	}
	
	public static WebElement samenameattributebtn_submit(WebDriver driver){
		
		element = driver.findElement(By.xpath("//*[@id='HTML10']/div[1]/button[1]"));
		return element;
		
	}
	
	public static WebElement samenameattributebtn_login(WebDriver driver){
		
		element = driver.findElement(By.xpath("//*[@id='HTML10']/div[1]/button[2]"));
		return element;
		
	}
	
	public static WebElement samenameattributebtn_register(WebDriver driver){
		
		element = driver.findElement(By.xpath("//*[@id='HTML10']/div[1]/button[2]"));
		return element;
		
	}
	
	public static WebElement displayfortimeanddissapearn_alert2(WebDriver driver){
		
		element = driver.findElement(By.id("alert2"));
		return element;
		
	}
	
	public static WebElement textwillbedisplayedwithdelay_delayedText(WebDriver driver){
		
		element = driver.findElement(By.id("delayedText"));
		return element;
		
	}
	
	public static WebElement urlpopupwindow_popup(WebDriver driver){
		
		element = driver.findElement(By.xpath("//*[@id='HTML37']/div[1]/p/a"));
		return element;
		
	}
	
	public static WebElement uploadfile_uploadfile(WebDriver driver){
		
		element = driver.findElement(By.id("uploadfile"));
		return element;
		
	}
	
	public static WebElement timerenablebutton_timerButton(WebDriver driver){
		
		element = driver.findElement(By.id("timerButton"));
		return element;
		
	}
	
	public static WebElement disableenablebutton_myBtn(WebDriver driver){
		
		element = driver.findElement(By.id("myBtn"));
		return element;
		
	}
	
	public static WebElement disableenablebutton_tryit(WebDriver driver){
		
		element = driver.findElement(By.xpath("//*[text()='Try it'"));
		return element;
		
	}
	
	public static WebElement button_buttonx(WebDriver driver){
		
		element = driver.findElement(By.xpath("//*[text()='Button X'"));
		return element;
		
	}
	
	public static WebElement button_buttony(WebDriver driver){
		
		element = driver.findElement(By.xpath("//*[text()='Button Y'"));
		return element;
		
	}
	
	public static WebElement button_doubleclickhere(WebDriver driver){
		
		element = driver.findElement(By.xpath("//*[text()=' Double click Here   '"));
		return element;
		
	}
	
	public static WebElement checkbox_dte(WebDriver driver){
		
		element = driver.findElement(By.id("dte"));
		return element;
		
	}
	
	public static WebElement button_checkthis(WebDriver driver){
		
		element = driver.findElement(By.xpath("//button[contains(text(),'Check this')]"));
		return element;
		
	}
	
	public static WebElement url_pageone(WebDriver driver){
		
		element = driver.findElement(By.xpath("//*[text()='Page One'"));
		return element;
		
	}
	
	public static WebElement url_homelink(WebDriver driver){
		
		element = driver.findElement(By.className("home-link"));
		return element;
		
	}
	
	public static WebElement url_feedlink(WebDriver driver){
		
		element = driver.findElement(By.className("feed-link"));
		return element;
		
	}
	
	public static WebElement textarea_ta1(WebDriver driver){
		
		element = driver.findElement(By.id("ta1"));
		return element;
		
	}
	
	public static WebElement textarea_textareafield(WebDriver driver){
		
		element = driver.findElement(By.xpath("//*[text()='The cat was playing in the garden.'"));
		return element;
		
	}
	
	public static WebElement table_table1(WebDriver driver){
		
		element = driver.findElement(By.id("table1"));
		return element;
		
	}
	
	public static WebElement htmlform_input_username(WebDriver driver){
		
		element = driver.findElement(By.xpath("//*[@id='HTML31']/div[1]/form/input[1]"));
		return element;
		
	}
	
	public static WebElement htmlform_input_password(WebDriver driver){
		
		element = driver.findElement(By.xpath("//*[@id='HTML31']/div[1]/form/input[2]"));
		return element;
		
	}
	
	public static WebElement htmlform_button_login(WebDriver driver){
		
		element = driver.findElement(By.xpath("//*[@id='HTML31']/div[1]/form/button[1]"));
		return element;
		
	}
	
	public static WebElement iframe_iframe1(WebDriver driver){
		
		element = driver.findElement(By.id("iframe1"));
		return element;
		
	}
	
	public static WebElement iframe_iframe2(WebDriver driver){
		
		element = driver.findElement(By.id("iframe2"));
		return element;
		
	}
	
	public static WebElement loginpage_input_username(WebDriver driver){
		
		element = driver.findElement(By.name("userid"));
		return element;
		
	}
	
	public static WebElement loginpage_input_password(WebDriver driver){
		
		element = driver.findElement(By.name("pswrd"));
		return element;
		
	}
	
	public static WebElement loginpage_button_login(WebDriver driver){
		
		element = driver.findElement(By.xpath("//*[@id='HTML42']/div[1]/form/input[3]"));
		return element;
		
	}
	
	public static WebElement loginpage_button_cancel(WebDriver driver){
		
		element = driver.findElement(By.xpath("//*[@id='HTML42']/div[1]/form/input[4]"));
		return element;
		
	}
	
	public static WebElement searchthisblog_textbox(WebDriver driver){
		
		element = driver.findElement(By.xpath("//*[@id='BlogSearch1_form']/form/table/tbody/tr/td[1]/input"));
		return element;
		
	}
	
	public static WebElement searchthisblog_searchbutton(WebDriver driver){
		
		element = driver.findElement(By.xpath("//*[@id='BlogSearch1_form']/form/table/tbody/tr/td[2]/input"));
		return element;
		
	}
	
	public static List<WebElement> radiobutton_gender(WebDriver driver){
		
		listofelements = driver.findElements(By.name("gender"));
		return listofelements;
		
	}
	
	public static WebElement button_alert1(WebDriver driver){
		
		element = driver.findElement(By.id("alert1"));
		return element;
		
	}
	
	public static WebElement checkbox_checkbox1(WebDriver driver){
		
		element = driver.findElement(By.id("checkbox1"));
		return element;
		
	}
	
	public static WebElement checkbox_checkbox2(WebDriver driver){
		
		element = driver.findElement(By.id("checkbox2"));
		return element;
		
	}
	
	public static WebElement readonlytextbox_rotb(WebDriver driver){
		
		element = driver.findElement(By.id("checkbox2"));
		return element;
		
	}
	
	public static WebElement getpromptbutton_prompt(WebDriver driver){
		
		element = driver.findElement(By.id("prompt"));
		return element;
		
	}
	
	public static WebElement getconfirmationbutton_confirm(WebDriver driver){
		
		element = driver.findElement(By.id("confirm"));
		return element;
		
	}
	
	public static WebElement locateusingnameattribute_textboxn(WebDriver driver){
		
		element = driver.findElement(By.name("textboxn"));
		return element;
		
	}
	
	public static WebElement urlothersitestopracticeautomation_compendiumdev(WebDriver driver){
		
		element = driver.findElement(By.xpath("//*[text()='compendiumdev'"));
		return element;
		
	}
	
	public static WebElement urlothersitestopracticeautomation_onlytestingblog(WebDriver driver){
		
		element = driver.findElement(By.xpath("//*[text()='onlytestingblog'"));
		return element;
		
	}
	
	public static WebElement urlothersitestopracticeautomation_testwisely(WebDriver driver){
		
		element = driver.findElement(By.xpath("//*[text()='testwisely'"));
		return element;
		
	}
	
	public static WebElement urlothersitestopracticeautomation_jqueryui(WebDriver driver){
		
		element = driver.findElement(By.xpath("//*[text()='jqueryui'"));
		return element;
		
	}
	
	public static WebElement urlothersitestopracticeautomation_theautomatedtester(WebDriver driver){
		
		element = driver.findElement(By.xpath("//*[text()='theautomatedtester'"));
		return element;
		
	}
	
	public static WebElement sameidandname_button(WebDriver driver){
		
		element = driver.findElement(By.xpath("/html/body/div[4]/div[2]/div[2]/div[2]/div[2]/div[2]/div[2]/div/div[4]/div[3]/div/aside/div[1]/div[12]/div[1]/input"));
		return element;
		
	}
	
	public static WebElement locateusingclasstextbox_classonex(WebDriver driver){
		
		element = driver.findElement(By.className("classone"));
		return element;
		
	}
	
	public static WebElement sameclassnametextbox_textbox(WebDriver driver){
		
		element = driver.findElement(By.xpath("//*[@id='HTML28']/div[1]/input"));
		return element;
		
	}
	
	public static List<WebElement> selectavehicleradiobutton_vehicle(WebDriver driver){
		
		listofelements = driver.findElements(By.name("vehicle"));
		return listofelements;
		
	}
	
	public static WebElement checkboxselectmultipleoptions_accessories(WebDriver driver){
		
		element = driver.findElement(By.className("accessories"));
		return element;
		
	}

	public static WebElement texttestdoubleclick_testdoubleclick(WebDriver driver){
		
		element = driver.findElement(By.id("testdoubleclick"));
		return element;
		
	}
	
	public static WebElement buttondelayedbuttondropdown_Dropdown(WebDriver driver){
		
		element = driver.findElement(By.xpath("//*[text()='Dropdown'"));
		return element;
		
	}
}
