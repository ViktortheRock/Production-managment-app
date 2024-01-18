package com.example.factory.controller;

import com.example.factory.dto.ProductDto;
import com.example.factory.dto.ProductResponseDto;
import com.example.factory.model.Product;
import com.example.factory.service.ProductService;
import com.example.factory.service.MachineService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/product")
public class ProductController {

    private ProductService productService;
    private MachineService machineService;

    public ProductController(ProductService productService, MachineService machineService) {
        this.productService = productService;
        this.machineService = machineService;
    }

    @GetMapping("/{product_id}")
    public ProductResponseDto get(@PathVariable("product_id") long productId) {
        return ProductResponseDto.of(productService.read(productId));
    }

    @PutMapping("/{product_id}")
    public ProductResponseDto update(@PathVariable("product_id") long productId,
                                     @RequestBody ProductDto productDto) {
        Product product = Product.of(productDto);
        product.setId(productId);
        product.setMachine(machineService.read(productDto.getMachineId()));
        return ProductResponseDto.of(productService.update(product));
    }

    @PostMapping
    public ProductResponseDto create(@RequestBody ProductDto productDto) {
        Product product = Product.of(productDto);
        product.setMachine(machineService.read(productDto.getMachineId()));
        return ProductResponseDto.of(productService.create(product));
    }

    @DeleteMapping("/{product_id}")
    public void delete(@PathVariable("product_id") long productId) {
        productService.delete(productId);
    }

    @GetMapping("/all")
    public List<Product> getAll() {
        return productService.getAll();
    }

    @GetMapping("/allDto")
    public List<ProductResponseDto> getAllDto() {
        return productService.getAll().stream()
                .map(p -> ProductResponseDto.of(p))
                .collect(Collectors.toList());
    }

    @GetMapping("/all/byMachineId/{machine_id}")
    public List<ProductResponseDto> getAllByMachineId(@PathVariable("machine_id") long machineId) {
        return productService.getAllByMachineId(machineId);
    }
}
