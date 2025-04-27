package com.github.jukkarol.controller;

import com.github.jukkarol.dto.notificationDto.DisplayNotificationDto;
import com.github.jukkarol.dto.notificationDto.request.CreateNotificationRequest;
import com.github.jukkarol.service.NotificationService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RequestMapping("api/v1/notifications")
@RestController
@Validated
public class NotificationsController {
    public final NotificationService notificationService;

    @PostMapping
    public ResponseEntity<DisplayNotificationDto> addNotification(@RequestBody @Valid CreateNotificationRequest dto) {
        DisplayNotificationDto createdNotification = notificationService.CreateNotification(dto);

        return ResponseEntity.ok(createdNotification);
    }
}
