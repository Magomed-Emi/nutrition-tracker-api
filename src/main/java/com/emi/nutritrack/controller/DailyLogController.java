package com.emi.nutritrack.controller;

import com.emi.nutritrack.entity.DailyLog;
import com.emi.nutritrack.service.DailyLogService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/dailyLogs")
public class DailyLogController {

    private final DailyLogService dailyLogService;

    public DailyLogController(DailyLogService dailyLogService) {
        this.dailyLogService = dailyLogService;
    }

    @GetMapping
    public List<DailyLog> getAllDailyLogs() {
        return dailyLogService.getAllDailyLogs();
    }

    @GetMapping("/{id}")
    public Optional<DailyLog> getDailyLogById(@PathVariable Long id) {
        return dailyLogService.getDailyLogById(id);
    }

    @PostMapping
    public void addDailyLog(@RequestBody DailyLog dailyLog) {
        dailyLogService.addDailyLog(dailyLog);
    }

    @PutMapping("/{id}")
    public void updateDailyLog(@RequestBody DailyLog dailyLog,
                               @PathVariable Long id) {
        dailyLogService.updateDailyLog(dailyLog, id);
    }

    @DeleteMapping("/{id}")
    public void deleteDailyLog(@PathVariable Long id) {
        dailyLogService.deleteDailyLog(id);
    }

    @PutMapping("/calculateDailyLog/{id}")
    public void calculateDailyLog(@PathVariable Long id)
    {
        Optional<DailyLog> dailyLog = dailyLogService.getDailyLogById(id);

        if (dailyLog.isPresent())
        {
            dailyLogService.calculateDailyLog(dailyLog.get());
        }
    }

}