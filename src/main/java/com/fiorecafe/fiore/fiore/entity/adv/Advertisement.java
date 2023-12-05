package com.fiorecafe.fiore.fiore.entity.adv;

import com.fiorecafe.fiore.fiore.entity.category.CategoryImage;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "advertisements")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Advertisement {
    @Id
    private Long id;

    private String description;

    @OneToOne()
    @JoinColumn(name = "adv_image_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private AdvImage advImage;


}
