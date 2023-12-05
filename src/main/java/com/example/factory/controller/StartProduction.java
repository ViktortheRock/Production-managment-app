package com.example.factory.controller;

import com.example.factory.ProductGenerationThread;
import com.example.factory.dto.ThreadRequest;
import com.example.factory.model.Product;
import com.example.factory.model.ProductivityInMinute;
import com.example.factory.service.ProductivityInMinutesService;
import org.springframework.web.bind.annotation.*;

@RestController
public class StartProduction {

    private ProductivityInMinutesService productivityInMinutesService;

    public StartProduction(ProductivityInMinutesService productivityInMinutesService) {
        this.productivityInMinutesService = productivityInMinutesService;
    }

    @PostMapping("/start_production")
    public String create(/*@RequestBody Product product,
                         */@RequestBody ThreadRequest threadRequest) {
        ProductGenerationThread prodThread = ProductGenerationThread.builder()
                .name(threadRequest.getName())
//                .product(product)
                .productivityInMinutesService(productivityInMinutesService)
                .expectedProductivity(threadRequest.getExpectedProductivity())
                .build();
        Thread thread = new Thread(prodThread);
        thread.setName(threadRequest.getName());
        thread.setDaemon(true);
        thread.start();
        return thread.getName();
//        return "hello";
    }
}
