package school.sptech.sistema_estoque.model.estoque;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class Almoxarifado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "numero_sala")
    private Integer numeroSala;

    @OneToMany(mappedBy = "almoxarifado",cascade = CascadeType.REMOVE,orphanRemoval = true)
    private List<Material> materiais;

    @OneToMany(mappedBy = "almoxarifado", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Almoxarife> almoxarifes;
}
