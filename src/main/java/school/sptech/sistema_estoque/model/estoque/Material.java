package school.sptech.sistema_estoque.model.estoque;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Id;

@Entity
public class Material {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nomeMaterial;
    @ManyToOne private Categoria categoria;
    @ManyToOne private Almoxarifado almoxarifado;
    @ManyToOne private UnidadeMedida unidadeMedida;

    public Material(Integer id, String nomeMaterial, Categoria categoria, Almoxarifado almoxarifado, UnidadeMedida unidadeMedida) {
        this.id = id;
        this.nomeMaterial = nomeMaterial;
        this.categoria = categoria;
        this.almoxarifado = almoxarifado;
        this.unidadeMedida = unidadeMedida;
    }
    public Material() {}

    public Integer getId() {return id;}
    public void setId(Integer id) {this.id = id;}
    public Categoria getCategoria() {return categoria;}
    public void setCategoria(Categoria categoria) {this.categoria = categoria;}
    public Almoxarifado getEstoque() {return almoxarifado;}
    public void setEstoque(Almoxarifado estoque) {this.almoxarifado = estoque;}
    public UnidadeMedida getUnidadeMedida() {return unidadeMedida;}
    public void setUnidadeMedida(UnidadeMedida unidadeMedida) {this.unidadeMedida = unidadeMedida;}
    public String getNomeMaterial() {return nomeMaterial;}
    public void setNomeMaterial(String nomeMaterial) {this.nomeMaterial = nomeMaterial;}
    public Almoxarifado getAlmoxarifado() {return almoxarifado;}
    public void setAlmoxarifado(Almoxarifado almoxarifado) {this.almoxarifado = almoxarifado;}
}
