package com.example.factory;

import com.example.factory.model.ProductName;
import com.example.factory.service.ProductService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FactoryApplication {

    public static void main(String[] args) {
        SpringApplication.run(FactoryApplication.class, args);
        Thread thread1 = Thread.currentThread();
        System.out.println("Thread name " + thread1.getName());
    }

    @Bean
    public CommandLineRunner creationProduct(final ProductService productService) {

        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                Thread thread = new Thread(new ProductGenerationThread(productService, ProductName.PANDA_CLASSIC));
                thread.setDaemon(true);
                thread.start();
                System.out.println();

            }
        };
    }
}
