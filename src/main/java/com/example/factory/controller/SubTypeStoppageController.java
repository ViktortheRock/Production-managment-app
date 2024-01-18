package com.example.factory.controller;

import com.example.factory.dto.ProductResponseDto;
import com.example.factory.dto.stoppage.BaseTypeStoppageDto;
import com.example.factory.dto.stoppage.SubTypeStoppageDto;
import com.example.factory.service.stoppage.BaseTypeStoppageService;
import com.example.factory.service.stoppage.SubTypeStoppageService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sub_type_stoppage")
public class SubTypeStoppageController {

    private SubTypeStoppageService stoppageService;

    public SubTypeStoppageController(SubTypeStoppageService stoppageService) {
        this.stoppageService = stoppageService;
    }

    @PostMapping
    public SubTypeStoppageDto create(@RequestBody SubTypeStoppageDto stoppageDto) {
        return stoppageService.create(stoppageDto);
    }

    @GetMapping("/{id}")
    public SubTypeStoppageDto get(@PathVariable("id") long stoppageId) {
        return stoppageService.read(stoppageId);
    }

    @PutMapping("/{id}")
    public SubTypeStoppageDto update(@PathVariable("id") long stoppageId,
                                     @RequestBody SubTypeStoppageDto stoppageDto) {
        stoppageDto.setId(stoppageId);
        return stoppageService.update(stoppageDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") long stoppageId) {
        stoppageService.delete(stoppageId);
    }

    @GetMapping("/all")
    public List<SubTypeStoppageDto> getAll() {
        return stoppageService.getAll();
    }

    @GetMapping("/all/by_base_stoppage/{id}")
    public List<SubTypeStoppageDto> getAllByMachineId(@PathVariable("id") long baseTypeId) {
        return stoppageService.getAllByBaseTypeStoppage(baseTypeId);
    }


}
