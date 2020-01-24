import com.hepsiburada.qa.pages.Homepage;
import com.hepsiburada.qa.base.TestBase;
import com.hepsiburada.qa.pages.ListofProducts;
import com.hepsiburada.qa.pages.LoginPage;
import com.hepsiburada.qa.pages.ProductPage;
import com.hepsiburada.qa.util.TestUtil;
import com.thoughtworks.gauge.AfterScenario;
import com.thoughtworks.gauge.BeforeScenario;
import com.thoughtworks.gauge.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.FileWriter;
import java.io.IOException;

public class HomepageStepImplementation extends TestBase {

    Homepage homePage;
    LoginPage loginPage;
    ListofProducts listofProducts = new ListofProducts();
    ProductPage productPage = new ProductPage();



    public HomepageStepImplementation() {
        super();
    }

    @BeforeScenario
    public void setUp(){
    initialization();

    }

    @Step("Login page sayfası açılır")
    public void goToLogin() {
        loginPage = new LoginPage();
        homePage = new Homepage();
        homePage.goLoginPage();
        loginPage.login(prop.getProperty("email"), prop.getProperty("password"));

    }
    @Step("Login işlemi kontrol edilir")
    public void loginCheck() throws InterruptedException
        {
            homePage.loginCheck();
        }


    @Step("Rastgele bir kategori ve alt kategori seçilir")
    public ListofProducts kategoriSec() throws InterruptedException
    {

            homePage.Random();
            Thread.sleep(3000);
            return new ListofProducts();
       // homePage.hoverMenu();

    }
    @Step("Marka seçilir")
    public ListofProducts markaSecim() {
        listofProducts.markaSec();
        return new ListofProducts();

    }
    @Step("Fiyat aralığı 10-4000 seçilir")
    public ListofProducts fiyatAralik()
    {
        homePage.fiyatAralikSec();
        return new ListofProducts();
    }

    @Step("Random ürün seçilir")
    public void urunSecimi() throws InterruptedException
    {
        homePage.randomUrun();

    }

    @Step("Ürün adedi 2 arttırılır")
    public void adetArttir() throws InterruptedException
    {
        homePage.adetArttir();
    }

    @Step("Toplam tutar bilgisi alınır")
    public void toplamTutar()
    {
        homePage.toplamTutar();

    }

    @Step("Alışveriş tamamlanır")
    public void tamamla()
    {
        homePage.tamamla();
    }

    @Step("Yeni adres eklenir")
    public void yeniAdres() throws InterruptedException
    {
        homePage.yeniAdresEkle();
        //homePage.yeniAdresEkle(prop.getProperty("firstName"),prop.getProperty("lastName"),prop.getProperty("adress"),prop.getProperty("adressName"));
    }

    @Step("Devam et butonuna tıklanır")
    public void devam()  throws InterruptedException
    {
        homePage.devamEt();
    }

    @Step("Ödeme tipi kredi kartı seçilir")
    public void odemeSec()
    {
        homePage.odemeTipiSec();
    }

    @Step("Kart bilgileri girilir ve anasayfaya dönülür")
    public void kartBilgisi()
    {
        homePage.kartBilgileriGir();
    }

    @Step("Sepete tıklanır ve sepet temizlenir")
    public void sepetTemizle() throws InterruptedException
    {
        homePage.sepeteGit();
        homePage.sepetTemizle();
    }
    @AfterScenario
    public void tearDown()
    {
        //driver.close();
    }

}
