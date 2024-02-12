package com.app.VinylMarket.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name="ItemArtist")
public class ItemArtistEntity {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name="item_id")
    private ItemEntity itemEntity;

    @ManyToOne
    @JoinColumn(name="artist_id")
    private ArtistEntity artistEntity;
}
