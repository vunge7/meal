package vunge.ao.meal.util;

import vunge.ao.meal.dto.CategoryResponseDto;
import vunge.ao.meal.dto.MealResponseDto;
import vunge.ao.meal.entity.Category;
import vunge.ao.meal.entity.Meal;

public class BuilderMethods {
    public static CategoryResponseDto toMealResponseDto(Category response) {
        return CategoryResponseDto.builder()
                .id(response.getId())
                .description(response.getDescription())
                .build();
    }


    public static MealResponseDto toMealResponseDto(Meal meal) {
        return MealResponseDto.builder()
                .id(meal.getId())
                .title(meal.getTitle())
                .description(meal.getDescription())
                .price(meal.getPrice())
                .details(meal.getDetails())
                .categoryId(meal.getCategory().getId())
                .build();
    }

}
