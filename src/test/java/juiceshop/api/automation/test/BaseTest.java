package juiceshop.api.automation.test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;


public class BaseTest {

    @BeforeAll
    static void setup() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        requestBuilder();
        responseBuilder();
    }

    private static void requestBuilder() {
        RestAssured.requestSpecification=new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBaseUri("http://localhost:3000")
                .build();
    }

    private static void responseBuilder(){
        RestAssured.responseSpecification=new ResponseSpecBuilder()
                .expectContentType(ContentType.JSON)
                .build();
    }

}
