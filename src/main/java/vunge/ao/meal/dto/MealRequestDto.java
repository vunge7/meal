package vunge.ao.meal.dto;

import jakarta.validation.constraints.NotNull;


import java.math.BigDecimal;
import java.util.UUID;

public record MealRequestDto(
        @NotNull String title,
        @NotNull String description,
        @NotNull String badge,
        @NotNull BigDecimal price,
        String details,
        @NotNull  UUID categoryId
) {
}
