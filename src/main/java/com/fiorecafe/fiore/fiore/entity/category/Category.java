package com.fiorecafe.fiore.fiore.entity.category;

import com.fiorecafe.fiore.fiore.entity.item.Item;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categories")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;

    private String categoryName;

    private String photoUrl;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "category_image_id")
    private CategoryImage categoryImage;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "category")
    private List<Item> items;

}
