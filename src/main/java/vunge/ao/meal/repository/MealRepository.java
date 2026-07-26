package vunge.ao.meal.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import vunge.ao.meal.entity.Category;
import vunge.ao.meal.entity.Meal;

import java.util.UUID;

public interface MealRepository extends JpaRepository<Meal, UUID>, JpaSpecificationExecutor<Meal> {

    boolean existsMealByTitle(String title);
    Page<Meal> findMealsByDeletedFalse(Pageable pageable);




}
