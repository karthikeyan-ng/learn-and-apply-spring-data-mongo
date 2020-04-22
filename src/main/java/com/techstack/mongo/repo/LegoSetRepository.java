package com.techstack.mongo.repo;

import com.techstack.mongo.model.LegoSet;
import com.techstack.mongo.model.LegoSetDifficulty;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Collection;
import java.util.List;

public interface LegoSetRepository extends MongoRepository<LegoSet, String> {

    List<LegoSet> findAllByThemeContains(String theme, Sort sort);

    List<LegoSet> findAllByDifficultyAndNameStartsWith(LegoSetDifficulty difficulty, String name);

    @Query(" {'delivery.deliveryFee' : {$lt : ?0}} ")
    List<LegoSet> findAllByDeliveryPriceLessThan(int price);

    @Query("{'reviews.rating' : {$eq : 10}}")
    List<LegoSet> findAllByGreatReviews();
}
