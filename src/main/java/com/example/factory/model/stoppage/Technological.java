package com.example.factory.model.stoppage;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
//@Data @Builder
//@NoArgsConstructor @AllArgsConstructor
@DiscriminatorValue("technology")
public class Technological extends Planned{
}
