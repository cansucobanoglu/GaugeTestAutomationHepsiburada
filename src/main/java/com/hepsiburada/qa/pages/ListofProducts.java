package com.hepsiburada.qa.pages;

import com.hepsiburada.qa.base.TestBase;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

public class ListofProducts extends TestBase {

    // initializing the page objects
    public ListofProducts() {

        PageFactory.initElements(driver, this);
    }

    JavascriptExecutor js =(JavascriptExecutor) driver;

    @FindBy(xpath = "//input[@placeholder='En az']")
    WebElement enAz;

    @FindBy(xpath = "//input[@placeholder='En çok']")
    WebElement enCok;


    // Actions
    public void markaSec()
    {
        //Rastgele bir marka seçilir.
        WebElement markaMenu = driver.findElement(By.xpath("//li[@class='box-container brand']"));
        List<WebElement> markaList = markaMenu.findElements(By.xpath(" //label[contains(@for,'brand')]"));
        System.out.println("Marka sayısı: "+markaList.size());
        Random randomMarka = new Random();
        int getRandom = randomMarka.nextInt(markaList.size());
        WebElement randomElement = markaList.get(getRandom);
        randomElement.click();

    }


}
