package com.example.scheduleapi.exception;


public class ScheduleNotFoundException extends RuntimeException {
    public ScheduleNotFoundException(long id) {
        super("指定されたIDの予定が見つかりません： " + id);
    }
}
