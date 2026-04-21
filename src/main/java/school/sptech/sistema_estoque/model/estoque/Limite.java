package school.sptech.sistema_estoque.model.estoque;

import jakarta.persistence.*;

@Entity
@Table(name = "limite")
public class Limite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String limite;

    @ManyToOne
    @JoinColumn(name = "tipo_limite_id")
    private TipoLimite tipoLimite;

    @ManyToOne
    private Material material;

    public Limite() {
    }

    public Limite(Integer id, String limite, TipoLimite tipoLimite, Material material) {
        this.id = id;
        this.limite = limite;
        this.tipoLimite = tipoLimite;
        this.material = material;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLimite() {
        return limite;
    }

    public void setLimite(String limite) {
        this.limite = limite;
    }

    public TipoLimite getTipoLimite() {
        return tipoLimite;
    }

    public void setTipoLimite(TipoLimite tipoLimite) {
        this.tipoLimite = tipoLimite;
    }
}