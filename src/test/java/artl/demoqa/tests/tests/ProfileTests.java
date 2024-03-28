package artl.demoqa.tests;

import artl.demoqa.tests.extension.WithLogin;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.url;
import static org.assertj.core.api.Assertions.assertThat;

public class ProfileTests {

    @Test
    @WithLogin
    void successfulLogoutWithExtensionTest() {
        open("/profile");
        $("#userName-value").shouldHave(text("testUser123"));
        $(byText("Log out")).click();

        $("#userForm").shouldBe(visible);
        assertThat(url()).contains("/login");
    }
}
