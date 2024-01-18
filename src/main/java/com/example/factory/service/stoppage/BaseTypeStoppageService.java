package com.example.factory.service.stoppage;

import com.example.factory.dto.stoppage.BaseTypeStoppageDto;

import java.util.List;

public interface BaseTypeStoppageService {

    BaseTypeStoppageDto create(BaseTypeStoppageDto stoppage);
    BaseTypeStoppageDto read(long id);
    List<BaseTypeStoppageDto> getAll();
    BaseTypeStoppageDto update(BaseTypeStoppageDto stoppage);
    void delete(long machineId);
}
