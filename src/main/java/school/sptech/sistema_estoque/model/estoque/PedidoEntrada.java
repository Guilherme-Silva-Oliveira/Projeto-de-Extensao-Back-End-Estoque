package school.sptech.sistema_estoque.model.estoque;

import jakarta.persistence.*;
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

    @Column(name = "is_devolucao", nullable = false)
    private boolean isDevolucao;

    public PedidoEntrada() {
    }

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

    public Boolean getDevolucao() {
        return isDevolucao;
    }

    public void setDevolucao(Boolean devolucao) {
        isDevolucao = devolucao;
    }
}
