package vunge.ao.meal.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

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
import vunge.ao.meal.repository.CategoryRepository;
import vunge.ao.meal.usecase.category.CreateCategoryUseCase;
import vunge.ao.meal.usecase.category.FindAllCategoryUseCase;
import vunge.ao.meal.usecase.category.FindByIdCategoryUseCase;

import java.util.UUID;

@RestController
@RequestMapping("/categories")
@Tag(name = "Categories", description = "The Categories API")
@RequiredArgsConstructor
public class CategoryController {
    private final CreateCategoryUseCase createCategoryUseCase;
    private final FindAllCategoryUseCase findAllCategoryUseCase;
    private final FindByIdCategoryUseCase findByIdCategoryUseCase;


    @PostMapping
    @Operation(summary = "Create a new category", description = "Create a new category")
    public ResponseEntity<ApiResponse<CategoryResponseDto>> getCategoryRepository(
            @RequestBody CategoryRequestDto request) {
        var response = createCategoryUseCase.execute(
                new CreateCategoryUseCase.Input(request)
        );
        return ResponseEntity.ok(ApiResponse.successData(response));

    }

    @GetMapping
    @Operation(summary = "Find all categories", description = "Find all categories")
    public ResponseEntity<ApiResponse<Page<CategoryResponseDto>>> findAll(

            @RequestParam(defaultValue = "0") int pag,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String orderBy) {
        Pageable pageable = PageRequest.of(pag, size, Sort.by(orderBy));
        return ResponseEntity.ok(
                ApiResponse.successData(
                        findAllCategoryUseCase
                                .execute(
                                        new FindAllCategoryUseCase.Input(
                                                pageable
                                        )
                                )
                ));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find by id category", description = "Find all categories")
    public ResponseEntity<ApiResponse<CategoryResponseDto>> findById(UUID id) {
        return ResponseEntity.ok(
                ApiResponse.successData(
                        findByIdCategoryUseCase
                                .execute(
                                        new FindByIdCategoryUseCase.Input(
                                                id
                                        )
                                )
                ));
    }

}
