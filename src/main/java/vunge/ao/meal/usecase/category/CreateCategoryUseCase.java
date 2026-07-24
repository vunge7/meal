package vunge.ao.meal.usecase.category;

import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import vunge.ao.meal.dto.CategoryRequestDto;
import vunge.ao.meal.dto.CategoryResponseDto;
import vunge.ao.meal.entity.Category;
import vunge.ao.meal.exceptions.CategoryException;
import vunge.ao.meal.repository.CategoryRepository;
import vunge.ao.meal.util.BuilderMethods;
import vunge.ao.meal.util.Execute;
import vunge.ao.meal.util.Messages;


@Component
@RequiredArgsConstructor
public class CreateCategoryUseCase implements Execute<CategoryResponseDto, CreateCategoryUseCase.Input> {

    private final CategoryRepository repository;
    public record Input(CategoryRequestDto categoryRequestDto) {
    }

    @Transactional
    @Override
    public CategoryResponseDto execute(Input input) {
        var request = input.categoryRequestDto;
        Category category = new Category();
        preparedCategory(category, request);
        validate(request);
        var response = repository.save(category);
        return BuilderMethods
                .toCategoryResponseDto(response);
    }


    private @NotNull void preparedCategory(Category category, CategoryRequestDto request) {
        category.setDescription(request.description());
    }

    private void validate(CategoryRequestDto request) {
        if (repository.existsCategoriesByDescription(request.description())) {
            throw new CategoryException(
                    Messages.EXISTS_DESCRIPTION,
                    HttpStatus.CONFLICT
            );
        }
    }

}
