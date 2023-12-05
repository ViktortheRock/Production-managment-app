package com.example.factory;

import com.example.factory.model.Product;
import com.example.factory.model.ProductivityInMinute;
import com.example.factory.service.ProductivityInMinutesService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;

@Data
@Builder
@NoArgsConstructor @AllArgsConstructor
public class ProductGenerationThread implements Runnable {

    private boolean threadRun = true;
    private String name;
    private Product product;
    private ProductivityInMinutesService productivityInMinutesService;
    private long expectedProductivity;

//    public ProductGenerationThread(String name, final ProductivityInMinutesService productivityInMinutesService, Product product, long expectedProductivity) {
//        this.name = name;
//        this.productivityInMinutesService = productivityInMinutesService;
//        this.product = product;
//        this.expectedProductivity = expectedProductivity;
//    }

    @Override
    public void run() {
        Thread.currentThread().setName(name);
        System.out.println("Thread " + Thread.currentThread().getName() + " started");
        LocalDateTime currentTime = LocalDateTime.now();
        LocalDateTime nextMinute = currentTime.plusMinutes(1).withSecond(0).withNano(0);
        int count = 0;
        threadRun = true;
        while(threadRun) {
            currentTime = LocalDateTime.now();
            if (currentTime.isBefore(nextMinute)) {
                count++;
                try {
                    Thread.sleep(sleepDuration());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            } else {
                var productivityInMinute = ProductivityInMinute.builder()
                        .product(product)
                        .data(nextMinute)
                        .prodInMinute(count)
                        .build();
                productivityInMinutesService.create(productivityInMinute);
                System.out.println(name+ " " + productivityInMinute.getData() + " - " + productivityInMinute.getProdInMinute());
                nextMinute = nextMinute.plusMinutes(1);
                count = 0;
            }
        }
    }

    private long sleepDuration() {
        long timeForProduction = 60000/expectedProductivity;
        return timeForProduction + ThreadLocalRandom.current().nextLong(-timeForProduction/5, timeForProduction/10);
    }
}
