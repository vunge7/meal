package vunge.ao.meal.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.util.UUID;

import java.math.BigDecimal;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryResponseDto{
    private UUID id;
    private String description;
}
