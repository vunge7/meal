package vunge.ao.meal.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vunge.ao.meal.common.ApiResponse;
import vunge.ao.meal.dto.CategoryRequestDto;
import vunge.ao.meal.dto.CategoryResponseDto;
import vunge.ao.meal.repository.CategoryRepository;
import vunge.ao.meal.usecase.category.CreateCategoryUseCase;

@RestController
@RequestMapping("/categories")
@Tag(name = "Categories", description = "The Categories API")
@RequiredArgsConstructor
public class CategoryController {
    private final CreateCategoryUseCase createCategoryUseCase;

    @PostMapping
    @Operation(summary = "Create a new category", description = "Create a new category")
    public ResponseEntity<ApiResponse<CategoryResponseDto>> getCategoryRepository(
            @RequestBody CategoryRequestDto request) {
        var response = createCategoryUseCase.execute(
                new CreateCategoryUseCase.Input(request)
        );
        return ResponseEntity.ok(ApiResponse.successData(response));

    }

}
