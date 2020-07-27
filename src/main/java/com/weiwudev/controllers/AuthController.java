package com.weiwudev.controllers;


import com.weiwudev.models.ResponseObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.WebSession;
import reactor.core.publisher.Mono;


@RestController
@RefreshScope
public class AuthController {

    @Value("${check.response.value}")
    private String checkResponse;

    @PostMapping("/login")
    public Mono<ResponseEntity<ResponseObject>> login() {
        return Mono.just(ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("Login Successful")));

    }

    @PutMapping("/logout")
    public Mono<ResponseEntity<ResponseObject>> logout(WebSession webSession) {
        webSession.invalidate();
        return Mono.just(ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("Logout Successful")));
    }

    @GetMapping("/check")
    public Mono<ResponseEntity<ResponseObject>> checkAuth() {
        return Mono.just(ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(checkResponse)));
    }

}
