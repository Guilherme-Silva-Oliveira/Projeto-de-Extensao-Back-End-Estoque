package school.sptech.sistema_estoque.model.estoque;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "status_historico")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Status {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String descStatus;
}
