package com.example.factory.service.stoppage;

import com.example.factory.dto.stoppage.SubTypeStoppageDto;

import java.util.List;

public interface SubTypeStoppageService {

    SubTypeStoppageDto create(SubTypeStoppageDto stoppage);
    SubTypeStoppageDto read(long id);
    List<SubTypeStoppageDto> getAll();
    SubTypeStoppageDto update(SubTypeStoppageDto stoppage);
    void delete(long machineId);
    List<SubTypeStoppageDto> getAllByBaseTypeStoppage(long id);
}
