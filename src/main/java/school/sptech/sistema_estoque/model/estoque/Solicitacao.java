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
    private Boolean deveDevolver;
    private Integer inteligenciaArtificialId;
    @ManyToOne private Motivo motivo;
    private String descricao;
    private String materiais; // Armazena materiais como string (ex: "Caneta,Papel")
    private LocalDateTime dataSolicitacao;
    private LocalDateTime dataParaEnvio;
}
