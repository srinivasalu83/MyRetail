package myretail.bootstrap;

import myretail.domain.Product;
import myretail.repository.ProductRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@EnableSwagger2
public class SpringMongodbBootstrap {

    private ProductRepository repository;

    private Logger log = Logger.getLogger(SpringMongodbBootstrap.class);

    @Autowired
    public void setProductRepository(ProductRepository repository) {
        this.repository = repository;
    }

    /*
    **inserting a produce to the NoSQL database: mongodb
     */
    @PostConstruct
    private void loadProducts() {
        Map<String, String> map = new HashMap<>();
        map.put("value", "13.49");
        map.put("currency_code", "USD");

        repository.save(new Product("13860428", "The LCD Blue Wide Screen TV",map));

        log.info("Saved  The LCD Blue Wide Screen TV - id:" + repository.findByProductId("13860428"));

        //Product phone = new Product();
        map.put("value", "800");
        map.put("currency_code", "USD");
        repository.save(new Product("16483589", "One Plus mobile",map));

        log.info("Saved One Plus mobile - id:" + repository.findByProductId("16483589"));
    }


    }



