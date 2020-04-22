package com.techstack.mongo.repo;

import com.techstack.mongo.model.LegoSet;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LegoSetRepository extends MongoRepository<LegoSet, String> {
}
