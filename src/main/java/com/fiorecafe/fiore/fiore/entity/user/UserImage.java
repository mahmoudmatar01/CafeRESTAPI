package com.fiorecafe.fiore.fiore.entity.user;

import com.fiorecafe.fiore.fiore.entity.category.Category;
import com.fiorecafe.fiore.fiore.utils.entity.ImageData;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_images")
@Builder
public class UserImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private byte[] data;

    private String type;

    @Column(nullable = false)
    private String title;

    private String userPhotoUrl;

}
