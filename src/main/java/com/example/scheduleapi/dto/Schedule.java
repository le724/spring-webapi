package com.example.scheduleapi.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Schedule {
    private long id;
    private String title;
    private String content;
    private LocalDateTime schedule;
    private LocalDateTime createdAt;
}
