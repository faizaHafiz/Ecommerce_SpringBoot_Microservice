package com.example.services;

import com.example.Dtos.GenericProductDto;
import com.example.exceptions.NotFoundException;

import java.util.List;

public interface ProductService {
    public GenericProductDto getProductById(Long id) throws NotFoundException;
    //we are returning dto instead of model from service
    public GenericProductDto createProduct(GenericProductDto genericProductDto);


    public GenericProductDto[] getAllProducts();

    public GenericProductDto UpdateProductById(Long id) throws NotFoundException;

    public GenericProductDto DeleteProductById(Long id);
}
