package com.khushi.url_health_checker.service;

import com.khushi.url_health_checker.model.HealthCheckResponse;
import org.springframework.stereotype.Service;

import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class HealthCheckService {

    public HealthCheckResponse checkUrl(String urlString) {

        try {

            long startTime = System.currentTimeMillis();

            URL url = new URL(urlString);

            HttpURLConnection connection =
                    (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            int responseCode = connection.getResponseCode();

            long endTime = System.currentTimeMillis();

            return new HealthCheckResponse(
                    urlString,
                    responseCode,
                    endTime - startTime,
                    "UP"
            );

        } catch (Exception e) {

            return new HealthCheckResponse(
                    urlString,
                    0,
                    0,
                    "DOWN"
            );
        }
    }
}