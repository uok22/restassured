package com.example.logservice;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@SpringBootApplication
public class LogServiceApplication {

    @GetMapping("/getit")
    public ResponseEntity<LoginResponsePayload> login(@RequestHeader Map<String, String> headers) {

        System.out.println("----- ****** :::::  getMapping: /login");

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("x-session-id", "session-22");
        responseHeaders.set("x-app-key", "app-key-22");

        LoginResponsePayload loginResponsePayload = new LoginResponsePayload("uuid-1", "jwt-1");
        ResponseEntity responseEntity = ResponseEntity.ok().headers(responseHeaders).body(loginResponsePayload);

        return responseEntity;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponsePayload> doLogin(@RequestBody LoginRequestPayload request) {

            System.out.println("----- ****** :::::  POST IT: /login");

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("x-session-id", "post-session-abc123");
        responseHeaders.set("x-app-key", "post-app-key-abc123");
        responseHeaders.set("Content-Type", "application/json");

        ResponseEntity<LoginResponsePayload> responseEntity = ResponseEntity.ok()
                .headers(responseHeaders)
                .body(new LoginResponsePayload("uuid-postit", "jwtxxx123qwerty"));

        return responseEntity;
    }

    public static void main(String[] args) {
        SpringApplication.run(LogServiceApplication.class, args);
    }

}


