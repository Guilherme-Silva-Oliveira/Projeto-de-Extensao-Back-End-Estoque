package school.sptech.sistema_estoque.model.estoque;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import school.sptech.sistema_estoque.model.estoque.compound_id.PedidoEntradaId;

import java.time.LocalDateTime;

@Entity
@Table(name = "pedido_entrada")
@IdClass(PedidoEntradaId.class)
public class PedidoEntrada {

    @Id
    @ManyToOne
    @JoinColumn(name = "fornecedor_id")
    private Fornecedor fornecedor;

    @Id
    @ManyToOne
    @JoinColumn(name = "material_id")
    private Material material;

    private Integer quantidade;

    private LocalDateTime dataEntrada;

    public PedidoEntrada() {
    }

    public PedidoEntrada(Fornecedor fornecedor, Material material, Integer quantidade, LocalDateTime dataEntrada) {
        this.fornecedor = fornecedor;
        this.material = material;
        this.quantidade = quantidade;
        this.dataEntrada = dataEntrada;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public LocalDateTime getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(LocalDateTime dataEntrada) {
        this.dataEntrada = dataEntrada;
    }
}
