package juiceshop.api.automation.test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import juiceshop.api.automation.domain.User;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class BasketTest extends BaseTest {

    private final String ADD_TO_BASKET_ENDPOINT = "/api/BasketItems";
    private final String USER_LOGIN_ENDPOINT = "/rest/user/login";
    private final String DELETE_BASKET_ENDPOINT = "/api/BasketItems/{id}";

    @Test
    void addItemToBasket() {

        Map map = getTokenAndBId();

        int id = given()
                .header("Authorization", "Bearer " + map.get("token"))
                .body("{\n" +
                        "    \"ProductId\": 1,\n" +
                        "    \"BasketId\": " + map.get("bid") + ",\n" +
                        "    \"quantity\": 1\n" +
                        "}").

                when().
                post(ADD_TO_BASKET_ENDPOINT).
                then().
                statusCode(HttpStatus.SC_OK).
                body("data", notNullValue()).
                extract()
                .path("data.id");

        map.put("id", id);
        deleteBid(map);
    }

    private void deleteBid(Map map) {
        given()
                .header("Authorization", "Bearer " + map.get("token"))
                .pathParam("id", map.get("id")).
                when().
                delete(DELETE_BASKET_ENDPOINT).
                then()
                .statusCode(HttpStatus.SC_OK);

    }

    private Map getTokenAndBId() {
        Map map = new HashMap();
        User user = new User("test35@test.com", "password");

        Response response = given()
                .body(user)
                .when()
                .post(USER_LOGIN_ENDPOINT)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();

        JsonPath jsonPath = response.jsonPath();

        String token = jsonPath.get("authentication.token");
        int bid = jsonPath.get("authentication.bid");

        map.put("token", token);
        map.put("bid", bid);

        return map;
    }
}