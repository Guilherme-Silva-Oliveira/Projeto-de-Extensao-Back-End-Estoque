package school.sptech.sistema_estoque.model.estoque;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Material {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nomeMaterial;
    @ManyToOne private Categoria categoria;
    @ManyToOne private Almoxarifado almoxarifado;
    @ManyToOne private UnidadeMedida unidadeMedida;
    private Integer quantidade;
    @OneToMany(mappedBy = "material", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Limite> limites;
    @OneToMany(mappedBy = "material", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<PedidoEntrada> pedidosEntrada;
    @OneToMany(mappedBy = "material", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<PedidoSaida> pedidosSaida;

    @OneToMany(mappedBy = "material", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<CodigoBarras> codigosBarras;

    public Material(Integer id, String nomeMaterial, Categoria categoria, Almoxarifado almoxarifado, UnidadeMedida unidadeMedida, Integer quantidade) {
        this.id = id;
        this.nomeMaterial = nomeMaterial;
        this.categoria = categoria;
        this.almoxarifado = almoxarifado;
        this.unidadeMedida = unidadeMedida;
        this.quantidade = quantidade;
    }

    public Material() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomeMaterial() {
        return nomeMaterial;
    }

    public void setNomeMaterial(String nomeMaterial) {
        this.nomeMaterial = nomeMaterial;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Almoxarifado getAlmoxarifado() {
        return almoxarifado;
    }

    public void setAlmoxarifado(Almoxarifado almoxarifado) {
        this.almoxarifado = almoxarifado;
    }

    public UnidadeMedida getUnidadeMedida() {
        return unidadeMedida;
    }

    public void setUnidadeMedida(UnidadeMedida unidadeMedida) {
        this.unidadeMedida = unidadeMedida;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public List<Limite> getLimites() {
        return limites;
    }

    public void setLimites(List<Limite> limites) {
        this.limites = limites;
    }

    public List<PedidoEntrada> getPedidosEntrada() {
        return pedidosEntrada;
    }

    public void setPedidosEntrada(List<PedidoEntrada> pedidosEntrada) {
        this.pedidosEntrada = pedidosEntrada;
    }

    public List<PedidoSaida> getPedidosSaida() {
        return pedidosSaida;
    }

    public void setPedidosSaida(List<PedidoSaida> pedidosSaida) {
        this.pedidosSaida = pedidosSaida;
    }

    public List<CodigoBarras> getCodigosBarras() {
        return codigosBarras;
    }

    public void setCodigosBarras(List<CodigoBarras> codigosBarras) {
        this.codigosBarras = codigosBarras;
    }
}
