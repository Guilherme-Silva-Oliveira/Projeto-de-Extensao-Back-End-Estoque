package school.sptech.sistema_estoque.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntidadeNaoExisteException.class)
    public ResponseEntity<Map<String, Object>> handleEntidadeNaoExiste(EntidadeNaoExisteException e) {
        Map<String, Object> erro = new HashMap<>();
        erro.put("timestamp", LocalDateTime.now());
        erro.put("status", HttpStatus.NOT_FOUND.value());
        erro.put("erro", "Not Found");
        erro.put("mensagem", e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    @ExceptionHandler(EntidadeInvalidException.class)
    public ResponseEntity<Map<String, Object>> handleEntidadeInvalida(EntidadeInvalidException e) {
        Map<String, Object> erro = new HashMap<>();
        erro.put("timestamp", LocalDateTime.now());
        erro.put("status", HttpStatus.BAD_REQUEST.value());
        erro.put("erro", "Bad Request");
        erro.put("mensagem", e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }

    @ExceptionHandler(EntidadeConflictException.class)
    public ResponseEntity<Map<String, Object>> handleEntidadeJaExistente(EntidadeConflictException e) {
        Map<String, Object> erro = new HashMap<>();
        erro.put("timestamp", LocalDateTime.now());
        erro.put("status", HttpStatus.CONFLICT.value());
        erro.put("erro", "Conflict");
        erro.put("mensagem", e.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(erro);
    }
}
