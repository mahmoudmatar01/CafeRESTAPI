package com.fiorecafe.fiore.fiore.utils.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@MappedSuperclass
@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImageData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String title;

    private String type;

    private byte[] data;

    public ImageData(String title,String type,byte[]data){
        this.data=data;
        this.title=title;
        this.type=type;
    }
}