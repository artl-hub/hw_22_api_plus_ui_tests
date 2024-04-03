package artl.demoqa.tests.tests;

//import artl.demoqa.tests.extensions.WithLogin;
import artl.demoqa.tests.extension.WithLogin;
import artl.demoqa.tests.models.LoginBodyModel;
import artl.demoqa.tests.models.LoginResponseModel;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;

import static artl.demoqa.tests.specs.DemoqaSpecs.requestSpec;
import static artl.demoqa.tests.specs.DemoqaSpecs.responseSpec;
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

        LoginResponseModel response = given(requestSpec)
                .contentType(JSON)
                .body(authData)
                .when()
                .post("/Account/v1/Login")
                .then()
                .spec(responseSpec(200))
                .extract().as(LoginResponseModel.class);

        open("/favicon.ico");
        getWebDriver().manage().addCookie(new Cookie("userID", response.getUserId()));
        getWebDriver().manage().addCookie(new Cookie("token", response.getToken()));
        getWebDriver().manage().addCookie(new Cookie("expires", response.getExpires()));
    }
}

