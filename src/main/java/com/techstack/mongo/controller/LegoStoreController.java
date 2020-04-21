package com.techstack.mongo.controller;

import com.techstack.mongo.model.LegoSet;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("legostore/api")
public class LegoStoreController {

    private final MongoTemplate mongoTemplate;

    @PostMapping
    public void insert(@RequestBody LegoSet legoSet) {
        this.mongoTemplate.insert(legoSet);
    }

}
