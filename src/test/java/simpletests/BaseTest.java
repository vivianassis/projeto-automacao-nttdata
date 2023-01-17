package simpletests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class BaseTest {

    public WebDriver driver;

    @BeforeEach
    public void setUp() {
//        ChromeOptions chromeOptions = new ChromeOptions();
//        chromeOptions.addArguments("--lang=pt");
        driver = new ChromeDriver();
        System.setProperty("webdriver.chrome.driver", "C:\\driver\\chromedriver_win32\\chromedriver.exe");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://www.saucedemo.com/");
    }
    @AfterEach
    public void quitBronwser() {
        driver.quit();
    }

    //método genérico usado com frequencia em classes filhas
    public void login(String user, String password){
        driver.findElement(By.id("user-name")).sendKeys(user);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.id("login-button")).click();
    }
}
