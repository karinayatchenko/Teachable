package teacheableLoginTests;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TeachableLoginTests extends BaseClass {

    private final By loginLink = By.xpath("//*[@id='navbar']/div/div/div/ul/li[1]/a");
    private final By email = By.xpath("//*[@id='user_email']");
    private final By password = By.xpath("//*[@id='user_password']");
    private final By loginButton = By.xpath("//*[@id='new_user']/div[3]/input");
    private final By errorMessage = By.xpath("/html/body/div/div/div/div/div/div/div[1]/div/div");
    private final By forgotPassword = By.xpath("//*[@id='new_user']/center/a");
    private final By sendMeInstructionButton = By.xpath("//*[@id='new_user']/center/input");
    String homepageUrl = "https://takehome.zeachable.com/";
    String loginPageUrl = "https://sso.zeachable.com/secure/123/users/sign_in?clean_login=true&reset_purchase_session=1";

    @BeforeMethod
    public void openBrowser() {
        driver = new ChromeDriver();
        driver.get("https://takehome.zeachable.com");
    }

    @Test
    public void verifyLoginLinkIsVisible() {
        driver.findElement(loginLink);
        Assert.assertTrue(isLoginLinkVisible(), "Login link does not display");
    }

    @Test
    public void verifyLoginLinkRedirectsToLoginPage() throws InterruptedException {
        driver.findElement(loginLink).click();
        Thread.sleep(1000);
        String expectedUrl = driver.getCurrentUrl();
        Assert.assertEquals(loginPageUrl, expectedUrl);
    }


    @Test
    public void verifyEmailFieldExists() throws InterruptedException {
        driver.findElement(loginLink).click();
        Thread.sleep(1000);
        driver.findElement(email);
        Assert.assertTrue(isEmailFieldVisible(), "Email input field does not display");
    }

    @Test
    public void verifyPasswordFieldExist() throws InterruptedException {
        driver.findElement(loginLink).click();
        Thread.sleep(1000);
        driver.findElement(email);
        Assert.assertTrue(isPasswordFieldVisible(), "Password input field does not display");
    }

    @Test
    public void verifyLoginButtonExists() throws InterruptedException {
        driver.findElement(loginLink).click();
        Thread.sleep(1000);
        driver.findElement(loginButton);
        Assert.assertTrue(isLoginButtonVisible(), "Login Button input field does not display");
    }

    @Test
    public void verifyForgotPasswordLinkDisplayed() throws InterruptedException {
        driver.findElement(loginLink).click();
        Thread.sleep(1000);
        driver.findElement(forgotPassword);
        Assert.assertTrue(isForgotPasswordLinkDisplayed(), "Forgot Password Link input does not display");
    }

    @Test
    public void verifyUserCanLoginWithValidCredentials() throws InterruptedException {
        driver.findElement(loginLink).click();
        Thread.sleep(1000);
        driver.findElement(email).sendKeys("takehome@test.com");
        driver.findElement(password).sendKeys("Teachable");
        driver.findElement(loginButton).click();
        String expectedUrl = driver.getCurrentUrl();
        Assert.assertEquals(homepageUrl, expectedUrl);
    }

    @Test
    public void verifyUserCannotLoginWithInvalidPassword() throws InterruptedException {
        driver.findElement(loginLink).click();
        Thread.sleep(1000);
        driver.findElement(email).sendKeys("takehome@test.com ");
        driver.findElement(password).sendKeys("qwerty");
        driver.findElement(loginButton).click();
        Assert.assertTrue(isErrorMessageVisible(), "Error message is not visible");
    }

    @Test
    public void verifyUserCannotLoginWithIncorrectEmail() throws InterruptedException {
        driver.findElement(loginLink).click();
        Thread.sleep(1000);
        driver.findElement(email).sendKeys("takehome@test.co");
        driver.findElement(password).sendKeys("Teachable");
        driver.findElement(loginButton).click();
        Assert.assertTrue(isErrorMessageVisible(), "Error message is not visible");
    }

    @Test
    public void verifyUserCannotLoginWhenBothEmailAndPasswordFieldBlank() throws InterruptedException {
        driver.findElement(loginLink).click();
        Thread.sleep(1000);
        driver.findElement(loginButton).click();
        Assert.assertTrue(isErrorMessageVisible(), "Error message is not visible");
    }

    @Test
    public void verifyUserGetsRedirectedToResetPasswordPage() throws InterruptedException {
        driver.findElement(loginLink).click();
        Thread.sleep(1000);
        driver.findElement(forgotPassword).click();
        Thread.sleep(1000);
        Assert.assertTrue(isSendMeInstructionButtonDisplayed(), "User does not redirect tp the Reset Password Page");

    }

    boolean isForgotPasswordLinkDisplayed() {
        return isElementVisible(forgotPassword, 2);
    }

    boolean isSendMeInstructionButtonDisplayed() {
        return isElementVisible(sendMeInstructionButton, 2);
    }

    boolean isLoginButtonVisible() {
        return isElementVisible(loginButton, 2);
    }

    boolean isEmailFieldVisible() {
        return isElementVisible(email, 2);
    }

    boolean isPasswordFieldVisible() {
        return isElementVisible(password, 2);
    }

    boolean isErrorMessageVisible() {
        return isElementVisible(errorMessage, 5);
    }

    boolean isLoginLinkVisible() {
        return isElementVisible(loginLink, 2);
    }

    @AfterMethod
    public void terminateBrowser() {
        driver.close();
    }
}
