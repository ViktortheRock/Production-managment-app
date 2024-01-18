package com.example.factory.controller;

import com.example.factory.dto.stoppage.BaseTypeStoppageDto;
import com.example.factory.service.stoppage.BaseTypeStoppageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/base_type_stoppage")
public class BaseTypeStoppageController {

    private BaseTypeStoppageService stoppageService;

    public BaseTypeStoppageController(BaseTypeStoppageService stoppageService) {
        this.stoppageService = stoppageService;
    }

    @PostMapping
    public BaseTypeStoppageDto create(@RequestBody BaseTypeStoppageDto stoppageDto) {
        return stoppageService.create(stoppageDto);
    }

    @GetMapping("/{id}")
    public BaseTypeStoppageDto get(@PathVariable("id") long stoppageId) {
        return stoppageService.read(stoppageId);
    }

    @PutMapping("/{id}")
    public BaseTypeStoppageDto update(@PathVariable("id") long stoppageId,
                                      @RequestBody BaseTypeStoppageDto stoppageDto) {
        stoppageDto.setId(stoppageId);
        return stoppageService.update(stoppageDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") long stoppageId) {
        try {
            stoppageService.delete(stoppageId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all")
    public List<BaseTypeStoppageDto> getAll() {
        return stoppageService.getAll();
    }

}
