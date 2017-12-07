package myretail.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Product information is not availble in the NoSQL Database.")
public class ProductMisMatchException extends RuntimeException{
}
