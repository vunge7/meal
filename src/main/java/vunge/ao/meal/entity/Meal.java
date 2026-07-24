package vunge.ao.meal.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vunge.ao.meal.common.GenericValuesEntity;

import java.math.BigDecimal;


@Entity
@Table(name = "meals")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Meal extends GenericValuesEntity {
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;
    @Column(name = "badge")
    private String badge;
    @Column(name = "price", precision =  10, scale = 2)
    private BigDecimal price;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
