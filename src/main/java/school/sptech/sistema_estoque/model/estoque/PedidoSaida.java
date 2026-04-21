package school.sptech.sistema_estoque.model.estoque;

import jakarta.persistence.*;
import school.sptech.sistema_estoque.model.estoque.compound_id.PedidoSaidaId;
import java.time.LocalDateTime;

@Entity
@Table(name = "pedido_saida")
@IdClass(PedidoSaidaId.class)
public class PedidoSaida {

    @Id
    @ManyToOne
    @JoinColumn(name = "material_id")
    private Material material;
    @Id
    @ManyToOne
    @JoinColumn(name = "solicitacao_id")
    private Solicitacao solicitacao;
    private Integer quantidade;
    private LocalDateTime dataSolicitacao;
    @ManyToOne
    @JoinColumn(name = "escala_id")
    private Escala escala;

    public PedidoSaida(Material material, Solicitacao solicitacao, Integer quantidade, LocalDateTime dataSolicitacao, Escala escala) {
        this.material = material;
        this.solicitacao = solicitacao;
        this.quantidade = quantidade;
        this.dataSolicitacao = dataSolicitacao;
        this.escala = escala;
    }

    public PedidoSaida() {
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public Solicitacao getSolicitacao() {
        return solicitacao;
    }

    public void setSolicitacao(Solicitacao solicitacao) {
        this.solicitacao = solicitacao;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public LocalDateTime getDataSolicitacao() {
        return dataSolicitacao;
    }

    public void setDataSolicitacao(LocalDateTime dataSolicitacao) {
        this.dataSolicitacao = dataSolicitacao;
    }

    public Escala getEscala() {
        return escala;
    }

    public void setEscala(Escala escala) {
        this.escala = escala;
    }
}
