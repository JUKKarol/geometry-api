package com.github.jukkarol.geometry_api.client;

import com.github.jukkarol.geometry_api.dto.notificationDto.request.CreateNotificationRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Component
@Slf4j
public class NotificationClient {
    private final RestTemplate restTemplate;
    private final Environment environment;

    public NotificationClient(RestTemplate restTemplate, Environment environment) {
        this.restTemplate = restTemplate;
        this.environment = environment;
    }

    public void sendNotification(Long figureId) {
        try {
            String host = InetAddress.getLocalHost().getHostAddress();
            String port = environment.getProperty("spring.api.gateway.port");
            String url = "http://" + host + ":" + port + "/notifications/api/v1/notifications";

            CreateNotificationRequest notificationRequest = new CreateNotificationRequest(figureId);
            restTemplate.postForEntity(
                    url,
                    notificationRequest,
                    Void.class
            );
            log.info("Notification sent successfully for figure ID: {}", figureId);
        } catch (RestClientException e) {
            log.error("Failed to send notification for figure ID: {}. Error: {}", figureId, e.getMessage());
        } catch (UnknownHostException e) {
            log.error("Failed to determine host address. Error: {}", e.getMessage());
            throw new RuntimeException("Failed to determine host address", e);
        }
    }
}