package com.example.Dtos;

import com.example.models.Category;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class FakeStoreProductDTO {

    private Long id;
    private String title;
    private String description;
    private String category;
    private String image;
    private double price;
}
