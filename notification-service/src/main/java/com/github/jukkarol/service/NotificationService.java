package com.github.jukkarol.service;

import com.github.jukkarol.dto.notificationDto.DisplayNotificationDto;
import com.github.jukkarol.dto.notificationDto.request.CreateNotificationRequest;
import com.github.jukkarol.mapper.NotificationMapper;
import com.github.jukkarol.model.Notification;
import com.github.jukkarol.repository.NotificationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class NotificationService {
    public final NotificationRepository notificationRepository;
    public final NotificationMapper notificationMapper;

    public List<DisplayNotificationDto> GetAllNotifications(){
        List<Notification> notifications = notificationRepository.findAll();

        return notificationMapper.notificationToDisplayNotificationDtos(notifications);
    }

    public DisplayNotificationDto CreateNotification(CreateNotificationRequest createNotificationRequest){
        Notification notification = new Notification();
        notification.setShape_id(createNotificationRequest.getShape_id());
        notificationRepository.save(notification);

        return notificationMapper.notificationToDisplayNotificationDto(notification);
    }
}
