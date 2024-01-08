package com.example.services;

import com.example.Dtos.GenericProductDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("SelfServiceProduct")
public class SelfProductService implements ProductService{
    @Override
    public GenericProductDto getProductById(Long id) {
        return null;
    }

    @Override
    public GenericProductDto createProduct(GenericProductDto genericProductDto) {
        return null;
    }

    @Override
    public GenericProductDto[] getAllProducts() {
        return null;
    }

    @Override
    public GenericProductDto UpdateProductById(Long id) {
        return null;
    }

    @Override
    public GenericProductDto DeleteProductById(Long id) {
        return null;
    }
}
