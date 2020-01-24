package com.hepsiburada.qa.pages;

import com.csvreader.CsvWriter;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.hepsiburada.qa.base.TestBase;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.channels.SelectableChannel;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.WeakHashMap;

public  class Homepage extends TestBase {

    Actions action = new Actions(driver);
    JavascriptExecutor js =(JavascriptExecutor) driver;

     // initializing the page objects
    public Homepage() {

        PageFactory.initElements(driver, this);
    }

    @FindBy(id="myAccount")
    WebElement giris1;

    @FindBy(xpath = "//li[contains(@data-bind,'Giriş Yap')]")
    WebElement giris2;

    @FindBy(xpath = "//input[@placeholder='En az']")
    WebElement enAz;

    @FindBy(xpath = "//input[@placeholder='En çok']")
    WebElement enCok;

    @FindBy(xpath = "//button[@class='button small']")
    WebElement button_fiyatSec;

    @FindBy(xpath = "//span[@class='cart-copy']")
    WebElement sepetClick;

    @FindBy(xpath = "//div[@class='total-price']")
    WebElement totalPrice;


    @FindBy(xpath = "//button[@id='addToCart']")
    WebElement sepeteEkle;

    @FindBy(xpath = "//span[@id='cartItemCount']")
    WebElement sepetMiktar;

    @FindBy(xpath = "//input[@id='quantity']")
    WebElement miktar;

    @FindBy(xpath = "//input[@id='first-name']")
    WebElement firstName;

    @FindBy(xpath = "//input[@id='last-name']")
    WebElement lastName;

    @FindBy(xpath = "//input[@id='address-name']")
    WebElement adrsName;

    @FindBy(xpath = "//textarea[@id='address']")
    WebElement adrs;

    @FindBy(xpath = "//div[@class='fancybox-wrap fancybox-desktop fancybox-type-ajax modal modal-address fancybox-opened']//div[2]//div[1]//div[1]//button[1]")
    WebElement sehirDropDown;

    @FindBy(xpath = "//input[@id='phone']")
    WebElement telefon;

    @FindBy(xpath = "//button[@title='Seçiniz...']")
    WebElement ilceDropDown;

    @FindBy (xpath = "//span[@class='filter-option pull-left'][contains(text(),'Seçiniz...')]")
    WebElement mahalle;

    @FindBy (xpath = "//span[contains(text(),'Banka Kart')]")
    WebElement krediKart;

    @FindBy (xpath = "//input[@id='card-no']")
    WebElement cardno;

    @FindBy(xpath = "//input[@id='holder-Name']")
    WebElement holderName;

    @FindBy(xpath = "//button[@title='Ay']")
    WebElement monthButton;

    @FindBy(xpath = "//div[@class='btn-group bootstrap-select required dropup open']//li[3]")
    WebElement month;

    @FindBy(xpath = "//span[@class='filter-option pull-left'][contains(text(),'Yıl')]")
    WebElement yearButton;

    @FindBy(xpath = "//span[contains(text(),'2024')]")
    WebElement year;

    @FindBy(xpath = "//input[@id='cvc']")
    WebElement cvc;

    // Actions
    public  LoginPage goLoginPage() {
        action.moveToElement(giris1).perform();
        giris2.click();

        return new LoginPage();
    }
    public String getHomePageTitle() {

        return driver.getTitle();
    }

    public void Random() throws InterruptedException
    {
        //Ana kategori seçilir
        WebElement menuArea = driver.findElement(By.xpath("//div[@class='navigationWrapper']"));
        List<WebElement> menuList =  menuArea.findElements(By.xpath("//div/ul/li/a/span"));
        System.out.println("Toplam menü sayısı: " +menuList.size());

        Random random= new Random();
        int t = random.nextInt(menuList.size());
        WebElement randomElement = menuList.get(t);

        randomElement.click();
        System.out.println(t+1 + ". menüye tıklandı.");
        Thread.sleep(3000);

        if(t==0){
            WebElement elektronikMenu = driver.findElement((By.xpath("//ul[@class='nav-home']")));
            List<WebElement> elektronikList = elektronikMenu.findElements(By.xpath("//li/div/div/div/div/ul/li/a"));
            System.out.println("Toplam alt kategori: "+elektronikList.size());
            WebElement randomElektronik = elektronikList.get(random.nextInt(elektronikList.size()));
            randomElektronik.click();

        }
        else
        {
            WebElement otherMenu = driver.findElement((By.xpath("//div[@class='col lg-3 col-md-3 col-sm-3 menus']")));
            List<WebElement> modaMenuList = otherMenu.findElements(By.xpath("//ul/li/ul/li/a"));
            System.out.println("Number of alt kategori: " +modaMenuList.size() );
            WebElement randomOther = modaMenuList.get(random.nextInt(modaMenuList.size()));
            randomOther.click();
        }

    }

    public void randomUrun() throws InterruptedException
    {
        //Rastgele bir ürün seçilir
        WebElement urunMenu = driver.findElement(By.xpath("//ul[contains(@class,'product-list results')]"));
        List<WebElement> urunList = urunMenu.findElements(By.xpath("//span[contains(text(),'TL')]"));
        List<WebElement> urunIsimList = urunMenu.findElements(By.xpath("//div[contains(@class,'product-detail')]//h3"));

        Random randomUrun = new Random();

        WebElement randomElement = urunList.get(randomUrun.nextInt(urunList.size()));

        String listeUrunTutar = randomElement.getText();
        System.out.println("Liste ürün tutarı: " + listeUrunTutar);

        randomElement.click();
        Thread.sleep(3000);
        sepeteEkle();
        sepeteGit();

        WebElement sepetTutarMenu = driver.findElement(By.xpath("//ul[@class='cart-item-list']"));
        List<WebElement> sepetteTutarList = driver.findElements(By.xpath("//ul[@class='cart-item-list']//div[@class='price']//span"));
        String sepetteTutar = sepetteTutarList.get(sepetteTutarList.size()-1).getText();
        System.out.println("Eklenen ürünün sepetteki tutarı: "+ sepetteTutar);

    }

