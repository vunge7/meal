package vunge.ao.meal.usecase.category;

import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import vunge.ao.meal.dto.CategoryRequestDto;
import vunge.ao.meal.entity.Category;
import vunge.ao.meal.exceptions.CategoryException;
import vunge.ao.meal.repository.CategoryRepository;
import vunge.ao.meal.util.Execute;
import vunge.ao.meal.util.Messages;
import java.util.UUID;


@Component
@RequiredArgsConstructor
public class DeleteCategoryUseCase implements Execute<String, DeleteCategoryUseCase.Input> {

    private final CategoryRepository repository;
    public record Input(UUID  id) {
    }

    @Transactional
    @Override
    public String execute(Input input) {
        var id = input.id;

        var optical = repository.findById(id);

        if(optical.isEmpty()) {
            throw new CategoryException(
                    Messages.NOT_EXIST_CATEGORY,
                    HttpStatus.NOT_FOUND
            );
        }
        repository.deleteById(id);
      return Messages.DELETE_SUCCESSFUL;
    }



}
