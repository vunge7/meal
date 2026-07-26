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
import vunge.ao.meal.usecase.meal.FindAllMealUseCase;
import vunge.ao.meal.usecase.meal.UpdateMealUseCase;

import java.util.UUID;

@RestController
@RequestMapping("/meals")
@Tag(name = "Meals", description = "The Meals API")
@RequiredArgsConstructor
public class MealController {
    private final CreateMealUseCase createMealUseCase;
    private final UpdateMealUseCase updateMealUseCase;
    private final FindAllMealUseCase findAllMealUseCase;

    @PostMapping
    @Operation(summary = "Create a new meal", description = "Create a new meal")
    public ResponseEntity<ApiResponse<MealResponseDto>> createMeal(
            @Valid @RequestBody MealRequestDto request) {
        var response = createMealUseCase.execute(
                new CreateMealUseCase.Input(request)
        );
        return ResponseEntity.ok(ApiResponse.successData(response));

    }

    @PutMapping("/{id}")
    @Operation(summary = "Create a new meal", description = "Create a new meal")
    public ResponseEntity<ApiResponse<MealResponseDto>> updateMeal(
            @PathVariable UUID id,
            @Valid @RequestBody MealRequestDto request) {
        var response = updateMealUseCase.execute(
                new UpdateMealUseCase.Input(id, request)
        );
        return ResponseEntity.ok(ApiResponse.successData(response));

    }


    @GetMapping
    @Operation(summary = "Find all meals", description = "Find all meals")
    public ResponseEntity<ApiResponse<Page<MealResponseDto>>> findAll(
            @RequestParam(defaultValue = "0") int pag,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String orderBy) {
        Pageable pageable = PageRequest.of(pag, size, Sort.by(orderBy));
        return ResponseEntity.ok(
                ApiResponse.successData(
                        findAllMealUseCase
                                .execute(
                                        new FindAllMealUseCase.Input(
                                                pageable
                                        )
                                )
                ));
    }


}
