package com.techstack.mongo.repo;

import com.techstack.mongo.model.LegoSet;
import com.techstack.mongo.model.LegoSetDifficulty;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface LegoSetRepository extends MongoRepository<LegoSet, String> {

    List<LegoSet> findAllByThemeContains(String theme);

    List<LegoSet> findAllByDifficultyAndNameStartsWith(LegoSetDifficulty difficulty, String name);
}
