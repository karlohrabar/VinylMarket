package com.app.VinylMarket.dto;

import com.app.VinylMarket.enums.Format;
import com.app.VinylMarket.enums.Genre;
import com.app.VinylMarket.enums.ItemStatus;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemDto {

    private UUID id;
    private String title;
    private Format format;
    private Integer rpm;
    private Genre genre;
    @Range(min = 1900,max = 2024,message = "Year not valid!")
    private Integer year_of_release;
    private String condition_of_item;
    private String other_info;
    private String country;
    private String label;
    private Integer price;
    private String photo;
    private ItemStatus itemStatus;

    public String getPhotoPath(){
        if(photo == null)
            return null;
        return "/item-photos/" + photo;
    }
}