package ru.ibs.rgs;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.ibs.base.BaseTests;

public class FillRequestDMSFormTest extends BaseTests {


    @Test
    void testOpenRequestForm() {
        WebElement cookie = driver.findElement(By.xpath("//button[contains(text(), 'Хорошо')]"));
        cookie.click();
        WebElement baseMenu = driver.findElement(By.xpath("//a[contains(text(), 'Компаниям')]"));
        baseMenu.click();
        waitUntilElementToBeVisible(By.xpath("//span[contains(text(), 'Здоровье')]"));
        WebElement nextMenu = driver.findElement(By.xpath("//span[contains(text(), 'Здоровье')]"));
        waitUntilElementToBeClickable(nextMenu);
        nextMenu.click();
        WebElement targetMenu = driver.findElement(By.xpath("//a[contains(text(), 'Добровольное медицинское страхование')]"));
        waitUntilElementToBeVisible(targetMenu);
        targetMenu.click();

        wait.until(ExpectedConditions.textToBe(By.xpath("//h1[@class='title word-breaking title--h2']"), "Добровольное медицинское страхование"));
    }

    @Test
    void testFillRequestForm() {
        WebElement sendButton = driver.findElement(By.xpath("//span[contains(text(), 'Отправить заявку')]"));
        sendButton.click();

        String fieldXPath = "//input[@name='%s']";
        waitUntilElementToBeClickable(driver.findElement(By.xpath(String.format(fieldXPath, "userName"))));
        fillInputField(driver.findElement(By.xpath(String.format(fieldXPath, "userName"))), "Иванов Иван Иванович");
        fillInputPhoneField(driver.findElement(By.xpath(String.format(fieldXPath, "userTel"))), "911", "111", "1111");
        fillInputField(driver.findElement(By.xpath(String.format(fieldXPath, "userEmail"))), "superEmail");
        fillInputField(driver.findElement(By.xpath("//input[@class='vue-dadata__input']")), "Чудогород, Чудоулица 3");

        WebElement check = driver.findElement(By.xpath("//div[@class='checkbox-body form__checkbox']"));
        check.click();
        check.submit();

        waitUntilElementToBeVisible(By.xpath("//span[contains(text(), 'Введите корректный адрес электронной почты')]"));
    }
}
