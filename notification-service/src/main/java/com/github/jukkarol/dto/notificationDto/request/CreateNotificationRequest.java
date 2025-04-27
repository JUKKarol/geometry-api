package com.github.jukkarol.dto.notificationDto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateNotificationRequest {

    @NotNull(message = "Shape type cannot be null")
    private Long shape_id;
}
