package com.example.scheduleapi.controller;

import com.example.scheduleapi.dto.Schedule;
import com.example.scheduleapi.service.ScheduleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/schedules")
public class ScheduleController {
    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @GetMapping
    public ResponseEntity<List<Schedule>> findAll() {
        List<Schedule> schedules = scheduleService.findAll();

        // return new ResponseEntity<>(notes, HttpStatus.OK);
        return ResponseEntity.ok(schedules);
    }

    @PostMapping
    public ResponseEntity<Schedule> saveSchedule(@RequestBody Schedule schedule,
                                         UriComponentsBuilder uriBuilder) {
        Schedule createdSchedule = scheduleService.saveSchedule(schedule);

        URI location = uriBuilder.path("/api/schedules/{id}")
                .buildAndExpand(createdSchedule.getId()).toUri();

//        HttpHeaders headers = new HttpHeaders();
//        headers.setLocation(location);
//
//        return new ResponseEntity<>(createdNote,headers, HttpStatus.CREATED);

        return ResponseEntity.created(location).body(createdSchedule);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Schedule> findById(@PathVariable long id) {
        Schedule schedule = scheduleService.findById(id);

        return ResponseEntity.ok(schedule);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id){
        scheduleService.delete(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Schedule> update(@PathVariable long id,
                                           @RequestBody Schedule schedule) {
        scheduleService.update(schedule, id);

        return ResponseEntity.ok(schedule);
    }
}
