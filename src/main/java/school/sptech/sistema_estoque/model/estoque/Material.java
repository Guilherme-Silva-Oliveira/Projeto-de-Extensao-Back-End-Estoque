package school.sptech.sistema_estoque.model.estoque;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import org.springframework.data.annotation.Id;

public class Material {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne private Categoria categoria;

    public Material(Integer id, Categoria categoria) {
        this.id = id;
        this.categoria = categoria;
    }
    public Material() {}

    public Integer getId() {return id;}
    public void setId(Integer id) {this.id = id;}
    public Categoria getCategoria() {return categoria;}
    public void setCategoria(Categoria categoria) {this.categoria = categoria;}
}
