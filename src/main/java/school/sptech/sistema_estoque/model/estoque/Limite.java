package school.sptech.sistema_estoque.model.estoque;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class Limite {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String descLimite;
    private Double limite;

    @ManyToOne @JoinColumn(name = "material_id")
    private Material material;
}