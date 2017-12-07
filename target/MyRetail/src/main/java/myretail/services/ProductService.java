package myretail.services;


import myretail.domain.Product;

public interface ProductService {
     Iterable<Product> listAllProducts();

     Product findByProductId(String id);

     Product saveProduct(Product product);

     void deleteProduct(String id);
}
