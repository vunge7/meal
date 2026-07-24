package vunge.ao.meal.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

public record CategoryRequestDto(
       @NotNull String title,
       @NotNull String description,
       @NotNull String badge,
       @NotNull BigDecimal price
) {
}
