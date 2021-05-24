package ru.netology;

import com.codeborne.selenide.SelenideElement;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardFormTest {
    private WebDriver driver;

    @BeforeClass
    public static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @Before
    public void setupTest() {
        driver = new ChromeDriver();
    }

    @After
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void ShouldTestForm() {
        open("http://localhost:9999");
        SelenideElement form = $("[class=\"form form_size_m form_theme_alfa-on-white\"]");
        form.$("[data-test-id=name] input").setValue("Петр Иванов");
        form.$("[data-test-id=phone] input").setValue("+79270000000");
        form.$("[data-test-id=agreement]").click();
        form.$("[role=\"button\"]").click();
        $("[class=\"Success_successBlock__2L3Cw\"]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));

    }

    @Test
    public void ShouldTestFormDoubleName() {
        open("http://localhost:9999");
        SelenideElement form = $("[class=\"form form_size_m form_theme_alfa-on-white\"]");
        form.$("[data-test-id=name] input").setValue("Андрей Ивановский-Мироев");
        form.$("[data-test-id=phone] input").setValue("+79275647892");
        form.$("[data-test-id=agreement]").click();
        form.$("[role=\"button\"]").click();
        $("[class=\"Success_successBlock__2L3Cw\"]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));

    }

    @Test
    public void ShouldTestFormNameWithSpace() {
        open("http://localhost:9999");
        SelenideElement form = $("[class=\"form form_size_m form_theme_alfa-on-white\"]");
        form.$("[data-test-id=name] input").setValue("Петр Максимов Антонов");
        form.$("[data-test-id=phone] input").setValue("+79278436892");
        form.$("[data-test-id=agreement]").click();
        form.$("[role=\"button\"]").click();
        $("[class=\"Success_successBlock__2L3Cw\"]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));

    }

    @Test
     public void ShouldTestFormWithoutCheck() {
        open("http://localhost:9999");
        SelenideElement form = $("[class=\"form form_size_m form_theme_alfa-on-white\"]");
        form.$("[data-test-id=name] input").setValue("Петр Максимов Антонов");
        form.$("[data-test-id=phone] input").setValue("+79278436892");
        form.$("[role=\"button\"]").click();
        $(By.className("checkbox_checked")).shouldNot(exist);

    }

    @Test
    public void ShouldCheckInvalidName() {
        open("http://localhost:9999");
        SelenideElement form = $("[class=\"form form_size_m form_theme_alfa-on-white\"]");
        form.$("[data-test-id=name] input").setValue("Sergey Kazakov");
        form.$("[data-test-id=phone] input").setValue("+79278436892");
        form.$("[data-test-id=agreement]").click();
        form.$("[role=\"button\"]").click();
        $("[class=\"input__sub\"]").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }
}