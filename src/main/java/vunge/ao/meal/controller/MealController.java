package vunge.ao.meal.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vunge.ao.meal.common.ApiResponse;
import vunge.ao.meal.dto.CategoryRequestDto;
import vunge.ao.meal.dto.CategoryResponseDto;
import vunge.ao.meal.dto.MealRequestDto;
import vunge.ao.meal.dto.MealResponseDto;
import vunge.ao.meal.usecase.category.CreateCategoryUseCase;
import vunge.ao.meal.usecase.category.DeleteCategoryUseCase;
import vunge.ao.meal.usecase.category.FindAllCategoryUseCase;
import vunge.ao.meal.usecase.category.FindByIdCategoryUseCase;
import vunge.ao.meal.usecase.meal.CreateMealUseCase;

import java.util.UUID;

@RestController
@RequestMapping("/meals")
@Tag(name = "Meals", description = "The Meals API")
@RequiredArgsConstructor
public class MealController {
    private final CreateMealUseCase createMealUseCase;

    @PostMapping
    @Operation(summary = "Create a new meal", description = "Create a new meal")
    public ResponseEntity<ApiResponse<MealResponseDto>> createMeal(
            @Valid @RequestBody MealRequestDto request) {
        var response = createMealUseCase.execute(
                new CreateMealUseCase.Input(request)
        );
        return ResponseEntity.ok(ApiResponse.successData(response));

    }


}
