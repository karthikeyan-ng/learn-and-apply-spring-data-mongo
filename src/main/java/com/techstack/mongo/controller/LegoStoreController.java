package com.techstack.mongo.controller;

import com.techstack.mongo.model.LegoSet;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("legostore/api")
public class LegoStoreController {

    private final MongoTemplate mongoTemplate;

    @PostMapping
    public void insert(@RequestBody LegoSet legoSet) {
        this.mongoTemplate.insert(legoSet);
    }

    @GetMapping("/all")
    public List<LegoSet> getAll() {
        return this.mongoTemplate.findAll(LegoSet.class);
    }

    @PutMapping
    public void update(@RequestBody LegoSet legoSet) {
        this.mongoTemplate.save(legoSet);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        this.mongoTemplate.remove(new Query(Criteria.where("id").is(id)), LegoSet.class);
    }
}
