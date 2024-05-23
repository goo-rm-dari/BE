package com.goorm.server.controller;

import com.goorm.server.dto.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api")
@RestController
@RequiredArgsConstructor
public class ApiController {

    @GetMapping("/test")
    public Response<?> login() {
        return null;
    }
}
