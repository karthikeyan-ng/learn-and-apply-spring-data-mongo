package com.techstack.mongo.repo;

import com.techstack.mongo.model.LegoSet;
import com.techstack.mongo.model.LegoSetDifficulty;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface LegoSetRepository extends MongoRepository<LegoSet, String>, QuerydslPredicateExecutor<LegoSet> {

    List<LegoSet> findAllByThemeContains(String theme, Sort sort);

    List<LegoSet> findAllByDifficultyAndNameStartsWith(LegoSetDifficulty difficulty, String name);

    @Query(" {'delivery.deliveryFee' : {$lt : ?0}} ")
    List<LegoSet> findAllByDeliveryPriceLessThan(int price);

    @Query("{'reviews.rating' : {$eq : 10}}")
    List<LegoSet> findAllByGreatReviews();

    List<LegoSet> findAllByThemeIsNot(String theme);

    @Query("{'delivery.inStock' : true}")
    List<LegoSet> findAllInStock();

    List<LegoSet> findAllBy(TextCriteria textCriteria);

    @Query("{'paymentOptions.id' : ?0}")
    List<LegoSet> findByPaymentOptionId(String id);
}
