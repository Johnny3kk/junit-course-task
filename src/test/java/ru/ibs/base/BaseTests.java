package ru.ibs.base;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class BaseTests {

  protected static WebDriver driver;
  protected static WebDriverWait wait;

  @BeforeAll
  static void before() {
    System.setProperty("webdriver.gecko.driver", "src/test/resources/geckodriver.exe");
    driver = new FirefoxDriver();
    driver.manage().window().maximize();
    driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
    driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    wait = new WebDriverWait(driver, 20, 1000);
  }

  @BeforeEach
  void beforeEach() {
    driver.get("https://www.rgs.ru");
  }

  @AfterAll
  static void after() {
    driver.quit();
  }

  protected void waitUntilElementToBeClickable(WebElement element) {
    wait.until(ExpectedConditions.elementToBeClickable(element));
  }

  protected void waitUntilElementToBeVisible(By locator) {
    wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
  }

  protected void waitUntilElementToBeVisible(WebElement element) {
    wait.until(ExpectedConditions.visibilityOf(element));
  }

  protected void scrollToElementJs(WebElement element) {
    JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
    javascriptExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
  }

  protected void fillInputField(WebElement element, String value) {
    scrollToElementJs(element);
    waitUntilElementToBeVisible(element);
    waitUntilElementToBeClickable(element);
    element.click();
    element.sendKeys(Keys.chord(Keys.CONTROL, "a"));
    element.sendKeys(Keys.DELETE);
    element.clear();
    element.sendKeys(value);
    boolean checkFlag = wait.until(ExpectedConditions.attributeContains(element, "value", value));
    Assertions.assertTrue(checkFlag, "Поле было заполнено неверно.");
  }

  protected void fillInputPhoneField(
      WebElement element, String value1, String value2, String value3) {
    scrollToElementJs(element);
    waitUntilElementToBeVisible(element);
    element.click();
    element.sendKeys(Keys.chord(Keys.CONTROL, "a"));
    element.sendKeys(Keys.DELETE);
    element.clear();
    element.sendKeys(value1);
    element.sendKeys(value2);
    element.sendKeys(value3);
    boolean checkFlag =
        wait.until(
            ExpectedConditions.attributeContains(
                element, "value", "+7 (" + value1 + ") " + value2 + "-" + value3));
    Assertions.assertTrue(checkFlag, "Поле было заполнено неверно.");
  }
}
