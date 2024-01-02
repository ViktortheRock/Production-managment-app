package com.example.factory.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder
@NoArgsConstructor @AllArgsConstructor
public class ProductionThreadDto {

    private String threadName;
    private String state;

    public static ProductionThreadDto of(String name, String state) {
        return ProductionThreadDto.builder()
                .threadName(name)
                .state(state)
                .build();
    }
}
