package com.example.factory.controller;

import com.example.factory.dto.productivityInHour.ProductivityInHourDiagramResponseDto;
import com.example.factory.dto.productivityInHour.ProductivityInHourFilterDto;
import com.example.factory.dto.productivityInHour.ProductivityInHourResponseDto;
import com.example.factory.dto.productivityInMinute.ProductivityInMinuteFilterDto;
import com.example.factory.dto.productivityInMinute.ProductivityInMinuteResponseDto;
import com.example.factory.service.MachineService;
import com.example.factory.service.ProductService;
import com.example.factory.service.ProductivityInHourService;
import com.example.factory.service.ProductivityInMinuteService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/productivity_in_hour")
public class ProductivityInHourController {

    private ProductivityInHourService productivityInHourService;
    private ProductService productService;
    private MachineService machineService;

    public ProductivityInHourController(ProductivityInHourService productivityInHourService, ProductService productService, MachineService machineService) {
        this.productivityInHourService = productivityInHourService;
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
        productivityInHourService.delete(productivityId);
    }

    @GetMapping("/all")
    public List<ProductivityInHourResponseDto> getAll() {
        return productivityInHourService.getAll().stream()
                .map(p -> ProductivityInHourResponseDto.of(p))
                .collect(Collectors.toList());
    }

    @PostMapping("/all_filtered")
    public List<ProductivityInHourResponseDto> getAllFiltered(@RequestBody ProductivityInHourFilterDto productivityInHourFilterDto) {
        return productivityInHourService.getAllFiltered(productivityInHourFilterDto).stream()
                .map(p -> ProductivityInHourResponseDto.of(p))
                .collect(Collectors.toList());
    }

    @PostMapping("/diagram_all_filtered")
    public List<ProductivityInHourDiagramResponseDto> getAllFilteredDiagram(@RequestBody ProductivityInHourFilterDto productivity) {
        return productivityInHourService.getAllFilteredDiagram(productivity);
    }
    @PostMapping("/all_by_criteria_paged")
    public Page<ProductivityInHourResponseDto> getAllFilteredPaged(@RequestBody ProductivityInHourFilterDto productivityInHourFilterDto,
                                                         Pageable pageable) {
        return productivityInHourService.getAllFilteredPaged(productivityInHourFilterDto, pageable)
                .map(p -> ProductivityInHourResponseDto.of(p));
    }
}
