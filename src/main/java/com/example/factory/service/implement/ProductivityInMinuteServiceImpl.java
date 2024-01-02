package com.example.factory.service.implement;

import com.example.factory.model.Product;
import com.example.factory.model.ProductivityInMinute;
import com.example.factory.repositoty.ProductivityInMinuteRepository;
import com.example.factory.service.ProductivityInMinuteService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ProductivityInMinuteServiceImpl implements ProductivityInMinuteService {

    private final ProductivityInMinuteRepository productivityInMinuteRepository;

    public ProductivityInMinuteServiceImpl(ProductivityInMinuteRepository productivityInMinuteRepository) {
        this.productivityInMinuteRepository = productivityInMinuteRepository;
    }

    @Override
    public ProductivityInMinute create(ProductivityInMinute productivityInMinute) {
        var productivity = productivityInMinuteRepository
                .findByDateAndProduct_Id(
                        productivityInMinute.getDate(),
                        productivityInMinute.getProduct().getId());
        if (productivity.isPresent()) {
            throw new RuntimeException(String.format("Productivity for %s %s %s already exist",
                    productivityInMinute.getProduct().getName(),
                    productivityInMinute.getProduct().getMachine().getName(),
                    productivityInMinute.getDate()));
        } else {
            return productivityInMinuteRepository.save(productivityInMinute);
        }
    }

    @Override
    public int getProductivity(Product product, LocalDateTime time) {
        var productivity = productivityInMinuteRepository.findByDateAndProduct_Id(time, product.getId());
        if (productivity.isPresent())
            return productivity.get().getProdInMinute();
        else return 0;
    }

    @Override
    public ProductivityInMinute update(ProductivityInMinute productivityInMinute) {
        ProductivityInMinute productivity = null;
        try {
//            productivity = productivityInMinuteRepository
//                    .findByProduct_IdAndProduct_ProductivityInMinutes_Date(
//                            productivityInMinute.getProduct().getId(),
//                            productivityInMinute.getDate())
//                    .get();
            productivity = productivityInMinuteRepository
                    .findByDateAndProduct_Id(
                            productivityInMinute.getDate(),
                            productivityInMinute.getProduct().getId())
                    .get();
            productivity.setProdInMinute(productivityInMinute.getProdInMinute());
        } catch (Exception e) {
            throw new RuntimeException(String.format("Productivity for %s %s %s doesn't exist",
                    productivityInMinute.getProduct().getName(),
                    productivityInMinute.getProduct().getMachine().getName(),
                    productivityInMinute.getDate()));
        }
        return productivityInMinuteRepository.save(productivity);
    }

    @Override
    public Optional<ProductivityInMinute> findProductivity(ProductivityInMinute productivityInMinute) {
//        return productivityInMinuteRepository.findByProduct_IdAndProduct_ProductivityInMinutes_Date(productivityInMinute.getProduct().getId(), productivityInMinute.getDate());
        return productivityInMinuteRepository.findByDateAndProduct_Id(productivityInMinute.getDate(), productivityInMinute.getProduct().getId());
    }
}
