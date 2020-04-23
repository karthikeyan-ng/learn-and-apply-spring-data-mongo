package com.techstack.mongo.repo;

import com.techstack.mongo.model.PaymentOptions;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PaymentOptionsRepository extends MongoRepository<PaymentOptions, String> {
}
