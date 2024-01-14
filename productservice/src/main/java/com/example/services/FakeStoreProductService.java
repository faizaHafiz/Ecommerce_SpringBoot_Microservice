package com.example.services;

import com.example.Dtos.FakeStoreProductDTO;
import com.example.Dtos.GenericProductDto;
import com.example.exceptions.NotFoundException;
import com.example.thirdpartyclients.faketstore.FakeStoreProductClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;


@Primary //incase we dont want to use qualifiers to tell controller which obj to create
@Service("fakeStoreProductService") //creates object of this service
public class FakeStoreProductService implements ProductService{
    //object created automatically by springboot since only one concrete class
    private FakeStoreProductClient fakeStoreProductClient;



    //injecting into my service
    @Autowired
    public FakeStoreProductService(FakeStoreProductClient fakeStoreProductClient){
        this.fakeStoreProductClient = fakeStoreProductClient;
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
//        RestTemplate restTemplate = restTemplateBuilder.build();
//
//        ResponseEntity<FakeStoreProductDTO> response = restTemplate.getForEntity(
//                productUrl,
//                FakeStoreProductDTO.class,
//                id
//        );//get call
        //Response entity gets json along with other things associated with get
        //in java we dont use JSON. we convert JSON to java objects
        //FakeStoreProductDTO.class converts json to fakestoreproductdto obj

//        FakeStoreProductDTO fakeStoreProductDTO = response.getBody();
//        if(fakeStoreProductDTO==null){
//            throw new NotFoundException("Product with id "+id+" does not exist");
//        }
//        GenericProductDto genericProductDto = convertFakeStoreDtoToGenericProductDto(fakeStoreProductDTO);
//
//        return genericProductDto;

        return convertFakeStoreDtoToGenericProductDto(fakeStoreProductClient.getProductById(id));
    }

    @Override
    public GenericProductDto createProduct(GenericProductDto genericProductDto) {

//        RestTemplate restTemplate = restTemplateBuilder.build();
//        ResponseEntity<FakeStoreProductDTO> response = restTemplate.postForEntity(
//            createProductUrl,
//                genericProductDto,
//                FakeStoreProductDTO.class
//        );
//        FakeStoreProductDTO fakeStoreProductDTO = response.getBody();
//
//        GenericProductDto genericProductDto1 = convertFakeStoreDtoToGenericProductDto(fakeStoreProductDTO);
//
//        return genericProductDto1;
        return convertFakeStoreDtoToGenericProductDto(fakeStoreProductClient.createProduct(genericProductDto));
    }

    @Override
    public GenericProductDto[] getAllProducts() {
//        RestTemplate restTemplate = restTemplateBuilder.build();
//
//
//        ResponseEntity<FakeStoreProductDTO[]> response = restTemplate.getForEntity(
//                getAllProductsUrl,
//                FakeStoreProductDTO[].class
//        );
        FakeStoreProductDTO[] fakeStoreProductDTO = fakeStoreProductClient.getAllProducts();
        GenericProductDto[] genericProductDto = new GenericProductDto[fakeStoreProductDTO.length];
        for(int i=0;i<fakeStoreProductDTO.length;i++){
            GenericProductDto genericProductDto1 = convertFakeStoreDtoToGenericProductDto(fakeStoreProductDTO[i]);

            genericProductDto[i] = genericProductDto1;
        }

        return genericProductDto;
    }

    @Override
    public GenericProductDto UpdateProductById(Long id) throws NotFoundException{
//        RestTemplate restTemplate = restTemplateBuilder.build();
//        GenericProductDto response = restTemplate.exchange(
//                updateProductUrl,
//                HttpMethod.PUT,
//                new HttpEntity<>()
//                FakeStoreProductDTO.class,
//                id
//        );
        return convertFakeStoreDtoToGenericProductDto(fakeStoreProductClient.UpdateProductById(id));
    }

    @Override
    public GenericProductDto DeleteProductById(Long id) {
//        RestTemplate restTemplate = restTemplateBuilder.build();
//
//        ResponseEntity<FakeStoreProductDTO> response = restTemplate.exchange(
//                productUrl,
//                HttpMethod.DELETE,
//                null,
//                FakeStoreProductDTO.class,
//                id
//        ); // or execute method
//
//        FakeStoreProductDTO fakeStoreProductDTO = response.getBody();
//        GenericProductDto genericProductDto = convertFakeStoreDtoToGenericProductDto(fakeStoreProductDTO);
//        return genericProductDto;
        return convertFakeStoreDtoToGenericProductDto(fakeStoreProductClient.DeleteProductById(id));
    }


}
