package school.sptech.sistema_estoque.enums;

public enum MensagemEmail {
    MENSAGEM_PENDENTE_COMPRA(1,"SOLICITAÇÃO ACEITA COM NECESSIDADE DE COMPRA DE MATERIAIS"),
    MENSAGEM_TUDO_CERTO(2,"SOLICITAÇÃO ACEITA E AGUARDANDO SEPARAÇÃO DE MATERIAIS");
    
    private final Integer codStatus;
    private final String descricao;
    MensagemEmail(Integer codStatus, String descricao) {
        this.codStatus = codStatus;
        this.descricao = descricao;
    }
    public Integer getCodStatus() {return codStatus;}
    public String getDescricao() {return descricao;}
}
