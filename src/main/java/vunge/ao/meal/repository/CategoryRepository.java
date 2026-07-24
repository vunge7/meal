package vunge.ao.meal.repository;

import org.hibernate.validator.constraints.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import vunge.ao.meal.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, UUID> {

    boolean existsCategoriesByDescription(String description);
}
