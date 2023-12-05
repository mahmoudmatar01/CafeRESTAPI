package com.fiorecafe.fiore.fiore.entity.best_selling;

import com.fiorecafe.fiore.fiore.entity.item.ItemImage;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "best_selling_items")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BestSellingItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String itemName;

    private String description;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "best_selling_item_image_id")
    private BestSellingItemImage itemImage;

    private String imageUrl;

    private String components;

    private double price;
}
