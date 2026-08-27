package school.sptech.sistema_estoque.model.estoque;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import school.sptech.sistema_estoque.model.estoque.compound_id.PedidoEntradaId;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
@IdClass(PedidoEntradaId.class)
public class PedidoEntrada {

    private Integer professorId;
    @Id @ManyToOne @JoinColumn(name = "fornecedor_id")
    private Fornecedor fornecedor;
    @Id @ManyToOne @JoinColumn(name = "material_id")
    private Material material;
    private Integer quantidade;
    private LocalDateTime dataEntrada;
    @Column(name = "is_devolucao", nullable = false)
    private boolean isDevolucao;

    public PedidoEntrada() {}
    public PedidoEntrada(Fornecedor fornecedor, Material material, Integer quantidade, LocalDateTime dataEntrada) {
        this.fornecedor = fornecedor;
        this.material = material;
        this.quantidade = quantidade;
        this.dataEntrada = dataEntrada;
    }

    public PedidoEntrada(Fornecedor fornecedor, Material material, Integer quantidade, LocalDateTime dataEntrada, boolean isDevolucao) {
        this.fornecedor = fornecedor;
        this.material = material;
        this.quantidade = quantidade;
        this.dataEntrada = dataEntrada;
        this.isDevolucao = isDevolucao;
    }
}
