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
    private String limite;

    @ManyToOne @JoinColumn(name = "tipo_limite_id")
    private TipoLimite tipoLimite;
    @ManyToOne @JoinColumn(name = "material_id")
    private Material material;
}