package com.example.thirdpartyclients.faketstore;

import com.example.Dtos.FakeStoreProductDTO;
import com.example.Dtos.GenericProductDto;
import com.example.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class FakeStoreProductClient {

    //object created automatically by springboot since only one concrete class

    @Value("${fakestore.api.baseurl}")
    private String fakeStoreAPIBaseUrl;
    @Value("${fakestore.api.product}")
    private String fakeStoreProductPathUrl;
    private final String ProductPath = "/products";
    private String productUrl = fakeStoreAPIBaseUrl + ProductPath + "/{id}";
    private String getAllProductsUrl = fakeStoreAPIBaseUrl + ProductPath;

    RestTemplateBuilder restTemplateBuilder;

    @Autowired
    public FakeStoreProductClient(RestTemplateBuilder restTemplateBuilder,
                                  @Value("${fakestore.api.baseurl}") String fakeStoreAPIBaseUrl,
                                  @Value("${fakestore.api.product}") String fakeStoreProductPathUrl){
        this.restTemplateBuilder = restTemplateBuilder;
        this.productUrl = fakeStoreAPIBaseUrl + ProductPath + "/{id}";
        this.getAllProductsUrl  = fakeStoreAPIBaseUrl + ProductPath;
    }

    public FakeStoreProductDTO getProductById(Long id) throws NotFoundException {
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



        return fakeStoreProductDTO;
    }


    public FakeStoreProductDTO createProduct(GenericProductDto genericProductDto) {

        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDTO> response = restTemplate.postForEntity(
                getAllProductsUrl,
                genericProductDto,
                FakeStoreProductDTO.class
        );
        FakeStoreProductDTO fakeStoreProductDTO = response.getBody();



        return fakeStoreProductDTO;
    }


    public FakeStoreProductDTO[] getAllProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();


        ResponseEntity<FakeStoreProductDTO[]> response = restTemplate.getForEntity(
                getAllProductsUrl,
                FakeStoreProductDTO[].class
        );
        FakeStoreProductDTO[] fakeStoreProductDTO ;
        fakeStoreProductDTO = response.getBody();
//        GenericProductDto[] genericProductDto = new GenericProductDto[fakeStoreProductDTO.length];
//        for(int i=0;i<fakeStoreProductDTO.length;i++){
//            GenericProductDto genericProductDto1 = convertFakeStoreDtoToGenericProductDto(fakeStoreProductDTO[i]);
//
//            genericProductDto[i] = genericProductDto1;
//        }

        return  fakeStoreProductDTO;
    }


    public FakeStoreProductDTO UpdateProductById(Long id) throws NotFoundException{
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDTO> response = restTemplate.exchange(
                productUrl,
                HttpMethod.PUT,
                null,
                FakeStoreProductDTO.class,
                id
        );
        FakeStoreProductDTO fakeStoreProductDTO = response.getBody();
        if(fakeStoreProductDTO==null){
            throw new NotFoundException("Record with id" + id +" not found");
        }
        return fakeStoreProductDTO;
    }


    public FakeStoreProductDTO DeleteProductById(Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();

        ResponseEntity<FakeStoreProductDTO> response = restTemplate.exchange(
                productUrl,
                HttpMethod.DELETE,
                null,
                FakeStoreProductDTO.class,
                id
        ); // or execute method

        FakeStoreProductDTO fakeStoreProductDTO = response.getBody();

        return fakeStoreProductDTO;
    }

}
