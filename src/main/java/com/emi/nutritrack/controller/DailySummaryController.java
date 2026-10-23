
package com.emi.nutritrack.controller;

import com.emi.nutritrack.dto.DailySummaryResponse;
import com.emi.nutritrack.service.DailySummaryService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/dailySummary")
public class DailySummaryController {

    private final DailySummaryService dailySummaryService;

    public DailySummaryController(
            DailySummaryService dailySummaryService) {

        this.dailySummaryService = dailySummaryService;
    }

    @GetMapping("/user/{userId}/date/{date}")
    public DailySummaryResponse getDailySummary(
            @PathVariable Long userId,
            @PathVariable LocalDate date) {

        return dailySummaryService.getDailySummary(userId, date);
    }
}
