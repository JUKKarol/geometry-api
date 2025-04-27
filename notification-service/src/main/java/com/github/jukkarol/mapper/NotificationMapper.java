package com.github.jukkarol.mapper;

import com.github.jukkarol.dto.notificationDto.DisplayNotificationDto;
import com.github.jukkarol.model.Notification;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface NotificationMapper {
    NotificationMapper INSTANCE = Mappers.getMapper(NotificationMapper.class);

    DisplayNotificationDto notificationToDisplayNotificationDto(Notification notification);

    List<DisplayNotificationDto> notificationToDisplayNotificationDtos(List<Notification> notifications);
}
