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

    public Almoxarifado() {
    }

    public Almoxarifado(Integer id, Integer numeroSala) {
        this.id = id;
        this.numeroSala = numeroSala;
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

}
