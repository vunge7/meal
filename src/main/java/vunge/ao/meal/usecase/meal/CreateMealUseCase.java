package vunge.ao.meal.usecase.meal;

import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import vunge.ao.meal.dto.MealRequestDto;
import vunge.ao.meal.dto.MealResponseDto;
import vunge.ao.meal.entity.Category;
import vunge.ao.meal.entity.Meal;
import vunge.ao.meal.exceptions.CategoryException;
import vunge.ao.meal.exceptions.MealException;
import vunge.ao.meal.repository.CategoryRepository;
import vunge.ao.meal.repository.MealRepository;
import vunge.ao.meal.util.BuilderMethods;
import vunge.ao.meal.util.Execute;
import vunge.ao.meal.util.Messages;

import java.util.Optional;


@Component
@RequiredArgsConstructor
public class CreateMealUseCase implements Execute<MealResponseDto, CreateMealUseCase.Input> {
    private final MealRepository repository;
    private final CategoryRepository categoryRepository;

    public record Input(MealRequestDto request) {
    }

    @Transactional
    @Override
    public MealResponseDto execute(Input input) {
        var request = input.request();

        // 1. Validações primeiro (lê do banco sem alterar estado em memória)
        validateCategory(request);
        validateMeal(request);

        // 2. Busca e construção do objeto
        var optionalCategory = categoryRepository.findById(request.categoryId());
        Meal meal = new Meal();
        preparedMeal(meal, optionalCategory, request);

        // 3. Persistência
        var response = repository.save(meal);

        return BuilderMethods.toMealResponseDto(response);
    }

    private @NotNull void preparedMeal(Meal meal, Optional<Category> optionalCategory, MealRequestDto request) {
        var category = optionalCategory.orElseThrow();
        meal.setTitle(request.title());
        meal.setDescription(request.description());
        meal.setPrice(request.price());
        meal.setDetails(request.details());
        meal.setCategory(category);
        category.addMeal(meal);
    }

    private void validateMeal(MealRequestDto request) {
        if (repository.existsByTitle(request.title())) {
            throw new MealException(
                    Messages.EXISTS_DESCRIPTION,
                    HttpStatus.CONFLICT
            );
        }
    }

    private void validateCategory(MealRequestDto request) {
        if (!categoryRepository.existsById(request.categoryId())) {
            throw new CategoryException(
                    Messages.NOT_EXIST_CATEGORY,
                    HttpStatus.NOT_FOUND
            );
        }
    }
}
