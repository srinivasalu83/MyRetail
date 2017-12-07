package myretail.controllers.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
/*
** The @EnableSwagger2 annotation enables Swagger support in the class
 */
@EnableSwagger2
public class SwaggerConfig {
    /*
    **Initialize  the Spring Boot configuration using
    * Docket
     */
    @Bean
    public Docket productApi() {
        ApiInfo apiInfo = new ApiInfo("My Retail API", "API to store and retrieve data from MyRetail", "1",
                "http://terms-of-service.url",
                "srinivasalu", "Apache License Version 2.0", "https://www.apache.org/licenses/LICENSE-2.0");

        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(regex("/products.*"))
                .build()
                .pathMapping("/")
                .useDefaultResponseMessages(false);
    }
}
