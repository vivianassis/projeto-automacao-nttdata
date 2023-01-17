package simpletests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.time.Duration;


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
        driver.findElement(By.name("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        String message = driver.findElement(By.cssSelector(".error-message-container")).getText();

        Assertions.assertEquals(
                "epic sadface: username is required",
                message.toLowerCase());
    }

    @Test
    public void EmptyPassword() {

        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.name("password")).sendKeys("");
        driver.findElement(By.id("login-button")).click();

        String message = driver.findElement(By.cssSelector(".error-message-container")).getText();

        Assertions.assertEquals(
                "epic sadface: password is required",
                message.toLowerCase());
    }

    @Test
    public void CommonLogin() {

        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.name("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
       // driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));  -- CASO O TESTE FALHE POR ESPERA DO NAVEGADOR

        String cabecalho = driver.findElement(By.className("title")).getText();
        boolean valida = cabecalho.contains("PRODUCTS");

        Assertions.assertTrue(valida);
    }

        @Test
        public void BlockLogin () {
            driver.findElement(By.id("user-name")).sendKeys("locked_out_user");
            driver.findElement(By.name("password")).sendKeys("secret_sauce");
            driver.findElement(By.id("login-button")).click();

            String message = driver.findElement(By.cssSelector(".error-message-container")).getText();

            Assertions.assertEquals(
                    "epic sadface: sorry, this user has been locked out.",
                    message.toLowerCase());
        }

//        @Test
//        public void ProblemLogin () {
//            driver.findElement(By.id("user-name")).sendKeys("problem_user");
//            driver.findElement(By.name("password")).sendKeys("secret_sauce");
//            driver.findElement(By.id("login-button")).click();
//
//            //Inserir Validação
//            String message = driver.findElement(By.cssSelector(".error-message-container")).getText();
//
//            Assertions.assertEquals(
//                    "epic sadface: username and password do not match any user in this service",
//                    message.toLowerCase());
//        }

//        @Test
//        public void PerformanceLogin() {
//            driver.findElement(By.id("user-name")).sendKeys("performance_glitch_user");
//            driver.findElement(By.name("password")).sendKeys("secret_sauce");
//            driver.findElement(By.id("login-button")).click();
//
//            //Inserir Validação
//            String message = driver.findElement(By.cssSelector(".error-message-container")).getText();
//
//            Assertions.assertEquals(
//                    "epic sadface: username and password do not match any user in this service",
//                    message.toLowerCase());
//        }
    }





