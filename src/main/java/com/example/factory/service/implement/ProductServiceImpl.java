package com.example.factory.service.implement;

import com.example.factory.dto.ProductResponseDto;
import com.example.factory.model.Product;
import com.example.factory.repositoty.ProductRepository;
import com.example.factory.service.ProductService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

//    @Transactional
//    public void saveProduct(Product product) {
//        productRepository.save(product);
//    }

    @Override
    public Product create(Product product) {
        Optional<Product> fromDB = productRepository.findByNameAndNumbersInPackAndMachine_Id(product.getName(), product.getNumbersInPack(), product.getMachine().getId());
        if (fromDB.isPresent()) {
            throw new RuntimeException(String.format("Product %s %d already exists", product.getName(), product.getNumbersInPack()));
        }
        return productRepository.save(product);
    }

    @Override
    @Transactional(readOnly = true)
    public Product read(long id) {
        return productRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Product with id " + id + " not found"));
    }

    @Override
    public Product update(Product product) {
        read(product.getId());
        return productRepository.save(product);
    }

    @Override
    public void delete(long productId) {
        productRepository.deleteById(productId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public List<ProductResponseDto> getAllByMachineId(long machineId) {
        return productRepository.findProductByMachine_IdOrderByNameAsc(machineId).stream()
                .map(p -> ProductResponseDto.of(p))
                .collect(Collectors.toList());
    }
}
