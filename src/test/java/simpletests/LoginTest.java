package simpletests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;


public class LoginTest extends BaseTest {

    @Test
    public void UrlValidations() {

        String url = driver.getCurrentUrl();

        //Usar HTTPS://WWW.GOOGLE.COM/ para simular erro e ver o terminal
        Assertions.assertEquals("HTTPS://WWW.SAUCEDEMO.COM/", url.toUpperCase());
        //Correção: HTTPS://WWW.SAUCEDEMO.COM/
        System.out.println("Estamos em: " + url.toLowerCase());
    }

    @Test
    public void EmptyUsername() {

        driver.findElement(By.id("user-name")).sendKeys("");
        driver.findElement(By.name("password")).sendKeys("1234");
        driver.findElement(By.id("login-button")).click();

        String message = driver.findElement(By.cssSelector(".error-message-container")).getText();

        Assertions.assertEquals(
                "epic sadface: username is required",
                message.toLowerCase());
    }

    @Test
    public void EmptyPassword() {

        driver.findElement(By.id("user-name")).sendKeys("user");
        driver.findElement(By.name("password")).sendKeys("");
        driver.findElement(By.id("login-button")).click();

        String message = driver.findElement(By.cssSelector(".error-message-container")).getText();

        Assertions.assertEquals(
                "epic sadface: password is required",
                message.toLowerCase());
    }

    @Test
    public void CommonLogin() {

        driver.findElement(By.id("user-name")).sendKeys("usuário padrão");
        driver.findElement(By.name("password")).sendKeys("molho secreto");
        driver.findElement(By.id("login-button")).click();

        String message = driver.findElement(By.cssSelector(".error-message-container")).getText();

        Assertions.assertEquals(
                "epic sadface: username and password do not match any user in this service",
                message.toLowerCase());
    }

        @Test
        public void BlockLogin () {
            driver.findElement(By.id("user-name")).sendKeys("bloqueado_out_user");
            driver.findElement(By.name("password")).sendKeys("molho secreto");
            driver.findElement(By.id("login-button")).click();

            String message = driver.findElement(By.cssSelector(".error-message-container")).getText();

            Assertions.assertEquals(
                    "epic sadface: username and password do not match any user in this service",
                    message.toLowerCase());
        }

        @Test
        public void ProblemLogin () {
            driver.findElement(By.id("user-name")).sendKeys("problem_user");
            driver.findElement(By.name("password")).sendKeys("molho secreto");
            driver.findElement(By.id("login-button")).click();

            //Inserir Validação
            String message = driver.findElement(By.cssSelector(".error-message-container")).getText();

            Assertions.assertEquals(
                    "epic sadface: username and password do not match any user in this service",
                    message.toLowerCase());
        }

        @Test
        public void PerformanceLogin() {
            driver.findElement(By.id("user-name")).sendKeys("performance_glitch_user");
            driver.findElement(By.name("password")).sendKeys("molho secreto");
            driver.findElement(By.id("login-button")).click();

            //Inserir Validação
            String message = driver.findElement(By.cssSelector(".error-message-container")).getText();

            Assertions.assertEquals(
                    "epic sadface: username and password do not match any user in this service",
                    message.toLowerCase());
        }
    }





