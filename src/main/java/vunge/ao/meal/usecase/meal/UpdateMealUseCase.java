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
import java.util.UUID;


@Component
@RequiredArgsConstructor
public class UpdateMealUseCase implements Execute<MealResponseDto, UpdateMealUseCase.Input> {
    private final MealRepository repository;
    private final CategoryRepository categoryRepository;

    public record Input(UUID id, MealRequestDto request) {
    }

    @Transactional
    @Override
    public MealResponseDto execute(Input input) {
        var request = input.request();

        // 1. Validações prévias
        validateCategory(request);
        validateMealForUpdate(input.id(), request);

        // 2. Busca o Meal existente no banco (ou lança exceção)
        Meal meal = repository.findById(input.id())
                .orElseThrow(() -> new MealException(
                        Messages.NOT_EXIST_MEAL_ID,
                        HttpStatus.NOT_FOUND
                ));

        // 3. Busca a Categoria
        Category category = categoryRepository.findById(request.categoryId())
                .orElseThrow(() -> new CategoryException(
                        Messages.NOT_EXIST_CATEGORY,
                        HttpStatus.NOT_FOUND
                ));

        // 4. Atualiza os dados do objeto existente
        updateMealFields(meal, category, request);

        // 5. Salva as alterações
        var response = repository.save(meal);

        return BuilderMethods.toMealResponseDto(response);
    }

    // Método auxiliar limpo (sem Optional e sem anotações inválidas no void)
    private void updateMealFields(Meal meal, Category category, MealRequestDto request) {
        meal.setTitle(request.title());
        meal.setDescription(request.description());
        meal.setBadge(request.badge());
        meal.setPrice(request.price());
        meal.setDetails(request.details());

        // Atualiza o relacionamento se mudou de categoria
        meal.setCategory(category);
    }

    private void validateMealForUpdate(UUID currentMealId, MealRequestDto request) {
        if (repository.existsByTitleAndIdNot(request.title(), currentMealId)) {
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
