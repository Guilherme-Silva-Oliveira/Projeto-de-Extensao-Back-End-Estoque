package school.sptech.sistema_estoque.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidFornecedorRequestException extends RuntimeException {
    public InvalidFornecedorRequestException(String message) {
        super(message);
    }
}
