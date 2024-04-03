package artl.demoqa.tests.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.assertj.core.error.ShouldHave.shouldHave;

public class ProfilePage {

    SelenideElement
            userName = $("#userName-value"),
            bookRowsInfo = $(".rt-noData");



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
