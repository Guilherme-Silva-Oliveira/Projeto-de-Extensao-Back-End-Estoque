package school.sptech.sistema_estoque.exception;

public class InvalidSolicitacaoRequestException extends RuntimeException {
    public InvalidSolicitacaoRequestException(String message) {
        super(message);
    }
}
