package ru.ibs.rgs;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.ibs.base.BaseTests;

public class FillRequestDMSFormTest extends BaseTests {

  @ParameterizedTest(name = "тест {index} = {0}")
  @CsvFileSource(resources = "/personal_data.csv")
  void testFillRequestForm(
      String name, String tel1, String tel2, String tel3, String email, String address) {

    if (name.equals("Иванов Иван Иванович")) {
      WebElement cookie = driver.findElement(By.xpath("//button[contains(text(), 'Хорошо')]"));
      waitUntilElementToBeClickable(cookie);
      cookie.click();
    }

    WebElement baseMenu = driver.findElement(By.xpath("//a[contains(text(), 'Компаниям')]"));
    waitUntilElementToBeVisible(baseMenu);
    waitUntilElementToBeClickable(baseMenu);
    baseMenu.click();

    WebElement nextMenu = driver.findElement(By.xpath("//span[contains(text(), 'Здоровье')]"));
    waitUntilElementToBeVisible(nextMenu);
    waitUntilElementToBeClickable(nextMenu);
    nextMenu.click();

    WebElement targetMenu =
        driver.findElement(
            By.xpath("//a[contains(text(), 'Добровольное медицинское страхование')]"));
    waitUntilElementToBeVisible(targetMenu);
    waitUntilElementToBeClickable(targetMenu);
    targetMenu.click();

    wait.until(
        ExpectedConditions.textToBe(
            By.xpath("//h1[@class='title word-breaking title--h2']"),
            "Добровольное медицинское страхование"));

    WebElement sendButton =
        driver.findElement(By.xpath("//span[contains(text(), 'Отправить заявку')]"));
    waitUntilElementToBeClickable(sendButton);
    sendButton.click();

    String fieldXPath = "//input[@name='%s']";
    fillInputField(driver.findElement(By.xpath(String.format(fieldXPath, "userName"))), name);
    fillInputPhoneField(
        driver.findElement(By.xpath(String.format(fieldXPath, "userTel"))), tel1, tel2, tel3);
    fillInputField(driver.findElement(By.xpath(String.format(fieldXPath, "userEmail"))), email);
    fillInputField(driver.findElement(By.xpath("//input[@class='vue-dadata__input']")), address);

    WebElement check = driver.findElement(By.xpath("//div[@class='checkbox-body form__checkbox']"));
    check.click();
    check.submit();

    wait.until(
        ExpectedConditions.textToBe(
            By.xpath("//span[contains(text(), 'Введите корректный адрес электронной почты')]"),
            "Введите корректный адрес электронной почты"));

  }
}
