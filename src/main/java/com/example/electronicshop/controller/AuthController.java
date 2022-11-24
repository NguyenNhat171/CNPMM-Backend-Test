package com.example.electronicshop.controller;


import com.example.electronicshop.communication.request.GetOTPRequest;
import com.example.electronicshop.communication.request.LoginRequest;
import com.example.electronicshop.communication.request.Register;
import com.example.electronicshop.models.ResponseObject;
import com.example.electronicshop.notification.AppException;
import com.example.electronicshop.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;
    @PostMapping("/login")
    public ResponseEntity<ResponseObject> login( @RequestBody LoginRequest loginReq) {
        return authService.login(loginReq);
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseObject> register( @RequestBody Register registerReq) {
        return authService.register(registerReq);
    }
    @PostMapping("/registerwithmail")
    public ResponseEntity<?> registerwithmail( @RequestBody Register registerReq) {
        return authService.registerwithmail(registerReq);
    }

    @PostMapping("/reset")
    public ResponseEntity<?> reset(@RequestBody GetOTPRequest req) {
        if (!req.getEmail().isBlank()) return authService.reset(req.getEmail());
        throw new AppException(HttpStatus.BAD_REQUEST.value(), "Email is required");
    }

    @PostMapping("/verify")
    public ResponseEntity<?> verify(@Valid @RequestBody GetOTPRequest req) {
        return authService.verifyOTP(req);
    }

}
