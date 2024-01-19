import io.github.bonigarcia.wdm.WebDriverManager;
import net.datafaker.Faker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class FluxoDeCompraTests {

    private WebDriver nav;
    Faker faker = new Faker();
    String nome = faker.name().firstName();
    String sobrenome = faker.name().lastName();
    String email = faker.internet().emailAddress();


    public void abrirNavegador(){
        WebDriverManager.chromedriver().setup();
        nav = new ChromeDriver();
        nav.get("https://automationexercise.com/");
    }
    public void fecharNavegador(){
        nav.quit();
    }
    private void scrollDown(WebDriver driver) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollTo(0, document.body.scrollHeight);");
    }

    public void realizarLogin (){
        nav.findElement(By.xpath(Elementos.Acessar_Login)).click();
        nav.findElement(By.xpath(Elementos.Login_Email)).sendKeys("ingrid.secundo@dbccompany.com.br");
        nav.findElement(By.xpath(Elementos.Login_Senha)).sendKeys("123456");
        nav.findElement(By.xpath(Elementos.Btn_Login)).click();
    }

    public void cadastrarUsuario (){
        nav.findElement(By.xpath(Elementos.Acessar_Login)).click();
        nav.findElement(By.xpath(Elementos.NewUser_Name)).sendKeys(nome);
        nav.findElement(By.xpath(Elementos.NewUser_Email)).sendKeys(email);
        nav.findElement(By.xpath(Elementos.Btn_Signup)).click();
        nav.findElement(By.id(Elementos.Signup_Title)).click();
        nav.findElement(By.id(Elementos.Signup_Password)).sendKeys("123456");
        nav.findElement(By.id(Elementos.Signup_Day)).sendKeys("4");
        nav.findElement(By.id(Elementos.Signup_Month)).sendKeys("May");
        nav.findElement(By.id(Elementos.Signup_Year)).sendKeys("1992");
        nav.findElement(By.id(Elementos.Signup_Name)).sendKeys(nome);
        nav.findElement(By.id(Elementos.Signup_Sobrenome)).sendKeys(sobrenome);
        nav.findElement(By.id(Elementos.Signup_Endereco)).sendKeys("Rua A");
        nav.findElement(By.id(Elementos.Signup_Estado)).sendKeys("Sergipe");
        nav.findElement(By.id(Elementos.Signup_Cidade)).sendKeys("Aracaju");
        nav.findElement(By.id(Elementos.Signup_CEP)).sendKeys("49001171");
        nav.findElement(By.id(Elementos.Signup_Telefone)).sendKeys("79999999999");
        scrollDown(nav);
        nav.findElement(By.xpath(Elementos.Btn_CreateAccount)).click();
        nav.findElement(By.xpath(Elementos.Btn_CreateContinue)).click();
    }

    public void realizarCompra (){
        nav.findElement(By.xpath(Elementos.Menu_Products)).click();
        nav.findElement(By.id(Elementos.Pesquisar_Produto)).sendKeys("Stylish Dress");
        nav.findElement(By.id(Elementos.Bnt_Pesquisar)).click();
        scrollDown(nav);
        nav.findElement(By.xpath(Elementos.View_Product)).click();
        nav.findElement(By.id(Elementos.Qtd_Produto)).clear();
        nav.findElement(By.id(Elementos.Qtd_Produto)).sendKeys("3");
        nav.findElement(By.xpath(Elementos.Btn_AddCarrinho)).click();
        nav.findElement(By.xpath(Elementos.Btn_ContinuarComprando)).click();
        nav.findElement(By.xpath(Elementos.Menu_Products)).click();
        nav.findElement(By.id(Elementos.Pesquisar_Produto)).sendKeys("Beautiful Peacock Blue Cotton Linen Saree");
        nav.findElement(By.id(Elementos.Bnt_Pesquisar)).click();
        scrollDown(nav);
        nav.findElement(By.xpath(Elementos.View_Product)).click();
        nav.findElement(By.id(Elementos.Qtd_Produto)).clear();
        nav.findElement(By.id(Elementos.Qtd_Produto)).sendKeys("2");
        nav.findElement(By.xpath(Elementos.Btn_AddCarrinho)).click();
        nav.findElement(By.xpath(Elementos.Btn_ContinuarComprando)).click();
        nav.findElement(By.xpath(Elementos.Menu_Products)).click();
        nav.findElement(By.id(Elementos.Pesquisar_Produto)).sendKeys("Men Tshirt");
        nav.findElement(By.id(Elementos.Bnt_Pesquisar)).click();
        scrollDown(nav);
        nav.findElement(By.xpath(Elementos.Btn_AddCart)).click();
        nav.findElement(By.xpath(Elementos.Btn_ViewCart)).click();
        nav.findElement(By.xpath(Elementos.Btn_Checkout)).click();
        scrollDown(nav);
        nav.findElement(By.xpath(Elementos.Btn_PlaceOrder)).click();
        nav.findElement(By.xpath(Elementos.NomeCartao)).sendKeys("Ingrid S Secundo");
        nav.findElement(By.xpath(Elementos.NumeroCartao)).sendKeys("1234 5678 1234 5678");
        nav.findElement(By.xpath(Elementos.CVCCartao)).sendKeys("123");
        nav.findElement(By.xpath(Elementos.ExpMesCartao)).sendKeys("01");
        nav.findElement(By.xpath(Elementos.ExpAnoCartao)).sendKeys("2030");
        nav.findElement(By.id(Elementos.Btn_PayConfirmOrder)).click();
        String msgConfirmacao = nav.findElement(By.xpath(Elementos.msgConfirmacao)).getText();
        Assertions.assertEquals("Congratulations! Your order has been confirmed!",msgConfirmacao);
        nav.findElement(By.xpath(Elementos.BtnFinalizar)).click();
    }

    @Test
    public void realizarCompraUsuarioCadastradoTest(){
        abrirNavegador();
        realizarLogin();
        realizarCompra();
        fecharNavegador();
    }

    @Test
    public void realizarCompraUsuarioNovoTest(){
        abrirNavegador();
        cadastrarUsuario();
        realizarCompra();
        fecharNavegador();
    }

}
