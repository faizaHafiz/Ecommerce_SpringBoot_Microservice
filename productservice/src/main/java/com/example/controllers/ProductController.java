package com.example.controllers;

import com.example.Dtos.ExceptionDto;
import com.example.Dtos.GenericProductDto;
import com.example.exceptions.NotFoundException;
import com.example.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.NotActiveException;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
//    @Autowired field injection
//    private ProductService productService;
    private ProductService productService;
    @Autowired
    //qualifier tells which implementtion i want
    public ProductController(@Qualifier("fakeStoreProductService") ProductService productService){
        this.productService = productService;
    }

    @GetMapping()
    public GenericProductDto[] getAllProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("{id}")
    public GenericProductDto getProductById(@PathVariable("id") Long id) throws NotFoundException {
        return  productService.getProductById(id);
        // return "here is the product id: " + id;
    }

    @PutMapping("{id}")
    public GenericProductDto updateProductById(@PathVariable("id") Long id) throws NotFoundException{
        return productService.UpdateProductById(id);
    }

    @PostMapping()
    public GenericProductDto createProduct(@RequestBody GenericProductDto genericProductDto){
        return productService.createProduct(genericProductDto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<GenericProductDto> deleteProductById(@PathVariable("id") Long id){
        return new ResponseEntity<>(productService.DeleteProductById(id),HttpStatus.ACCEPTED);
    }

    // Specific to this controller only
//    @ExceptionHandler(NotFoundException.class) // called when an exception is thrown in controller
//    public ResponseEntity<ExceptionDto> handleNotFoundException(NotFoundException notFoundException){
//        return new ResponseEntity<>(new ExceptionDto(HttpStatus.NOT_FOUND, notFoundException.getMessage()),
//                HttpStatus.NOT_FOUND); //we are controlling the ResponseEntity
//    }

}
/*
* Product service - create interface
* for loose coupling - if more ways then
* here now we are using fakestoreAPI
* but in future it will change.
* */
