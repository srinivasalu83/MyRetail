package myretail.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "ProductId information is not in NoSQL Database")
public class ProductNotFoundException extends RuntimeException{
}
