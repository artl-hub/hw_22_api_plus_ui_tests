package artl.demoqa.tests.extension;

import artl.demoqa.tests.models.LoginBodyModel;
import artl.demoqa.tests.models.LoginResponseModel;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.Cookie;

import static artl.demoqa.tests.specs.DemoqaSpecs.requestSpec;
import static artl.demoqa.tests.specs.DemoqaSpecs.responseSpec;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

public class LoginExtension implements BeforeEachCallback {

    @Override
    public void beforeEach(ExtensionContext context) {

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
