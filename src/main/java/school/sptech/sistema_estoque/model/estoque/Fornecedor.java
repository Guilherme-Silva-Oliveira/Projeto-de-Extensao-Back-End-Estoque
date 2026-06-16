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
public class Fornecedor {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String email;
    private String telefone;

    @ManyToOne @JoinColumn(name = "tipo_fornecedor_id")
    private TipoFornecedor tipoFornecedor;
    @OneToMany(mappedBy = "fornecedor", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<PedidoEntrada> pedidosEntrada;
}
