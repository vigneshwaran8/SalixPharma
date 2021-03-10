package Login;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Variables.Store;

public class SalixCl{
	
	
	public static WebDriver driver;
	public static WebDriverWait wait;

	@BeforeTest
	public void beforeTest() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Novastrid IT\\Downloads\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}

	@Test (dependsOnMethods ="datetime" )
    public void logout() throws InterruptedException  { 
		wait = new WebDriverWait (driver, 10);
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".user-icon [title='Logout']")));
		WebElement logoutbtn = driver.findElement(By.cssSelector(".user-icon [title='Logout']"));
		logoutbtn.click();
		WebElement popupbtnYes = driver.findElement(By.cssSelector("div#logoutconfirm > .modal-dialog a[title='Yes']"));
		popupbtnYes.click();
		Thread.sleep(5000);
		if(wait.until(ExpectedConditions.alertIsPresent())!=null) {
			 Alert alert = driver.switchTo().alert();
             String alertText = alert.getText();
             System.out.println("Alert data: " + alertText);
             alert.accept();
             Thread.sleep(5000);
		}
	}
	
	@Test (groups = "login")
    public void login() throws InterruptedException {
	    driver.get("https://salixv3qa.radiusdirect.net/user");  
	    Thread.sleep(2000);
	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	    wait = new WebDriverWait (driver, 10);
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[name='email']")));
	    WebElement username = driver.findElement(By.cssSelector("input[name='email']"));
	    username.sendKeys(Store.userName);
	    WebElement password = driver.findElement(By.cssSelector("input[name='password']"));
	    password.sendKeys(Store.password);
	    WebElement loginbutton = driver.findElement(By.cssSelector(".text-uppercase"));
	    loginbutton.click();
	    Thread.sleep(5000);
	    }

	@Test (dependsOnGroups = "login")
    public void create() throws InterruptedException {
		wait = new WebDriverWait (driver, 10);
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".nav.navbar-nav .fa.fa-users")));
	    WebElement createbtn = driver.findElement(By.cssSelector(".nav.navbar-nav .fa.fa-users"));
	    createbtn.click();
	    Thread.sleep(5000);
	    WebElement product = driver.findElement(By.cssSelector("#products .each-topic-sec:nth-of-type(1) h4"));
	    product.click();
	    Thread.sleep(5000);
	    }
	
	@Test (dependsOnMethods ="create")
	 public void type() throws InterruptedException {
		wait = new WebDriverWait (driver, 10);
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div:nth-of-type(1) > .each-topic .company-info.type-icon")));
	    WebElement typechoose = driver.findElement(By.cssSelector("div:nth-of-type(1) > .each-topic .company-info.type-icon"));
	    typechoose.click();
	    Thread.sleep(5000);
    	}
	
	@Test (dependsOnMethods ="type")
	 public void presenettitle() throws InterruptedException {
		if (driver.findElements(By.cssSelector(".each-topic.ng-star-inserted h4")).get(0).isDisplayed()) {
		wait = new WebDriverWait (driver, 10);
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".each-topic.ng-star-inserted h4")));
	    WebElement titlechoose = driver.findElement(By.cssSelector(".each-topic.ng-star-inserted h4"));
	    titlechoose.click();
		}
	}
	
	@Test (dependsOnMethods ="presenettitle")
	 public void datetime() throws InterruptedException {
		wait = new WebDriverWait (driver, 10);
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#calendar .today.day")));
	    WebElement choosedate = driver.findElement(By.cssSelector("#calendar .today.day"));
	    choosedate.click();
	    Thread.sleep(5000);
	    WebElement choosetime = driver.findElement(By.cssSelector("[for='start8\\:45 PM'] .avl"));
	    choosetime.click();
	    Thread.sleep(5000);
	}
	
	@AfterTest
	public void afterTest() {
		driver.close();
		driver.quit();
	}
}