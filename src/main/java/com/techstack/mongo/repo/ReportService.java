package com.techstack.mongo.repo;

import com.techstack.mongo.model.AvgRatingModel;
import com.techstack.mongo.model.LegoSet;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.project;

@RequiredArgsConstructor
@Service
public class ReportService {

    private final MongoTemplate mongoTemplate;

    public List<AvgRatingModel> getAvgRatingReport(){
        ProjectionOperation projectToMatchModel = project()
                .andExpression("name").as("productName")
                .andExpression("{$avg : '$reviews.rating'}").as("avgRating");
        Aggregation avgRatingAggregation = newAggregation(LegoSet.class, projectToMatchModel);

        return this.mongoTemplate
                .aggregate(avgRatingAggregation, LegoSet.class, AvgRatingModel.class)
                .getMappedResults();
    }
}
