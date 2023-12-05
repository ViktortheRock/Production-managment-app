package com.example.factory.service.implement;

import com.example.factory.model.ProductivityInMinute;
import com.example.factory.repositoty.ProductivityInMinuteRepository;
import com.example.factory.service.ProductivityInMinutesService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductivityInMinutesServiceImpl implements ProductivityInMinutesService {

    private final ProductivityInMinuteRepository productivityInMinuteRepository;

    public ProductivityInMinutesServiceImpl(ProductivityInMinuteRepository productivityInMinuteRepository) {
        this.productivityInMinuteRepository = productivityInMinuteRepository;
    }

    @Override
    public ProductivityInMinute create(ProductivityInMinute productivityInMinute) {
        return productivityInMinuteRepository.save(productivityInMinute);
    }
}
