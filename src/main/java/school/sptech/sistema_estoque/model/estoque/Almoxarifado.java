package school.sptech.sistema_estoque.model.estoque;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "almoxarifado")
public class Almoxarifado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "numero_sala")
    private Integer numeroSala;

    @OneToMany
    @JoinColumn(name = "almoxarifado_id")
    private List<Limite> limites;

    public Almoxarifado() {
    }

    public Almoxarifado(Integer id, Integer numeroSala, List<Limite> limites) {
        this.id = id;
        this.numeroSala = numeroSala;
        this.limites = limites;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumeroSala() {
        return numeroSala;
    }

    public void setNumeroSala(Integer numeroSala) {
        this.numeroSala = numeroSala;
    }

    public List<Limite> getLimites() {
        return limites;
    }

    public void setLimites(List<Limite> limites) {
        this.limites = limites;
    }
}
