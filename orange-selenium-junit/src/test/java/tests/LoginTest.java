package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.LoginPage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/*
* Classe de teste: aqui a gente escreve o que testar e que esperar.
* Usa as ações já prontas de LoginPage, sem se preocupar com "como" o Selenium acha os elementos.
 */
public class LoginTest {

    WebDriver driver;       //Reprensenta o navegador
    LoginPage loginPage;    // Representa a tela de login (nosso page object)

    // roda antes de cada teste (@Test) - prepara o ambiente
    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();  //baixa/configura o drive certo do chrome

        // adiciona opções para evitar falhas de comunicação com o chrome
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);              //abre o navegador chrome
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");  //acessa a página
        loginPage = new LoginPage(driver);  //cria o objeto na tela de login
    }

    // teste: tenta logar com o usuario e senha invalidos
    // espera que apareça a mensagem "invalid credentials"
    @Test
    public void loginComCredenciaisInvalidas_deveExibirMensagemdeErro() {
        loginPage.acessarUsuario("ususarioInvalido"); // digita o usuario errado
        loginPage.acessarSenha("senhaInvalida");      // digita a senha errada
        loginPage.clicarLogin();                      // clica no botão login

        String mensagem = loginPage.getMensagemErro(); // pega a mensagem de erro exibida
        assertEquals("Invalid credentials", mensagem); // compara com o esperado
    }

    @Test
    public void loginComCredenciaisValidas_deveExibirDashboard() {
        try {
            loginPage.acessarUsuario("Admin");
            loginPage.acessarSenha("admin123");
            loginPage.clicarLogin();

            boolean dashboardVisivel = loginPage.dashboardEstaVisivel();
            assertTrue(dashboardVisivel, "O Dashboard deveria estar visível após login válido");
        }catch (AssertionError | Exception e){
            tirarScreenshot("loginComCredenciaisValidas");
            throw e;
        }
    }

    // salvar um print da tela no momento da falha, dentro da pasta "screenshots"
    private void tirarScreenshot(String nomeDoTeste) {
        try {
            File origem = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            Files.createDirectories(Paths.get("screenshots"));
            File destino = new File("screechots/" + nomeDoTeste + ".png");
            Files.copy(origem.toPath(), destino.toPath(), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Screenshot salvo em: " + destino.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Não foi possível salvar o screenshot: " + e.getMessage());
        }
    }

    // Roda depois de cada teste - fecha o navegador pra não ficar aberto
    @AfterEach
    public void tearDown(){
        driver.quit();
    }
}