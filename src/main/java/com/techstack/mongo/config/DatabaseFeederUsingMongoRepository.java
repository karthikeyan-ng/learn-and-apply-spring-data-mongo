package com.techstack.mongo.config;

import com.techstack.mongo.model.DeliveryInfo;
import com.techstack.mongo.model.LegoSet;
import com.techstack.mongo.model.LegoSetDifficulty;
import com.techstack.mongo.model.PaymentOptions;
import com.techstack.mongo.model.PaymentType;
import com.techstack.mongo.model.ProductReview;
import com.techstack.mongo.repo.LegoSetRepository;
import com.techstack.mongo.repo.PaymentOptionsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
//@Service
public class DatabaseFeederUsingMongoRepository implements CommandLineRunner {

    private final LegoSetRepository legoSetRepository;
    private final PaymentOptionsRepository paymentOptionsRepository;

    @Override
    public void run(String... args) {

        this.paymentOptionsRepository.deleteAll();
        this.legoSetRepository.deleteAll();

        /*
        Payment Options
         */

        PaymentOptions creditCardPayment = new PaymentOptions(PaymentType.CreditCard, 0);
        PaymentOptions payPalPayment = new PaymentOptions(PaymentType.PayPal, 1);
        PaymentOptions cashPayment = new PaymentOptions(PaymentType.Cash, 10);
        this.paymentOptionsRepository.saveAll(List.of(creditCardPayment, payPalPayment, cashPayment));

        /*
        Lego Sets
         */

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
                creditCardPayment
        );

        LegoSet skyPolice = new LegoSet(
                "Sky Police Air Base",
                "City",
                LegoSetDifficulty.MEDIUM,
                new DeliveryInfo(LocalDate.now().plusDays(3), 50, true),
                Arrays.asList(
                        new ProductReview("Dan", 5),
                        new ProductReview("Andrew", 8)
                ),
                creditCardPayment
        );

        LegoSet mcLarenSenna = new LegoSet(
                "McLaren Senna",
                "Speed Champions",
                LegoSetDifficulty.EASY,
                new DeliveryInfo(LocalDate.now().plusDays(7), 70, false),
                Arrays.asList(
                        new ProductReview("Bogdan", 9),
                        new ProductReview("Christa", 9)
                ),
                payPalPayment
        );

        LegoSet mindstormsEve = new LegoSet(
                "MINDSTORMS EV3",
                "Mindstorms",
                LegoSetDifficulty.HARD,
                new DeliveryInfo(LocalDate.now().plusDays(10), 100, false),
                Arrays.asList(
                        new ProductReview("Cosmin", 10),
                        new ProductReview("Jane", 9),
                        new ProductReview("James", 10)
                ),
                cashPayment
        );

        this.legoSetRepository.saveAll(
                List.of(mcLarenSenna, skyPolice, milleniumFalcon, mindstormsEve));
    }

}
