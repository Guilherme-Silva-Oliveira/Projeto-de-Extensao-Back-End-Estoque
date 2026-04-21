package school.sptech.sistema_estoque.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EntidadeInvalidException extends RuntimeException {
    public EntidadeInvalidException(String message) {
        super(message);
    }
}
