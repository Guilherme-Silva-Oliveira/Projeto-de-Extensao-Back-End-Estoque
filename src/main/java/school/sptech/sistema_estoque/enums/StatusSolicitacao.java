package school.sptech.sistema_estoque.enums;

public enum StatusSolicitacao {
    RECEBIDA(1,"Solicitação Recebida"),
    ACEITA(2,"Solicitação Aceita"),
    REJEITADA(3,"Solicitação Rejeitada por Almoxarife"),
    PENDENTE_ENTREGA(4,"Solicitação Pendente de Entrega"),
    CANCELADA(5,"Solicitação Cancelada por Almoxarife"),
    FINALIZADA(6,"Solicitação Finalizada");

    private final Integer codStatus;
    private final String descricao;
    StatusSolicitacao(Integer codStatus,String descricao) {
        this.codStatus = codStatus;
        this.descricao = descricao;
    }
    public Integer getCodStatus() {return codStatus;}
    public String getDescricao() {return descricao;}
}
