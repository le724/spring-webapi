package com.example.scheduleapi.service;

import com.example.scheduleapi.dto.Schedule;
import com.example.scheduleapi.exception.ScheduleArgumentNotValidException;
import com.example.scheduleapi.exception.ScheduleNotFoundException;
import com.example.scheduleapi.repository.ScheduleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;

    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    public List<Schedule> findAll() {
        return scheduleRepository.findAll();
    }

    public Schedule saveSchedule(Schedule schedule){
        if (schedule.getTitle() == null || schedule.getTitle().isEmpty()) {
            throw new ScheduleArgumentNotValidException("title is empty");
        }

        if (schedule.getContent() == null || schedule.getContent().isEmpty()) {
            throw new ScheduleArgumentNotValidException("content is empty");
        }

        scheduleRepository.insertSchedule(schedule);
        return schedule;
    }

    public Schedule findById(long id){
        Schedule schedule = scheduleRepository.findById(id);

        if(schedule == null){
            throw new ScheduleNotFoundException(id);
        }

        return schedule;
    }

    public boolean delete(long id){
        if(!scheduleRepository.delete(id)){
            throw new ScheduleNotFoundException(id);
        }

        return true;
    }

    public Schedule update(Schedule schedule,long id){
        if (schedule.getTitle() == null || schedule.getTitle().isEmpty()) {
            throw new ScheduleArgumentNotValidException("title is empty");
        }

        if (schedule.getContent() == null || schedule.getContent().isEmpty()) {
            throw new ScheduleArgumentNotValidException("content is empty");
        }

        Schedule targetSchedule = scheduleRepository.findById(id);
        if(targetSchedule == null){
            throw new ScheduleNotFoundException(id);
        }

        schedule.setId(id);
        schedule.setCreatedAt(targetSchedule.getCreatedAt());

        return scheduleRepository.update(schedule);
    }
}
