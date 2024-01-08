package com.example.services;

import com.example.Dtos.FakeStoreProductDTO;
import com.example.Dtos.GenericProductDto;
import com.example.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.xml.transform.sax.SAXResult;
import java.util.ArrayList;
import java.util.List;

@Service("fakeStoreProductService") //creates object of this service
public class FakeStoreProductService implements ProductService{
    //object created automatically by springboot since only one concrete class
    private String productUrl = "https://fakestoreapi.com/products/{id}";
    private String createProductUrl = "https://fakestoreapi.com/products";
    private String getAllProductsUrl = "https://fakestoreapi.com/products";
    private String updateProductUrl = "https://fakestoreapi.com/products/{id}";
    RestTemplateBuilder restTemplateBuilder;

    //injecting into my service
    @Autowired
    public FakeStoreProductService(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder = restTemplateBuilder;
    }

    public GenericProductDto convertFakeStoreDtoToGenericProductDto(FakeStoreProductDTO fakeStoreProductDTO){
        GenericProductDto genericProductDto = new GenericProductDto();
        genericProductDto.setId(fakeStoreProductDTO.getId());
        genericProductDto.setTitle(fakeStoreProductDTO.getTitle());
        genericProductDto.setImage(fakeStoreProductDTO.getImage());
        genericProductDto.setCategory(fakeStoreProductDTO.getCategory());
        genericProductDto.setDescription(fakeStoreProductDTO.getDescription());

        return genericProductDto;
    }
    @Override
    public GenericProductDto getProductById(Long id) throws NotFoundException {
        //RestTemplate restTemplate;//to create API calls hitting another API
        //restTemplate.//for restTemplate we need more configurations



        //RestTemplateBuilder restTemplateBuilder;
        RestTemplate restTemplate = restTemplateBuilder.build();

        ResponseEntity<FakeStoreProductDTO> response = restTemplate.getForEntity(
                productUrl,
                FakeStoreProductDTO.class,
                id
        );//get call
        //Response entity gets json along with other things associated with get
        //in java we dont use JSON. we convert JSON to java objects
        //FakeStoreProductDTO.class converts json to fakestoreproductdto obj

        FakeStoreProductDTO fakeStoreProductDTO = response.getBody();
        if(fakeStoreProductDTO==null){
            throw new NotFoundException("Product with id "+id+" does not exist");
        }
        GenericProductDto genericProductDto = convertFakeStoreDtoToGenericProductDto(fakeStoreProductDTO);

        return genericProductDto;
    }

    @Override
    public GenericProductDto createProduct(GenericProductDto genericProductDto) {

        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDTO> response = restTemplate.postForEntity(
            createProductUrl,
                genericProductDto,
                FakeStoreProductDTO.class
        );
        FakeStoreProductDTO fakeStoreProductDTO = response.getBody();

        GenericProductDto genericProductDto1 = convertFakeStoreDtoToGenericProductDto(fakeStoreProductDTO);

        return genericProductDto1;
    }

    @Override
    public GenericProductDto[] getAllProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();


        ResponseEntity<FakeStoreProductDTO[]> response = restTemplate.getForEntity(
                getAllProductsUrl,
                FakeStoreProductDTO[].class
        );
        FakeStoreProductDTO[] fakeStoreProductDTO ;
        fakeStoreProductDTO = response.getBody();
        GenericProductDto[] genericProductDto = new GenericProductDto[fakeStoreProductDTO.length];
        for(int i=0;i<fakeStoreProductDTO.length;i++){
            GenericProductDto genericProductDto1 = convertFakeStoreDtoToGenericProductDto(fakeStoreProductDTO[i]);

            genericProductDto[i] = genericProductDto1;
        }

        return genericProductDto;
    }

    @Override
    public GenericProductDto UpdateProductById(Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
//        GenericProductDto response = restTemplate.exchange(
//                updateProductUrl,
//                HttpMethod.PUT,
//                new HttpEntity<>()
//                FakeStoreProductDTO.class,
//                id
//        );
        return null;
    }

    @Override
    public GenericProductDto DeleteProductById(Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();

        ResponseEntity<FakeStoreProductDTO> response = restTemplate.exchange(
                productUrl,
                HttpMethod.DELETE,
                null,
                FakeStoreProductDTO.class,
                id
        ); // or execute method

        FakeStoreProductDTO fakeStoreProductDTO = response.getBody();
        GenericProductDto genericProductDto = convertFakeStoreDtoToGenericProductDto(fakeStoreProductDTO);
        return genericProductDto;
    }


}
