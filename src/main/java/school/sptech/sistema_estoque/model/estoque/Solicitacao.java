package school.sptech.sistema_estoque.model.estoque;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class Solicitacao {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne private Professor professor;
    @ManyToOne private Motivo motivo;
    private String descricao;
    private LocalDateTime dataSolicitacao;

    @OneToMany(mappedBy = "solicitacao", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<PedidoSaida> pedidosSaida;
    @Column(name = "is_aceito")
    private Boolean isAceito;
}
