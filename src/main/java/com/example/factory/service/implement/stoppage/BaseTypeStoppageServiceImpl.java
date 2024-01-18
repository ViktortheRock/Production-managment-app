package com.example.factory.service.implement.stoppage;

import com.example.factory.dto.stoppage.BaseTypeStoppageDto;
import com.example.factory.model.Machine;
import com.example.factory.model.stoppage.BaseTypeStoppage;
import com.example.factory.model.stoppage.Stoppage;
import com.example.factory.repositoty.stoppage.BaseTypeStoppageRepository;
import com.example.factory.service.stoppage.BaseTypeStoppageService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BaseTypeStoppageServiceImpl implements BaseTypeStoppageService {

    private BaseTypeStoppageRepository stoppageRepository;

    public BaseTypeStoppageServiceImpl(BaseTypeStoppageRepository stoppageRepository) {
        this.stoppageRepository = stoppageRepository;
    }

    @Override
    public BaseTypeStoppageDto create(BaseTypeStoppageDto stoppageDto) {
        Optional<BaseTypeStoppage> stoppageFromDb = stoppageRepository.findByName(stoppageDto.getName());
        if (stoppageFromDb.isPresent()) {
            throw new RuntimeException(String.format("Such type of stoppage %s already exists", stoppageDto.getName()));
        }
        return BaseTypeStoppageDto.of(stoppageRepository.save(BaseTypeStoppage.of(stoppageDto)));
    }

    @Override
    @Transactional(readOnly = true)
    public BaseTypeStoppageDto read(long id) {
        return BaseTypeStoppageDto.of(stoppageRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Machine with id " + id + " not found")));
    }

    @Override
    public BaseTypeStoppageDto update(BaseTypeStoppageDto stoppageDto) {
        read(stoppageDto.getId());
        return BaseTypeStoppageDto.of(stoppageRepository.save(BaseTypeStoppage.of(stoppageDto)));
    }

    @Override
    public void delete(long stoppageId) {
        read(stoppageId);
        try {
            stoppageRepository.deleteById(stoppageId);
        } catch (Exception e) {
            throw new RuntimeException("Тип простою використовується та не може бути видален");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<BaseTypeStoppageDto> getAll() {
        return stoppageRepository.findAll().stream()
                .map(s -> BaseTypeStoppageDto.of(s))
                .collect(Collectors.toList());
    }
}
