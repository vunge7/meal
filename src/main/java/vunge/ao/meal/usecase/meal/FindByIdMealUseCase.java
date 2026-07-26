package vunge.ao.meal.usecase.meal;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import vunge.ao.meal.dto.CategoryResponseDto;
import vunge.ao.meal.dto.MealResponseDto;
import vunge.ao.meal.exceptions.CategoryException;
import vunge.ao.meal.exceptions.MealException;
import vunge.ao.meal.repository.CategoryRepository;
import vunge.ao.meal.repository.MealRepository;
import vunge.ao.meal.util.BuilderMethods;
import vunge.ao.meal.util.Execute;
import vunge.ao.meal.util.Messages;

import java.util.UUID;


@Component
@RequiredArgsConstructor
public class FindByIdMealUseCase implements Execute<MealResponseDto, FindByIdMealUseCase.Input> {

    private final MealRepository repository;

    public record Input(UUID  id) {
    }

    @Transactional
    @Override
    public MealResponseDto execute(Input input) {
        var optical = repository.findById(input.id);
        if (optical.isPresent()) {
            return BuilderMethods
                    .toMealResponseDto(optical.get());
        }
        throw new MealException(Messages.NOT_EXIST_MEAL_ID, HttpStatus.NOT_FOUND);

    }

}
