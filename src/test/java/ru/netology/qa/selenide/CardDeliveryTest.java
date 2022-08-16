package ru.netology.qa.selenide;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

class CardDeliveryTest {
    String planningDate = LocalDate.now().plusDays(5).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        @Test
        void shouldTest() {
        open("http://localhost:9999/");
        $("[placeholder='Город']").setValue("Казань");
        $("[placeholder='Дата встречи']").doubleClick().sendKeys(Keys.DELETE);
        $("[placeholder='Дата встречи']").sendKeys(planningDate);
        $("[name='name']").setValue("Антонов Антон");
        $("[name='phone']").setValue("+79991112223");
        $("[data-test-id='agreement']").click();
        $(By.className("button")).click();
        $(By.className("notification_visible")).shouldBe(visible, Duration.ofSeconds(15));
        $(By.className("notification__title")).shouldHave(exactText("Успешно!"));
        $(By.className("notification__content")).shouldHave(exactText("Встреча успешно забронирована на " + planningDate));
    }
}

