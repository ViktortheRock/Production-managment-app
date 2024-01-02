package com.example.factory.service.implement;

import com.example.factory.model.Product;
import com.example.factory.model.ProductivityInHour;
import com.example.factory.model.ProductivityInMinute;
import com.example.factory.repositoty.ProductivityInHourRepository;
import com.example.factory.repositoty.ProductivityInMinuteRepository;
import com.example.factory.service.ProductivityInHourService;
import com.example.factory.service.ProductivityInMinuteService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ProductivityInHourServiceImpl implements ProductivityInHourService {

    private final ProductivityInHourRepository productivityInHourRepository;

    public ProductivityInHourServiceImpl(ProductivityInHourRepository productivityInHourRepository) {
        this.productivityInHourRepository = productivityInHourRepository;
    }

    @Override
    public ProductivityInHour create(ProductivityInHour productivityInHour) {
        var productivity = productivityInHourRepository
                .findByDateAndProduct_Id(
                        productivityInHour.getDate(),
                        productivityInHour.getProduct().getId());
        if (productivity.isPresent()) {
            throw new RuntimeException(String.format("Productivity for %s %s %s already exist",
                    productivityInHour.getProduct().getName(),
                    productivityInHour.getProduct().getMachine().getName(),
                    productivityInHour.getDate()));
        } else {
            return productivityInHourRepository.save(productivityInHour);
        }
    }

    @Override
    public int getProductivity(Product product, LocalDateTime currentTime) {
        try {
            return productivityInHourRepository.findByDateAndProduct_Id(currentTime, product.getId())
                    .get().getProdInHour();
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public ProductivityInHour update(ProductivityInHour productivityInHour) {
        ProductivityInHour productivity = null;
        try {
            productivity = productivityInHourRepository
                    .findByDateAndProduct_Id(
                            productivityInHour.getDate(),
                            productivityInHour.getProduct().getId())
                    .get();
            productivity.setProdInHour(productivityInHour.getProdInHour());
        } catch (Exception e) {
            throw new RuntimeException(String.format("Productivity for %s %s %s doesn't exist",
                    productivityInHour.getProduct().getName(),
                    productivityInHour.getProduct().getMachine().getName(),
                    productivityInHour.getDate()));
        }
        return productivityInHourRepository.save(productivity);
    }

    @Override
    public Optional<ProductivityInHour> findProductivity(ProductivityInHour productivityInHour) {
        return productivityInHourRepository.findByDateAndProduct_Id(productivityInHour.getDate(), productivityInHour.getProduct().getId());
    }
}
