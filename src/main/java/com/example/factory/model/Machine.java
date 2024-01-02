package com.example.factory.model;

import com.example.factory.dto.MachineDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data @Builder
@NoArgsConstructor @AllArgsConstructor
public class Machine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

//    @ManyToMany
//    @JoinTable(name = "machines_products",
//    joinColumns = @JoinColumn(name = "machine_id"),
//    inverseJoinColumns = @JoinColumn(name = "product_id"))
    @OneToMany(mappedBy = "machine", fetch = FetchType.LAZY)
//    @JsonIgnore
    private List<Product> products;

    public static Machine of(MachineDto machineDto) {
        return Machine.builder()
                .name(machineDto.getName())
                .build();
    }
}
