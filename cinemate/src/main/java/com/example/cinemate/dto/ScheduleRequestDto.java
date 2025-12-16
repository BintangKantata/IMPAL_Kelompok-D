package com.example.cinemate.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScheduleRequestDto {
    private Long filmId;
    private Long locationId;
    private Long auditoriumId;
    private String date; // format: "YYYY-MM-DD"
    private String time; // format: "HH:mm"
}
