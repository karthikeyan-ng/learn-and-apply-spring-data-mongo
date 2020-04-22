package com.techstack.mongo.controller;

import com.techstack.mongo.model.AvgRatingModel;
import com.techstack.mongo.repo.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("legostore/api/reports")
public class ReportingController {
    private final ReportService reportService;

    @GetMapping("avgRatingsReport")
    public List<AvgRatingModel> avgRatingReport(){
        return this.reportService.getAvgRatingReport();
    }
}
