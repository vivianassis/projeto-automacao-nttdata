package simpletests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;


public class LoginTest extends BaseTest {

    @Test
    public void UrlValidations() {

        String url = driver.getCurrentUrl();
        Assertions.assertEquals("HTTPS://WWW.SAUCEDEMO.COM/", url.toUpperCase());
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
        login("standard_user", "secret_sauce");

        String cabecalho = driver.findElement(By.className("title")).getText();
        boolean valida = cabecalho.contains("PRODUCTS");

        Assertions.assertTrue(valida);
    }

//    //OU, simplificando...
//  @Test
//  public void CommomSampleLogin(){
//      login("stardard_user","secret_sauce");
//      Assertions.assertTrue(driver.findElement(By.className("title")).getText().contains("PRODUCTS"));
//  }

        @Test
        public void BlockLogin () {
            login("locked_out_user", "secret_sauce");

            String message = driver.findElement(By.cssSelector(".error-message-container")).getText();

            Assertions.assertEquals(
                    "epic sadface: sorry, this user has been locked out.",
                    message.toLowerCase());
        }

        @Test
        public void ProblemLogin () {
            login("problem_user", "secret_sauce");

            String imagem1 = driver.findElement(By.xpath("//*[@id=\"item_1_img_link\"]/img")).getAttribute("src");
            String imagem2 = driver.findElement(By.xpath("//*[@id=\"item_2_img_link\"]/img")).getAttribute("src");

            //Inserir Validação
            Assertions.assertEquals(imagem1, imagem2);
        }
}