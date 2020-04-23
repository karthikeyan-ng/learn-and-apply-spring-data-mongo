package com.techstack.mongo;

import com.techstack.mongo.model.DeliveryInfo;
import com.techstack.mongo.model.LegoSet;
import com.techstack.mongo.model.LegoSetDifficulty;
import com.techstack.mongo.model.PaymentOptions;
import com.techstack.mongo.model.PaymentType;
import com.techstack.mongo.model.ProductReview;
import com.techstack.mongo.repo.LegoSetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@DataMongoTest
class LegoStoreDatabaseIntegrationTests {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Autowired
	private LegoSetRepository legoSetRepository;

	@BeforeEach
	public void setup() {
		this.legoSetRepository.deleteAll();

		LegoSet milleniumFalcon = new LegoSet(
				"Millennium Falcon",
				"Star Wars",
				LegoSetDifficulty.HARD,
				new DeliveryInfo(LocalDate.now().plusDays(1), 30, true),
				Arrays.asList(
						new ProductReview("Dan", 7),
						new ProductReview("Anna", 10),
						new ProductReview("John", 8)
				),
				new PaymentOptions(PaymentType.CreditCard, 0));

		LegoSet skyPolice = new LegoSet(
				"Sky Police Air Base",
				"City",
				LegoSetDifficulty.MEDIUM,
				new DeliveryInfo(LocalDate.now().plusDays(3), 50, true),
				Arrays.asList(
						new ProductReview("Dan", 5),
						new ProductReview("Andrew", 8)
				),
				new PaymentOptions(PaymentType.CreditCard, 0));

		this.legoSetRepository.insert(milleniumFalcon);
		this.legoSetRepository.insert(skyPolice);
		
		legoSetRepository.insert(milleniumFalcon);
		legoSetRepository.insert(skyPolice);
	}

	@Test
	@DisplayName("Find all by great reviews should return products that have a review with a rating of ten")
	void findAllByGreatReviews_should_return_products() {
		List<LegoSet> results = this.legoSetRepository.findAllByGreatReviews();
		assertEquals(1, results.size());
		assertEquals("Millennium Falcon", results.get(0).getName());
	}

}
