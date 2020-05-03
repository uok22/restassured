package com.example.logservice;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LogServiceApplicationTests {

    @Test
    public void contextLoads() {

        RestAssured
                .given()
                    .baseUri("http://localhost")
                    .basePath("/getit")
                    .port(8080)
                    .header("h1", "header-1")
                    .header("h22", "header-22")
                .when()
                    .get()
                        .peek()
                        .getHeaders().forEach(System.out::println);
    }

    @Test
    public void loginTest() {

        LoginRequestPayload loginRequestPayload = new LoginRequestPayload("123456-789S", "Meitsi");

        RestAssured
                .given()
                    .baseUri("http://localhost")
                    .basePath("/getit")
                    .port(8080)
                    .header("h1", "header-1")
                    .header("h22", "header-22")
                    .contentType(ContentType.JSON)
                    .body(loginRequestPayload)
                .when()
                    .post()
                    .peek()
                    .getHeaders().forEach(System.out::println);


    }


    private SessionData doLogin() {

        LoginRequestPayload loginRequestPayload = new LoginRequestPayload("123456-789S", "Meitsi");

        RequestSpecification requestSpecification =
            RestAssured
                 .given()
                    .baseUri("http://localhost")
                    .basePath("/login")
                    .port(8080)
                    .header("h1", "header-1")
                    .header("h22", "header-22")
                    .contentType(ContentType.JSON)
                    .body(loginRequestPayload);

        Response response = requestSpecification.post();
        JsonPath jsonPath = response.jsonPath();

        SessionData sessionData = new SessionData();

        sessionData.x_session_id = response.getHeader("x-session-id");
        sessionData.x_app_key = response.getHeader("x-app-key");

        sessionData.jwt64 = jsonPath.getString("jwt");

        System.out.println();

        return sessionData;
    }

    @Test
    public void getitTest() {

        SessionData sessionData = doLogin();

        RestAssured
            .given()
                .baseUri("http://localhost")
                .basePath("/getit")
                .port(8080)
                .header("Authorization", "Bearer " + sessionData.jwt64)
                .header("x-session-id", sessionData.x_session_id)
                .header("x-app-key", sessionData.x_app_key)
            .when()
                .get()
                .peek()
                .getHeaders().forEach(System.out::println);

        System.out.println();
    }

}


class SessionData {
    String id;
    String name;
    String accountNo;

    String x_session_id;
    String x_app_key;
    String jwt64;

}

