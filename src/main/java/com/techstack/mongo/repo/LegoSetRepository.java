package com.techstack.mongo.repo;

import com.techstack.mongo.model.LegoSet;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface LegoSetRepository extends MongoRepository<LegoSet, String> {

    List<LegoSet> findAllByThemeContains(String theme);
}
