package artl.demoqa.tests.extension;

import io.restassured.response.Response;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

public class LoginExtension implements BeforeEachCallback {

    @Override
    public void beforeEach(ExtensionContext context) {
        //Make Api request to get token,
        String body = "{\"userName\": \"testUser123\", \"password\": \"4eDsM76F*9WY3Sn\"}";
        // BAD PRACTICE

        Response response =
                        given()
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
        open("/favicon.ico");
        //Put data to browser cookies
        getWebDriver().manage().addCookie(new Cookie("userID", userId));
        getWebDriver().manage().addCookie(new Cookie("token", token));
        getWebDriver().manage().addCookie(new Cookie("expires", expires));

    }
}
