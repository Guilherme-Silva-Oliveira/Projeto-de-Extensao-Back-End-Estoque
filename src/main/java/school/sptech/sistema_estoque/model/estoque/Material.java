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
    @ManyToOne private Categoria categoria;
    @ManyToOne private Estoque estoque;
    @ManyToOne private UnidadeMedida unidadeMedida;

    public Material(Integer id, Categoria categoria, Estoque estoque, UnidadeMedida unidadeMedida) {
        this.id = id;
        this.categoria = categoria;
        this.estoque = estoque;
        this.unidadeMedida = unidadeMedida;
    }
    public Material() {}

    public Integer getId() {return id;}
    public void setId(Integer id) {this.id = id;}
    public Categoria getCategoria() {return categoria;}
    public void setCategoria(Categoria categoria) {this.categoria = categoria;}
    public Estoque getEstoque() {return estoque;}
    public void setEstoque(Estoque estoque) {this.estoque = estoque;}
    public UnidadeMedida getUnidadeMedida() {return unidadeMedida;}
    public void setUnidadeMedida(UnidadeMedida unidadeMedida) {this.unidadeMedida = unidadeMedida;}
}
