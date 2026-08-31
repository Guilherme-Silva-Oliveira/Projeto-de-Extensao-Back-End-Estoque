package school.sptech.sistema_estoque.enums;

public enum StatusAlertaSolicitacao {
    MATERIAIS_INSUFICIENTES(1,"MATERIAIS_INSUFICIENTES"),
    ESTOQUE_VAZIO(2,"ESTOQUE_VAZIO"),
    TUDO_CERTO(3,"TUDO_CERTO");

    private final Integer codStatus;
    private final String descricao;
    StatusAlertaSolicitacao(Integer codStatus, String descricao) {
        this.codStatus = codStatus;
        this.descricao = descricao;
    }
    public Integer getCodStatus() {return codStatus;}
    public String getDescricao() {return descricao;}
}
