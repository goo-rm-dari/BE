package com.goorm.server.controller;

import com.goorm.server.dto.response.Response;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api")
public class ApiController {

    @GetMapping("/test")
    public Response<?> login() {
        return Response.ofSuccess("OK", null);
    }
}
