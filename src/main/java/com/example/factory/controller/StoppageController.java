package com.example.factory.controller;

import com.example.factory.dto.stoppage.*;
import com.example.factory.model.stoppage.BaseTypeStoppage;
import com.example.factory.model.stoppage.Stoppage;
import com.example.factory.model.stoppage.SubTypeStoppage;
import com.example.factory.service.MachineService;
import com.example.factory.service.ProductService;
import com.example.factory.service.stoppage.BaseTypeStoppageService;
import com.example.factory.service.stoppage.StoppageService;
import com.example.factory.service.stoppage.SubTypeStoppageService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/stoppage")
public class StoppageController {

    private StoppageService stoppageService;
    private BaseTypeStoppageService baseTypeStoppageService;
    private SubTypeStoppageService subTypeStoppageService;
    private ProductService productService;
    private MachineService machineService;

    public StoppageController(StoppageService stoppageService, BaseTypeStoppageService baseTypeStoppageService, SubTypeStoppageService subTypeStoppageService, ProductService productService, MachineService machineService) {
        this.stoppageService = stoppageService;
        this.baseTypeStoppageService = baseTypeStoppageService;
        this.subTypeStoppageService = subTypeStoppageService;
        this.productService = productService;
        this.machineService = machineService;
    }

    @PostMapping
    public StoppageCreateResponseDto createBegin(@RequestBody StoppageCreateDto stoppageDto) {
        Stoppage stoppage = Stoppage.of(stoppageDto);
        stoppage.setProduct(productService.read(stoppageDto.getProductId()));
        stoppage.setMachine(machineService.read(stoppageDto.getMachineId()));
        return StoppageCreateResponseDto.of(stoppageService.create(stoppage));
    }

    @PostMapping("/not_full/{id}")
    public StoppageResponseDto createFinish(@PathVariable("id") long stoppageId,
                                            @RequestBody StoppageCreateDto stoppageDto) {
        Stoppage stoppage = stoppageService.read(stoppageId);
        stoppage.setEndDate(LocalDateTime.now());
        stoppage.setDuration(Duration.between(stoppage.getStartDate(), stoppage.getEndDate()));
        stoppage.setBaseTypeStoppage(BaseTypeStoppage.of(baseTypeStoppageService.read(stoppageDto.getBaseTypeStoppageId())));
        stoppage.setSubTypeStoppage(SubTypeStoppage.of(subTypeStoppageService.read(stoppageDto.getSubTypeStoppageId())));
        return StoppageResponseDto.of(stoppageService.create(stoppage));
    }

    @GetMapping("/not_full/{id}")
    public StoppageCreateResponseDto getNotFull(@PathVariable("id") long stoppageId) {
        return StoppageCreateResponseDto.of(stoppageService.read(stoppageId));
    }

    @GetMapping("/{id}")
    public StoppageResponseDto get(@PathVariable("id") long stoppageId) {
        return StoppageResponseDto.of(stoppageService.read(stoppageId));
    }

    @PutMapping("/{id}")
    public StoppageResponseDto update(@PathVariable("id") long stoppageId,
                                      @RequestBody StoppageRequestDto stoppageDto) {
        Stoppage stoppage = stoppageService.read(stoppageId);
        stoppage.setStartDate(stoppageDto.getStartDate());
        stoppage.setEndDate(stoppageDto.getEndDate());
        stoppage.setDuration(Duration.between(stoppage.getStartDate(), stoppage.getEndDate()));
        stoppage.setProduct(productService.read(stoppageDto.getProductId()));
        stoppage.setMachine(machineService.read(stoppageDto.getMachineId()));
        stoppage.setBaseTypeStoppage(BaseTypeStoppage.of(baseTypeStoppageService.read(stoppageDto.getBaseTypeStoppageId())));
        stoppage.setSubTypeStoppage(SubTypeStoppage.of(subTypeStoppageService.read(stoppageDto.getSubTypeStoppageId())));
        return StoppageResponseDto.of(stoppageService.create(stoppage));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") long stoppageId) {
        stoppageService.delete(stoppageId);
    }

    @GetMapping("/not_finished/all")
    public List<StoppageCreateResponseDto> getNotFullAll() {
        return stoppageService.getAll().stream()
                .filter(s -> Objects.isNull(s.getEndDate()))
                .map(s -> StoppageCreateResponseDto.of(s))
                .collect(Collectors.toList());
    }


    @GetMapping("/all")
    public List<StoppageResponseDto> getAll() {
        return stoppageService.getAll().stream()
                .map(s -> StoppageResponseDto.of(s))
                .collect(Collectors.toList());
    }

    @PostMapping("/all_filtered")
    public List<StoppageResponseDto> getAll(@RequestBody StoppageFilterDto stoppageFilterDto) {
        return stoppageService.getAllFiltered(stoppageFilterDto).stream()
                .map(s -> StoppageResponseDto.of(s))
                .collect(Collectors.toList());
    }

    @PostMapping("/all_by_criteria_paged")
    public Page<StoppageResponseDto> getAllByCriteriaPaged(@RequestBody StoppageFilterDto stoppageFilterDto,
                                                      Pageable pageable) {
        return stoppageService.findEntitiesByDynamicCriteriaPaged(stoppageFilterDto, pageable)
                .map(s -> StoppageResponseDto.of(s));
    }

    @PostMapping("/all_by_criteria")
    public List<StoppageResponseDto> getAllByCriteria(@RequestBody StoppageFilterDto stoppageFilterDto) {
        return stoppageService.findEntitiesByDynamicCriteria(stoppageFilterDto).stream()
                .map(s -> StoppageResponseDto.of(s))
                .collect(Collectors.toList());
    }

}
