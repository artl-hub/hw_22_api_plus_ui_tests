package artl.demoqa.tests.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class ProfilePage {

    SelenideElement
            userName = $("#userName-value"),
            bookRowsInfo = $(".rt-noData");



    @Step ("Login vith API")
    public ProfilePage loginDemoQA() {

        return this;
    }

    @Step ("Open profile page")
    public ProfilePage openProfilePage() {
        open("/profile");
        return this;
    }
    @Step ("Check userName")
    public ProfilePage checkUserName() {
        $(userName).shouldHave(text("testUser123"));
        return this;
    }

    @Step ("Check no books rows")
    public ProfilePage checkNoBookRows() {
        bookRowsInfo.shouldHave(text("No rows found"));
        return this;
            }


}
