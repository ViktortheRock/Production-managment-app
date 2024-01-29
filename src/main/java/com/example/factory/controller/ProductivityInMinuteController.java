package com.example.factory.controller;

import com.example.factory.dto.productivityInMinute.ProductivityInMinuteFilterDto;
import com.example.factory.dto.productivityInMinute.ProductivityInMinuteResponseDto;
import com.example.factory.dto.stoppage.*;
import com.example.factory.service.MachineService;
import com.example.factory.service.ProductService;
import com.example.factory.service.ProductivityInMinuteService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/productivity_in_minute")
public class ProductivityInMinuteController {

    private ProductivityInMinuteService productivityInMinuteService;
    private ProductService productService;
    private MachineService machineService;

    public ProductivityInMinuteController(ProductivityInMinuteService productivityInMinuteService, ProductService productService, MachineService machineService) {
        this.productivityInMinuteService = productivityInMinuteService;
        this.productService = productService;
        this.machineService = machineService;
    }

//    @GetMapping("/{id}")
//    public StoppageResponseDto get(@PathVariable("id") long stoppageId) {
//        return StoppageResponseDto.of(productivityInMinuteService.read(stoppageId));
//    }
//
//    @PutMapping("/{id}")
//    public StoppageResponseDto update(@PathVariable("id") long stoppageId,
//                                      @RequestBody StoppageRequestDto stoppageDto) {
//        Stoppage stoppage = productivityInMinuteService.read(stoppageId);
//        stoppage.setStartDate(stoppageDto.getStartDate());
//        stoppage.setEndDate(stoppageDto.getEndDate());
//        stoppage.setDuration(Duration.between(stoppage.getStartDate(), stoppage.getEndDate()));
//        stoppage.setProduct(productService.read(stoppageDto.getProductId()));
//        stoppage.setMachine(machineService.read(stoppageDto.getMachineId()));
//        stoppage.setBaseTypeStoppage(BaseTypeStoppage.of(baseTypeStoppageService.read(stoppageDto.getBaseTypeStoppageId())));
//        stoppage.setSubTypeStoppage(SubTypeStoppage.of(subTypeStoppageService.read(stoppageDto.getSubTypeStoppageId())));
//        return StoppageResponseDto.of(productivityInMinuteService.create(stoppage));
//    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") long productivityId) {
        productivityInMinuteService.delete(productivityId);
    }

    @GetMapping("/all")
    public List<ProductivityInMinuteResponseDto> getAll() {
        return productivityInMinuteService.getAll().stream()
                .map(p -> ProductivityInMinuteResponseDto.of(p))
                .collect(Collectors.toList());
    }

    @PostMapping("/all_filtered")
    public List<ProductivityInMinuteResponseDto> getAllFiltered(@RequestBody ProductivityInMinuteFilterDto productivityInMinuteFilterDto) {
        return productivityInMinuteService.getAllFiltered(productivityInMinuteFilterDto).stream()
                .map(p -> ProductivityInMinuteResponseDto.of(p))
                .collect(Collectors.toList());
    }

    @PostMapping("/all_by_criteria_paged")
    public Page<ProductivityInMinuteResponseDto> getAllFilteredPaged(@RequestBody ProductivityInMinuteFilterDto productivityInMinuteFilterDto,
                                                         Pageable pageable) {
        return productivityInMinuteService.getAllFilteredPaged(productivityInMinuteFilterDto, pageable)
                .map(p -> ProductivityInMinuteResponseDto.of(p));
    }
}
