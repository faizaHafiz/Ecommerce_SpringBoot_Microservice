package com.example.Dtos;

import com.example.models.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenericProductDto {
    private Long id;
    private String title;
    private String description;
    private String category;
    private String image;
    private double price;
}
