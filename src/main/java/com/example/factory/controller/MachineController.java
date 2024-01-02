package com.example.factory.controller;

import com.example.factory.dto.MachineDto;
import com.example.factory.model.Machine;
import com.example.factory.service.MachineService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/machine")
public class MachineController {

    private MachineService machineService;

    public MachineController(MachineService machineService) {
        this.machineService = machineService;
    }

    @PostMapping
    public MachineDto create(@RequestBody Machine machine) {
        return MachineDto.of(machineService.create(machine));
    }

    @GetMapping("/{id}")
    public MachineDto get(@PathVariable("id") long machineId) {
        return MachineDto.of(machineService.read(machineId));
    }

    @PutMapping("/{machine_id}")
    public MachineDto update(@PathVariable("machine_id") long machineId,
                                     @RequestBody MachineDto machineDto) {
        Machine machine = Machine.of(machineDto);
        machine.setId(machineId);
        return machineDto.of(machineService.update(machine));
    }

    @DeleteMapping("/{machine_id}")
    public void delete(@PathVariable("machine_id") long machineId) {
        machineService.delete(machineId);
    }

    @GetMapping("/all")
    public List<MachineDto> getAll() {
        return machineService.getAll().stream()
                .map(m -> MachineDto.of(m))
                .collect(Collectors.toList());
    }

}
