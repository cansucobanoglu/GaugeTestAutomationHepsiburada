package com.hepsiburada.qa.pages;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.hepsiburada.qa.base.TestBase;

public class LoginPage extends TestBase {



    // initializing the page objects
    public LoginPage()  {

        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "email")
    WebElement email;

    @FindBy(id = "password")
    WebElement password;

    @FindBy(xpath = "//button[@class='btn full btn-login-submit']")
    WebElement giris;

    @FindBy(className = "hepsiburada-logo")
    WebElement hbLogo;

     // Actions

    public String getLoginpageTitle() {

        return driver.getTitle();

    }

    public Homepage login(String un, String pwd) 	 {


        email.sendKeys(un);
        password.sendKeys(pwd);
        giris.click();

        return new Homepage();

    }
}

