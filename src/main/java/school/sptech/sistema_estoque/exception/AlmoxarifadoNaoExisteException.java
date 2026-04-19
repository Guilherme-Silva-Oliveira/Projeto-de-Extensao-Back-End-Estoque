package school.sptech.sistema_estoque.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AlmoxarifadoNaoExisteException extends RuntimeException {
    public AlmoxarifadoNaoExisteException(String message) {
        super(message);
    }
}
