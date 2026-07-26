package vunge.ao.meal.usecase.category;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import vunge.ao.meal.dto.CategoryResponseDto;
import vunge.ao.meal.exceptions.CategoryException;
import vunge.ao.meal.repository.CategoryRepository;
import vunge.ao.meal.util.BuilderMethods;
import vunge.ao.meal.util.Execute;
import vunge.ao.meal.util.Messages;

import java.util.UUID;


@Component
@RequiredArgsConstructor
public class FindByIdCategoryUseCase implements Execute<CategoryResponseDto, FindByIdCategoryUseCase.Input> {

    private final CategoryRepository repository;

    public record Input(UUID  id) {
    }

    @Transactional
    @Override
    public CategoryResponseDto execute(Input input) {
        var optical = repository.findById(input.id);
        if (optical.isPresent()) {
            return BuilderMethods
                    .toMealResponseDto(optical.get());
        }
        throw new CategoryException(Messages.NOT_EXIST_CATEGORY, HttpStatus.NOT_FOUND);

    }

}
