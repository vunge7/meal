package vunge.ao.meal.util;

import vunge.ao.meal.dto.CategoryRequestDto;
import vunge.ao.meal.dto.CategoryResponseDto;
import vunge.ao.meal.entity.Category;

public class BuilderMethods {
    public static CategoryResponseDto toCategoryResponseDto(Category response) {
        return CategoryResponseDto.builder()
                .id(response.getId())
                .description(response.getDescription())
                .build();
    }
}
