package org.example.entity;

import lombok.Data;
import lombok.NonNull;

@Data
public class Warning {
  @NonNull private Double distance;
}
