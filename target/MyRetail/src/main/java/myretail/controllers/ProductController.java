package myretail.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import myretail.domain.Product;
import myretail.exception.ProductMisMatchException;
import myretail.services.ProductService;
import myretail.exception.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


import java.util.HashMap;
import java.util.Map;

/*
** ProductController defines the REST API endpoints
 */
@SuppressWarnings("unchecked")
@RestController
@Api(value="onlinestore", description="Operations pertaining to products in Online Store")
public class ProductController {

    @Autowired
    private ProductService repository;

    /**
     * Gets productId information from Target service and gives out response of price
     * and product information from MongoDb database
     * @param Id Id of product we need information about.
     * @return
     * @throws ProductNotFoundException
     */
    @ApiOperation(value = "View a list of available products")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    @RequestMapping(value = "/products/{id}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody Product getProductInfo(@PathVariable String Id, Model model) throws ProductNotFoundException {

        RestTemplate restTemplate = new RestTemplate();
        Product product = new Product();

        String url = "http://redsky.target.com/v1/pdp/tcin/"+ Id +"?excludes=taxonomy,price,promotion,bulk_ship,rating_and_review_reviews,rating_and_review_statistics,question_answer_statistics";
        Map<String, String> urlVariables = new HashMap<String, String>();
        urlVariables.put("id", Id);

        ObjectMapper infoMapper = new ObjectMapper();
        Map<String, Map> infoMap;

        try {

            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class, urlVariables);
            infoMap = infoMapper.readValue(response.getBody(), Map.class);

            product.productId = Id;
            Map<String,Map> productMap = infoMap.get("product");
            Map<String,Map> itemMap = productMap.get("item");
            Map<String,String> prodDescrMap = itemMap.get(("product_description"));
            product.title = prodDescrMap.get("title");

            Product productInfoFromRepo = repository.findByProductId(Id);

            product.current_price = productInfoFromRepo.current_price;

        }
        catch (Exception e) {
            throw new ProductNotFoundException();
        }


        return product;
    }

    /**
     * List the products from MongoDB database
     * @param Id Id of product we need information about.
     */

    @RequestMapping(value = "/products/list/{id}", method= RequestMethod.GET, produces = "application/json")
    public Iterable<Product> list(Model model){
        Iterable<Product> productList = repository.listAllProducts();
        return productList;
    }
    /**
     * Stores the product information for the given productID
     * @param prodInfo Product info JSON request body
     * @param Id Id of product that need to be stored.
     * @return
     * @throws ProductMisMatchException
     */
    @ApiOperation(value = "Update a product")
    @RequestMapping(value = "/products/{id}", method = RequestMethod.PUT, produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity modifyPrice(@PathVariable ("id") String Id,@RequestBody Product prodInfo ) throws ProductMisMatchException
    {
        Product product = new Product();
        Product storedProduct = repository.findByProductId(Id);
        storedProduct.setTitle(storedProduct.getTitle());
        product.current_price = storedProduct.current_price;
        repository.saveProduct(storedProduct);
        //return new ResponseEntity("Product updated successfully", HttpStatus.OK);
        return new ResponseEntity("Product updated successfully", HttpStatus.OK);
    }

}