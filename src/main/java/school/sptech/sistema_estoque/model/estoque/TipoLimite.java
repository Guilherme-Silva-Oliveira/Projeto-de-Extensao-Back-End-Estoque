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
public class TipoLimite {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String tipo;
}
