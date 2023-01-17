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

        @Test
        public void ProblemLogin () {
            driver.findElement(By.id("user-name")).sendKeys("problem_user");
            driver.findElement(By.name("password")).sendKeys("secret_sauce");
            driver.findElement(By.id("login-button")).click();

            //Inserir Validação
            String imagem1 = driver.findElement(By.xpath("//*[@id=\"item_1_img_link\"]/img")).getAttribute("src");
            String imagem2 = driver.findElement(By.xpath("//*[@id=\"item_2_img_link\"]/img")).getAttribute("src");

//            Assertions.assertNotEquals(imagem1, imagem2);
//            -- O certo é serem diferentes, mas como estamos validando o login com problema,
//            vamos pedir que sejam iguais

            Assertions.assertEquals(imagem1, imagem2);

            System.out.println("Resultado: \n" + imagem1 + "\nEstá igual a: \n" + imagem2 + "\nLogo, a página está com problema.");  //-- opcional (apoio ao teste)
        }

    }





