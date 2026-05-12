package school.sptech.sistema_estoque.model.estoque;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nomeCategoria;

    @OneToMany(mappedBy = "categoria",cascade = CascadeType.REMOVE,orphanRemoval = true)
    private List<Material> materiais;

    public Categoria(Integer id, String nomeCategoria) {
        this.id = id;
        this.nomeCategoria = nomeCategoria;
    }
    public Categoria() {}

    public Integer getId() {return id;}
    public void setId(Integer id) {this.id = id;}
    public String getNomeCategoria() {return nomeCategoria;}
    public void setNomeCategoria(String nomeCategoria) {this.nomeCategoria = nomeCategoria;}

    public List<Material> getMateriais() {
        return materiais;
    }

    public void setMateriais(List<Material> materiais) {
        this.materiais = materiais;
    }
}
