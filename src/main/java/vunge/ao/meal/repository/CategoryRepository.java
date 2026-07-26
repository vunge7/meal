package vunge.ao.meal.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import vunge.ao.meal.entity.Category;

import java.util.Optional;
import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID>, JpaSpecificationExecutor<Category> {



    boolean existsCategoriesByDescription(String description);

    Page<Category> findCategoriesByDeletedFalse(Pageable pageable);




}
