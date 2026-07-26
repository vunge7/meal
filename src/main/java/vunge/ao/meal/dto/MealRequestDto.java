package vunge.ao.meal.dto;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record MealRequestDto(
        @NotNull String title,
        @NotNull String description,
        @NotNull String badge,
        @NotNull BigDecimal price,
        String details
) {
}
