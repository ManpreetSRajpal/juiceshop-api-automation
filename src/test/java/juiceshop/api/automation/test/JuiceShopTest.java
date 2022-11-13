
package juiceshop.api.automation.test;

import juiceshop.api.automation.domain.User;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

class JuiceShopTest extends BaseTest {

    private final String LIST_PRODUCTS_ENDPOINT = "/rest/products/search";
    private final String LOGIN_USERS_ENDPOINT = "/rest/user/login";

    @Test
    void testListProducts() {
        when().
                get(LIST_PRODUCTS_ENDPOINT).
                then().
                statusCode(HttpStatus.SC_OK).
                body("status", is("success")).
                body("data", notNullValue()).
                body("data.size()", is(35))
                .body("data.findAll{it.name.startsWith('OWASP')}.size", is(18))
                .body("data.findAll{it.price<1}.size", is(4));
    }

    @Test
    void testLoginUser() {
        User user = new User("test18@test.com", "password");
        given().
                body(user).
                when().
                post(LOGIN_USERS_ENDPOINT).
                then().
                statusCode(HttpStatus.SC_OK)
                .body("authentication", notNullValue());
    }
}
