package ru.netology;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardFormTest {

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