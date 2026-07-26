package vunge.ao.meal.usecase.meal;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import vunge.ao.meal.dto.CategoryResponseDto;
import vunge.ao.meal.dto.MealResponseDto;
import vunge.ao.meal.repository.CategoryRepository;
import vunge.ao.meal.repository.MealRepository;
import vunge.ao.meal.util.BuilderMethods;
import vunge.ao.meal.util.Execute;


@Component
@RequiredArgsConstructor
public class FindAllMealUseCase implements Execute<Page<MealResponseDto>, FindAllMealUseCase.Input> {

    private final MealRepository repository;

    public record Input(Pageable pageable) {
    }

    @Transactional
    @Override
    public Page<MealResponseDto> execute(Input input) {
        var meals = repository.findAll(input.pageable);
        return meals.map(BuilderMethods::toMealResponseDto);
    }

}
