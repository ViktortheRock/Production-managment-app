package com.example.factory.service.implement.stoppage;

import com.example.factory.dto.stoppage.SubTypeStoppageDto;
import com.example.factory.model.stoppage.SubTypeStoppage;
import com.example.factory.repositoty.stoppage.BaseTypeStoppageRepository;
import com.example.factory.repositoty.stoppage.SubTypeStoppageRepository;
import com.example.factory.service.stoppage.SubTypeStoppageService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SubTypeStoppageServiceImpl implements SubTypeStoppageService {

    private SubTypeStoppageRepository subStoppageRepository;
    private BaseTypeStoppageRepository baseStoppageRepository;

    public SubTypeStoppageServiceImpl(SubTypeStoppageRepository subStoppageRepository, BaseTypeStoppageRepository baseStoppageRepository) {
        this.subStoppageRepository = subStoppageRepository;
        this.baseStoppageRepository = baseStoppageRepository;
    }

    @Override
    public SubTypeStoppageDto create(SubTypeStoppageDto stoppageDto) {
        Optional<SubTypeStoppage> stoppageFromDb = subStoppageRepository.findByName(stoppageDto.getName());
        if (stoppageFromDb.isPresent()) {
            throw new RuntimeException(String.format("Such type of stoppage %s already exists", stoppageDto.getName()));
        }
        SubTypeStoppage stoppage = SubTypeStoppage.of(stoppageDto);
        stoppage.setBaseTypeStoppage(baseStoppageRepository.findById(stoppageDto.getBaseTypeStoppageId()).get());
        return SubTypeStoppageDto.of(subStoppageRepository.save(stoppage));
    }

    @Override
    @Transactional(readOnly = true)
    public SubTypeStoppageDto read(long id) {
        return SubTypeStoppageDto.of(subStoppageRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Machine with id " + id + " not found")));
    }

    @Override
    public SubTypeStoppageDto update(SubTypeStoppageDto stoppageDto) {
        read(stoppageDto.getId());
        SubTypeStoppage stoppage = SubTypeStoppage.of(stoppageDto);
        stoppage.setBaseTypeStoppage(baseStoppageRepository.findById(stoppageDto.getBaseTypeStoppageId()).get());
        return SubTypeStoppageDto.of(subStoppageRepository.save(stoppage));
    }

    @Override
    public void delete(long stoppageId) {
        read(stoppageId);
        subStoppageRepository.deleteById(stoppageId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SubTypeStoppageDto> getAll() {
        return subStoppageRepository.findAll().stream()
                .map(s -> SubTypeStoppageDto.of(s))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<SubTypeStoppageDto> getAllByBaseTypeStoppage(long id) {
        return subStoppageRepository.findByBaseTypeStoppage_Id(id).stream()
                .map(s -> SubTypeStoppageDto.of(s))
                .collect(Collectors.toList());
    }
}
