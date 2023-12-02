package com.example.factory;

import com.example.factory.model.Product;
import com.example.factory.model.ProductName;
import com.example.factory.service.ProductService;
import org.springframework.stereotype.Component;

@Component
public class ProductGenerationThread implements Runnable {
    private ProductName incomingName;
    private final ProductService productService;

    public ProductGenerationThread(final ProductService productService, ProductName incomingName) {
        this.productService = productService;
        this.incomingName = incomingName;
    }

    @Override
    public void run() {
        while(!Thread.interrupted()) {
            Product product = new Product(incomingName);
            productService.saveProduct(product);
            System.out.println(product.toString());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public ProductName getIncomingName() {
        return incomingName;
    }

    public void setIncomingName(ProductName incomingName) {
        this.incomingName = incomingName;
    }
}
