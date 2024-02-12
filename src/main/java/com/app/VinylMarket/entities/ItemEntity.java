package com.app.VinylMarket.entities;

import com.app.VinylMarket.enums.Format;
import com.app.VinylMarket.enums.Genre;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "Item")
public class ItemEntity {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column
    private String title;

    @Column
    private Format format;

    @Column
    private Integer rpm;

    @Column
    private Genre genre;

    @Column
    private Integer year_of_release;

    @Column
    private String condition_of_item;

    @Column
    private String other_info;

    @Column
    private String country;

    @Column
    private String label;

}
