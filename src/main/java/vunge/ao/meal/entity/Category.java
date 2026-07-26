package vunge.ao.meal.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vunge.ao.meal.common.GenericValuesEntity;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categories")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Category extends GenericValuesEntity {
    private String description;
    @OneToMany(
            mappedBy = "category" ,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Meal> meals = new ArrayList<>();


    public void addMeal(Meal meal) {
        meals.add(meal);
        meal.setCategory(this);
    }
    public void removeMeal(Meal meal) {
        meals.remove(meal);
        meal.setCategory(null);
    }

}
