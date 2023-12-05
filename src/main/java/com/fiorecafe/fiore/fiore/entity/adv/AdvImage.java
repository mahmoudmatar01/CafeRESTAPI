package com.fiorecafe.fiore.fiore.entity.adv;

import com.fiorecafe.fiore.fiore.entity.item.Item;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "adv_images")
public class AdvImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String type;

    private byte[] data;

    @Column(name = "adv_photo_url")
    private String advImageUrl;

}
