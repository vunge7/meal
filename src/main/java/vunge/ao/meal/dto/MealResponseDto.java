package vunge.ao.meal.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MealResponseDto {
    private UUID id;
    private String title;
    private String badge;
    private String description;
    private BigDecimal price;
    private String details;
    private UUID categoryId;
}