    public void adetArttir() throws InterruptedException
    {
        List<WebElement> plus = driver.findElements(By.xpath("//button[@class='increase']"));
        plus.get(plus.size()-1).click();
        Thread.sleep(2000);

    }

    public void toplamTutar()
    {
        WebElement toplamTutar = driver.findElement(By.xpath("//strong[@data-bind ='text: cartItemPrice']"));
        System.out.println("Ürünlerin toplam tutarı: "+ toplamTutar.getText() +" TL");

        WebElement toplamKargo = driver.findElement(By.xpath("//strong[@data-bind ='text: shippingPrice']"));
        System.out.println("Toplam kargo tutar: "+ toplamKargo.getText() + "TL");

        String csvOutputFile = "output.csv";

        boolean isFileExist = new File(csvOutputFile).exists();
        try {

            CsvWriter csv = new CsvWriter(new FileWriter(csvOutputFile, true), ',');

            if(!isFileExist)
            {
                csv.write("ToplamUrunTutari");
                csv.write("ToplamKargoTutari");
                csv.endRecord();
            }

            csv.write(toplamTutar.getText()+" TL");
            csv.write(toplamKargo.getText()+" TL");
            csv.endRecord();
            //close the file
            csv.close();

            System.out.println("output.csv dosyasına yazdırıldı!");

        } catch (IOException e) {
            e.printStackTrace();
        }



    }

    public void tamamla()
    {
        WebElement tamamla = driver.findElement(By.xpath("//span[contains(text(),'i Tamamla')]"));
        tamamla.click();
    }

    public void yeniAdresEkle() throws InterruptedException
    {
        WebElement yeniAdres = driver.findElement(By.xpath("//div[@class='col delivery-addresses']//a[contains(text(),'Yeni Ekle')]"));
        yeniAdres.click();
        firstName.sendKeys("Test");
        lastName.sendKeys("Automation");

        adrs.sendKeys("Test Adresi");
        adrsName.sendKeys("Ev");
        telefon.sendKeys("5455554455");
        sehirDropDown.click();
        driver.findElement(By.xpath("//div[@class='control-group live-search error']//li[3]//a[1]")).click();
        ilceDropDown.click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//div[@class='control-group live-search error']//li[3]//span")).click();
        mahalle.click();
        driver.findElement(By.xpath("//div[@class='control-group live-search error']//li[3]")).click();
      //driver.findElement(By.xpath("//input[@id='citizen-id']")).sendKeys("20474732672");
        driver.findElement(By.xpath("//span[contains(text(),'Adresi Kaydet')]")).click();
    }

    public void devamEt() throws InterruptedException
    {

        Thread.sleep(3000);
        driver.findElement(By.xpath("//button[@class='btn btn-primary full']")).click();
    }

    public void odemeTipiSec()
    {
        krediKart.click();
    }

    public void kartBilgileriGir()
    {
        cardno.sendKeys("5400 6170 5888 6515");
        holderName.sendKeys("TEST TEST");
        monthButton.click();
        month.click();
        yearButton.click();
        year.click();
        cvc.sendKeys("011");
        anasayfa();
    }

    public void sepeteEkle() throws InterruptedException
    {
        System.out.println("Eklenen adet: " + miktar.getAttribute("value"));
        sepeteEkle.click();
        Thread.sleep(3000);
        System.out.println("Sepetteki toplam ürün adedi " + sepetMiktar.getText());
    }

    public void sepeteGit()
    {
        sepetClick.click();

    }

    public void sepetTemizle() throws InterruptedException
    {
        int i;
        WebElement menu = driver.findElement(By.xpath("//form[@id='form-item-list']"));
        List<WebElement> sepetListesi = menu.findElements(By.xpath("//ul[@class='cart-item-list']/li//div[@class='utils']//a[contains(text(),'Sil')]"));



        for(i=sepetListesi.size(); i>0; i--)
        {
             sepetListesi = menu.findElements(By.xpath("//ul[@class='cart-item-list']/li//div[@class='utils']//a[contains(text(),'Sil')]"));

            sepetListesi.get(sepetListesi.size()-1).click();
            Thread.sleep(3000);
        }


    }

    public void loginCheck() throws InterruptedException
    {

        boolean present;
        try {
            driver.findElement(By.xpath("//a[contains(text(),'Cansu Cobanoglu')]"));
            present = true;
            System.out.println("Login başarılı!");

            String sepet = driver.findElement(By.xpath("//label[@data-bind='visible: cartCount() > -1, text: cartCount']")).getText();
            if (sepet.equals("0"))
            {
                System.out.println("sepetteki ürün sayısı 0");
            }
            else
            {
                                sepetClick.click();
                String tutar = totalPrice.getText();
                System.out.println("Sepet tutarı:" + tutar);
                Thread.sleep(2000);
                driver.navigate().back();
            }

        } catch (NoSuchElementException e) {
            present = false;
            System.out.println("Login başarısız!");
        }


    }

    public void fiyatAralikSec()
    {
        js.executeScript("window.scrollBy(0,650)", "");
        enAz.sendKeys("10");
        enCok.sendKeys("4000");
        button_fiyatSec.click();
    }

    public void anasayfa()
    {
        driver.get(prop.getProperty("url"));
    }


}