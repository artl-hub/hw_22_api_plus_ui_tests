package artl.demoqa.tests.tests;

//import artl.demoqa.tests.extensions.WithLogin;
import artl.demoqa.tests.extension.WithLogin;
import artl.demoqa.tests.models.LoginBodyModel;
import artl.demoqa.tests.models.LoginResponseModel;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.codeborne.selenide.WebDriverRunner.url;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.assertj.core.api.Assertions.assertThat;


public class LoginModelTest extends TestBase {

    @Test
    void successfulLoginWithApiAndUiTest() {
        //Make Api request to get token,
//        String body = "{\"userName\": \"testUser123\", \"password\": \"4eDsM76F*9WY3Sn\"}";
        // BAD PRACTICE

        LoginBodyModel authData = new LoginBodyModel();
        authData.setUserName("testUser123");
        authData.setPassword("4eDsM76F*9WY3Sn");

        LoginResponseModel response = given()
                .log().uri()
                .log().method()
                .log().body()
                .contentType(JSON)
                .body(authData)

                .when()
                .post("/Account/v1/Login")

                .then()
                .log().status()
                .log().body().statusCode(200)
                .extract().as(LoginResponseModel.class);

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

    }
}

