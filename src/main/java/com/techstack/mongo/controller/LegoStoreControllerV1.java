package com.techstack.mongo.controller;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.techstack.mongo.model.LegoSet;
import com.techstack.mongo.model.LegoSetDifficulty;
import com.techstack.mongo.model.QLegoSet;
import com.techstack.mongo.repo.LegoSetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("legostore/api/v1")
public class LegoStoreControllerV1 {

    private final LegoSetRepository legoSetRepository;

    @PostMapping
    public void insert(@RequestBody LegoSet legoSet) {
        this.legoSetRepository.insert(legoSet);
    }

    @GetMapping("/all")
    public List<LegoSet> getAll() {
        Sort sortByThemeAsc = Sort.by("theme").ascending();
        return this.legoSetRepository.findAll(sortByThemeAsc);
    }

    @PutMapping
    public void update(@RequestBody LegoSet legoSet) {
        this.legoSetRepository.save(legoSet);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        this.legoSetRepository.deleteById(id);
    }

    @GetMapping("/{id}")
    public LegoSet findById(@PathVariable String id) {
        return this.legoSetRepository.findById(id).orElse(null);
    }

    @GetMapping("/byTheme/{theme}")
    public List<LegoSet> findByTheme(@PathVariable String theme) {
        Sort sortByThemeAsc = Sort.by("theme").ascending();
        return this.legoSetRepository.findAllByThemeContains(theme, sortByThemeAsc);
    }

    @GetMapping("/hardThatStartsWithM")
    public List<LegoSet> hardThatStartsWithM() {
        return this.legoSetRepository.findAllByDifficultyAndNameStartsWith(LegoSetDifficulty.HARD, "M");
    }

    @GetMapping("/byDeliveryFee/{price}")
    public List<LegoSet> byDeliveryFeeLessThan(@PathVariable int price) {
        return this.legoSetRepository.findAllByDeliveryPriceLessThan(price);
    }

    @GetMapping("greatReview")
    public List<LegoSet> byGreatReviews() {
        return this.legoSetRepository.findAllByGreatReviews();
    }

    @GetMapping("/notStarWarsTheme")
    public List<LegoSet> findAllNotStarWars(){
        return this.legoSetRepository.findAllByThemeIsNot("Star Wars");
    }

    @GetMapping("/inStock")
    public Collection<LegoSet> findAllInStock(){
        return this.legoSetRepository.findAllInStock();
    }

    @GetMapping("bestBuys")
    public Collection<LegoSet> bestBuys(){
        // build query
        QLegoSet query = new QLegoSet("query");
        BooleanExpression inStockFilter =  query.deliveryInfo.inStock.isTrue();
        Predicate smallDeliveryFeeFilter =  query.deliveryInfo.deliveryFee.lt(50);
        Predicate hasGreatReviews =  query.reviews.any().rating.eq(10);

        Predicate bestBuysFilter = inStockFilter
                .and(smallDeliveryFeeFilter)
                .and(hasGreatReviews);

        // pass the query to findAll()
        return (Collection<LegoSet>) this.legoSetRepository.findAll(bestBuysFilter);
    }
}
