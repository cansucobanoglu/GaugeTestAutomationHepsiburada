package com.hepsiburada.qa.pages;

import com.hepsiburada.qa.base.TestBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductPage extends TestBase {

    // initializing the page objects
    public ProductPage()  {

        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//button[@id='addToCart']")
    WebElement sepeteEkle;

    @FindBy(xpath = "//span[@id='cartItemCount']")
    WebElement sepetMiktar;

    @FindBy(xpath = "//input[@id='quantity']")
    WebElement miktar;

    //Actions
    public void sepeteEkle()
    {
        System.out.println("Eklenen adet: " + miktar.getAttribute("value"));
        sepeteEkle.click();
        System.out.println("Sepetteki toplam ürün adedi " + sepetMiktar.getText());
    }

    public void sepetKontrol()
    {

    }
}
