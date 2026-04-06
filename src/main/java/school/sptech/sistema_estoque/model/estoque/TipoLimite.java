package school.sptech.sistema_estoque.model.estoque;

import jakarta.persistence.*;

@Entity
@Table(name = "tipo_limite")
public class TipoLimite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String tipo;

    public TipoLimite(Integer id, String tipo) {
        this.id = id;
        this.tipo = tipo;
    }

    public TipoLimite() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
