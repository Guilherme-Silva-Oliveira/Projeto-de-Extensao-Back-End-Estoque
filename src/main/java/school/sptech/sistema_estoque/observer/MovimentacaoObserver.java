package school.sptech.sistema_estoque.observer;

// qualquer classe que queira ser notificada sobre as movimentações de estoque deve implementar essa interface (logs)
// o observer não precisa reconhecer nenhum notificador (os novos são ja implementados com o codigo que ja existe)


public interface MovimentacaoObserver {
    void gerarLogs(String mensagem);

    void atualizar(String mensagem);

    //       void gerarLogs(String tipoMovimentacao, String material, Integer quantidade);

}
