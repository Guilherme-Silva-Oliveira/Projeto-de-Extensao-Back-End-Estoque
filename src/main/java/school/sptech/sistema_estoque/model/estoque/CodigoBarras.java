package school.sptech.sistema_estoque.model.estoque;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class CodigoBarras {
    @Id private String codigo;
    @ManyToOne @JoinColumn(name = "material_id")
    private Material material;
}
