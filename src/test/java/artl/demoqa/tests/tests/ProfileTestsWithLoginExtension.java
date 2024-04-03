package artl.demoqa.tests.tests;

import artl.demoqa.tests.extension.WithLogin;
import artl.demoqa.tests.pages.ProfilePage;
import io.qameta.allure.Feature;
import io.qameta.allure.Link;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.assertj.core.api.Assertions.assertThat;


public class ProfileTestsWithLoginExtension extends TestBase {
    ProfilePage profilePage = new ProfilePage();


    @Test
    @Feature("Api+UI tests")
    @Story("Cheking profilePage")
    @Owner("A. Larichev")
    @DisplayName("Checking empty book rows on the profile page")
    @Link(value = "Base url", url = "https://github.com/")
    @Tag("smoke")
    @WithLogin
    void noRowsFoundTest() {
        open("/profile");
        profilePage
                .openProfilePage()
                .checkUserName()
                .checkNoBookRows();
    }
}
