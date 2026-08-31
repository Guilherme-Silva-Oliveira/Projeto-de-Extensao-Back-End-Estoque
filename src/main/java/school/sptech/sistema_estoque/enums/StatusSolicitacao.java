package school.sptech.sistema_estoque.enums;

public enum StatusSolicitacao {
    RECEBIDA(1,"RECEBIDA"),
    ACEITA(2,"ACEITA"),
    REJEITADA(3,"REJEITADA"),
    PENDENTE_ENTREGA(4,"PENDENTE_ENTREGA"),
    PRAZO_EXPIRADO(5,"PRAZO_EXPIRADO"),
    CANCELADA(6,"CANCELADA"),
    FINALIZADA(7,"FINALIZADA"),
    PENDENTE_DEVOLUCAO(8,"PENDENTE_DEVOLUÇÃO"),
    PENDENTE_COMPRA(9,"PENDENTE_COMPRA");

    private final Integer codStatus;
    private final String descricao;
    StatusSolicitacao(Integer codStatus,String descricao) {
        this.codStatus = codStatus;
        this.descricao = descricao;
    }
    public Integer getCodStatus() {return codStatus;}
    public String getDescricao() {return descricao;}
}
