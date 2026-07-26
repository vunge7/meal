package vunge.ao.meal.usecase.category;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import vunge.ao.meal.dto.CategoryResponseDto;
import vunge.ao.meal.repository.CategoryRepository;
import vunge.ao.meal.util.BuilderMethods;
import vunge.ao.meal.util.Execute;


@Component
@RequiredArgsConstructor
public class FindAllCategoryUseCase implements Execute<Page<CategoryResponseDto>, FindAllCategoryUseCase.Input> {

    private final CategoryRepository repository;

    public record Input(Pageable pageable) {
    }

    @Transactional
    @Override
    public Page<CategoryResponseDto> execute(Input input) {
        var categories = repository.findAll(input.pageable);
        return categories.map(BuilderMethods::toMealResponseDto);
    }

}
