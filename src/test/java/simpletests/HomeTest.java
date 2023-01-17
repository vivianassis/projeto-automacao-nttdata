package simpletests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

public class HomeTest extends BaseTest{
    String user = "standard_user";
    String password = "secret_sauce";

    @Test
    public void SeProdutoValido(){
    //Produto válido aparece e está relacionado a um preço válido
        login(user,password);

        Assertions.assertTrue(driver.findElement(By.className("inventory_item")).isDisplayed()
                && driver.findElement(By.className("inventory_item_price")).getText().contains("$"));
    }

    @Test
    public void validarBotaoAdicionarAoCarrinho(){
        //Produto removido do carrinho altera na Home:
        // 1- o Botão E 2- o 'número' no ícone 'Carrinho'
        // Ambas as condições, juntas.
        // Portanto, é inválida a ocorrência de apenas uma delas,
        // sendo necessário validar ambas as condições

        login(user,password);
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();


        Assertions.assertTrue(driver.findElement(By.id("remove-sauce-labs-backpack")).isDisplayed()
                && driver.findElement(By.className("shopping_cart_badge")).isDisplayed());
    }

    @Test
    public void validarBotaoRemoverDoCarrinho(){

        login(user,password);

        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        String adicionadoAoCarrinho = driver.findElement(By.className("shopping_cart_link")).getText();

        driver.findElement(By.id("remove-sauce-labs-backpack")).click();
        String retiradoDoCarrinho = driver.findElement(By.className("shopping_cart_link")).getText();

        Assertions.assertTrue(driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).isDisplayed());
        //&& driver.findElement(By.className("shopping_cart_link")).getText().isEmpty());
        // --Válida caso adicione apenas um item ao carrinho e retire
        Assertions.assertNotEquals(adicionadoAoCarrinho, retiradoDoCarrinho);
        //--Válida quando adicionamos um ou mais de um 'item' ao carrinho;
    }

    @Test
    public void validarRetornoDoCarrinhoParaHome(){
        login(user, password);

        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        driver.findElement(By.className("shopping_cart_link")).click();

        Assertions.assertEquals("https://www.saucedemo.com/cart.html", driver.getCurrentUrl());

        driver.findElement(By.name("continue-shopping")).click();

        Assertions.assertEquals("https://www.saucedemo.com/inventory.html", driver.getCurrentUrl());
    }
}