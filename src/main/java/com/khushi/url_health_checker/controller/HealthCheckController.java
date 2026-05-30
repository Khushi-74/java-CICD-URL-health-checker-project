package com.khushi.url_health_checker.controller;

import com.khushi.url_health_checker.model.HealthCheckResponse;
import com.khushi.url_health_checker.service.HealthCheckService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {

    private final HealthCheckService healthCheckService;

    public HealthCheckController(HealthCheckService healthCheckService) {
        this.healthCheckService = healthCheckService;
    }

    @GetMapping("/check")
    public HealthCheckResponse checkUrl(@RequestParam String url) {
        return healthCheckService.checkUrl(url);
    }
}