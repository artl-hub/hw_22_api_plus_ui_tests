package artl.demoqa.tests.tests;

import artl.demoqa.tests.extension.WithLogin;
import artl.demoqa.tests.pages.ProfilePage;
import io.qameta.allure.Feature;
import io.qameta.allure.Link;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;


public class ProfileTests extends TestBase {
    ProfilePage profilePage = new ProfilePage();


    @Test
    @Feature("Api+UI tests")
    @Story("Cheking profilePage")
    @Owner("A. Larichev")
    @DisplayName("Checking empty book rows on the profile page")
    @Link(value = "Base url", url = "https://github.com/")
    @Tag("smoke")

    void noRowsFoundTest() {
            //Make Api request to get token,
            String body = "{\"userName\": \"testUser123\", \"password\": \"4eDsM76F*9WY3Sn\"}";


            Response response = given()
                    .log().uri()
                    .log().method()
                    .log().body()
                    .contentType(JSON)
                    .body(body)
                    .when()
                    .post("/Account/v1/Login")
                    .then()
                    .log().status()
                    .log().body().statusCode(200)
                    .extract().response();

            String userId = response.path("userId"),
                    token = response.path("token"),
                    expires = response.path("expires");

//        open("https://demoqa.com/profile");

            //Put data to browser cookies
            open("/favicon.ico");
            getWebDriver().manage().addCookie(new Cookie("userID", userId));
            getWebDriver().manage().addCookie(new Cookie("token", token));
            getWebDriver().manage().addCookie(new Cookie("expires", expires));

            open("/profile");
            $("#userName-value").shouldHave(text("testUser123"));
        open("/profile");
        profilePage
                .openProfilePage()
                .checkUserName()
                .checkNoBookRows();
    }
}
