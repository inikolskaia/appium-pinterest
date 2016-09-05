import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;

import java.net.MalformedURLException;
import java.net.URL;

public class pinterest {
	private AppiumDriver driver;
	WebElement element;
 
	@Before
	public void setUp() throws MalformedURLException{
 
	DesiredCapabilities capabilities = new DesiredCapabilities();
	
	capabilities.setCapability("platformName", "android");
	capabilities.setCapability("testobject_device", "LG_Nexus_5X_real");
	capabilities.setCapability("testobject_api_key", "319C2953432D4B5F977997844F96BF15");
	capabilities.setCapability("testobject_app_id", "1");
	capabilities.setCapability("appPackage", "com.pinterest");
	
	driver = new AndroidDriver(new URL("http://appium.testobject.com/wd/hub"), capabilities);
	}
	
	@After
	public void tearDown() {
		driver.quit(); 
	}
	
	@Test  
    public void pinterest() throws Exception {  
 
	   	System.out.println("Starting test " + new Object(){}.getClass().getEnclosingMethod().getName());
		PinterestApp pinterest = new PinterestApp(driver);
		
		pinterest.registration("testytest@de.de", "gkhuhlo78", "Max Mustermann", "28");
		pinterest.selectInteresting();
		pinterest.createBoard("Testing UI");

		Assert.assertEquals("Testing UI", pinterest.boardName());
	   	
		System.out.println("Ending test " + new Object(){}.getClass().getEnclosingMethod().getName()); 
    }
	
	public static class PinterestApp {
		private final AppiumDriver driver;
		
		@AndroidFindBy(xpath = "//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[1]/android.widget.EditText[1]")
		private WebElement email;
		
		@AndroidFindBy(id = "com.pinterest:id/continue_email_bt")
		private WebElement continueBt;
		
		@AndroidFindBy(id = "com.pinterest:id/password")
		private WebElement password;
		
		@AndroidFindBy(id = "com.pinterest:id/next_bt")
		private WebElement nextBt;
		
		@AndroidFindBy(id = "com.pinterest:id/full_name")
		private WebElement fullName;
		
		@AndroidFindBy(id = "com.pinterest:id/age_et")
		private WebElement age;

		@AndroidFindBy(id = "com.pinterest:id/male_bt")
		private WebElement male;
		
		@AndroidFindBy(id = "com.pinterest:id/done_bt")
		private WebElement doneBt;
		
		@AndroidFindBy(xpath = "//*[@text='Design']")
		private WebElement design;
		
		@AndroidFindBy(xpath = "//*[@text='Art']")
		private WebElement art;
		
		@AndroidFindBy(xpath = "//*[@text='Photography']")
		private WebElement photography;
		
		@AndroidFindBy(xpath = "//*[@text='Travel']")
		private WebElement travel;
		
		@AndroidFindBy(xpath = "//*[@text='Technology']")
		private WebElement technology;
		
		@AndroidFindBy(id = "com.pinterest:id/nux_interests_picker_continue_btn")
		private WebElement intContinueBt;
		
		@AndroidFindBy(id = "com.pinterest:id/profile_menu_view")
		private WebElement profileMenu;
		
		@AndroidFindBy(id = "com.pinterest:id/board_add_iv")
		private WebElement addBoard;
		
		@AndroidFindBy(id = "com.pinterest:id/board_name_et")
		private WebElement boardName;
		
		@AndroidFindBy(id = "com.pinterest:id/board_name_et")
		private WebElement createBt;
		
		@AndroidFindBy(xpath = "//*[@text='Testing UI']")
		private WebElement boardValue;
		
		public PinterestApp(AppiumDriver driver) {
			this.driver = driver;
			PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		}

		public void registration(String email_value, String password_value, String name_value, String age_value) {
			email.sendKeys(email_value);
			continueBt.click();
			password.sendKeys(password_value);
			nextBt.click();
			fullName.sendKeys(name_value);
			nextBt.click();
			age.sendKeys(age_value);
			nextBt.click();
			male.click();
			doneBt.click();			
		}
		
		public void selectInteresting() {
			design.click();
			art.click();
			photography.click();
			travel.click();
			technology.click();
			intContinueBt.click();
		}
		
		public void createBoard(String search_words) {
			profileMenu.click();
			addBoard.click();
			boardName.sendKeys(search_words);
			createBt.click();
		}

		public Object boardName() {
			return boardValue.getText();
		}
	}

}
