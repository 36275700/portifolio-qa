package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

/**
 * Page object da tela de Login do OrangeHRM.
 * Aqui ficam só os elementos da tela e as ações possivéis nela.
 * A lógica de teste (o que verificar) fica na classe LoginTest.
 */
public class LoginPage {

    private WebDriver driver; // controla o navegador (abre, clica e digita)
    private WebDriverWait wait; // espera os elementos aparecerem ates de usar

    // Localizadores: onde cada elemento está na página (como o Selenium acha eles)
    private By campoUsuario = By.name("username");
    private By campoSenha = By.name("password");
    private By botaoLogin = By.cssSelector("button[type='submit']");
    private By mensagemErro = By.className("oxd-alert-content-text");
    private By tituloDashboard = By.xpath("//h6[text()='Dashboard']");

    // Construtor: roda quando você cria "new LoginPage(driver)" no teste
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        // espera até 10 segundos por qualquer elemento antes de desistir
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    // Digita o usuário no campo login
    // Espera o campo estar visível antes (o OrangeHRM demora pra carregar)
    public void acessarUsuario(String usuario) {
        WebElement campo = wait.until(ExpectedConditions.visibilityOfElementLocated(campoUsuario));
        campo.sendKeys(usuario);
    }

    // Digita a senha no campo de login
    public void acessarSenha(String senha){
        driver.findElement(campoSenha).sendKeys(senha);
    }

    // Clica no botão de Login
    public void clicarLogin() {
        driver.findElement(botaoLogin).click();
    }

    // Pega o texto da mensagem de erro que aparece após o login inválido
    // Espera a mensagem aparecer na tela antes de tentar ler
    public String getMensagemErro() {
        WebElement erro = wait.until(ExpectedConditions.visibilityOfElementLocated(mensagemErro));
        return erro.getText();
    }

    // confirma se o login deu certo checando o titulo "Dashboard"
    public boolean dashboardEstaVisivel() {
        WebElement dashboard = wait.until(ExpectedConditions.visibilityOfElementLocated(tituloDashboard));
        return dashboard.isDisplayed();
    }
}
