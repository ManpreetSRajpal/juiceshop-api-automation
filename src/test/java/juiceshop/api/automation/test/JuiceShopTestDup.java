
package juiceshop.api.automation.test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import juiceshop.api.automation.domain.User;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

class JuiceShopTestDup extends BaseTest {


    @Test
    void testListProducts() {
        when().
                get("/products/search").
                then().
                statusCode(HttpStatus.SC_OK).
                body("status", is("success")).
                body("data", notNullValue());
    }

    @Test
    void testLoginUser() {
        User user = new User("test18@test.com", "password");
        given().
                body(user).
        when().
                post("/user/login").
        then().
                statusCode(HttpStatus.SC_OK)
                .body("authentication", notNullValue());
    }
}
