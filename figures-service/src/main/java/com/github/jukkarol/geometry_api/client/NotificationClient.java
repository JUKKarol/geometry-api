package com.github.jukkarol.geometry_api.client;

import com.github.jukkarol.geometry_api.dto.notificationDto.request.CreateNotificationRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Component
@Slf4j
public class NotificationClient {
    private final RestTemplate restTemplate;

    @Value("${notification-service.url}")
    private String notificationServiceUrl;

    public NotificationClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void sendNotification(Long figureId) {
        try {
            CreateNotificationRequest notificationRequest = new CreateNotificationRequest(figureId);
            restTemplate.postForEntity(
                    notificationServiceUrl + "/api/v1/notifications",
                    notificationRequest,
                    Void.class
            );
            log.info("Notification sent successfully for figure ID: {}", figureId);
        } catch (RestClientException e) {
            log.error("Failed to send notification for figure ID: {}. Error: {}", figureId, e.getMessage());
        }
    }
}