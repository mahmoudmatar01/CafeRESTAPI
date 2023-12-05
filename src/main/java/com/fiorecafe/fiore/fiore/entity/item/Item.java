package com.fiorecafe.fiore.fiore.entity.item;
import com.fiorecafe.fiore.fiore.entity.category.Category;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "items")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemId;

    private String itemName;

    private String description;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "item_image_id")
    private ItemImage itemImage;

    private String imageUrl;

    private String components;

    private double price;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

}
