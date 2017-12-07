package myretail.repository;

import myretail.domain.Product;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface ProductRepository extends MongoRepository<Product, String> {
    public Product findByProductId(String productId);
}
