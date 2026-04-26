package school.sptech.sistema_estoque.model.estoque;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Solicitacao {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne private Professor professor;
    private String descricao;
    private LocalDateTime dataSolicitacao;

    @OneToMany(mappedBy = "solicitacao", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<PedidoSaida> pedidosSaida;

    public Solicitacao(Integer id, Professor professor, String descricao, LocalDateTime dataSolicitacao, List<PedidoSaida> pedidosSaida) {
        this.id = id;
        this.professor = professor;
        this.descricao = descricao;
        this.dataSolicitacao = dataSolicitacao;
        this.pedidosSaida = pedidosSaida;
    }

    public Solicitacao() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getDataSolicitacao() {
        return dataSolicitacao;
    }

    public void setDataSolicitacao(LocalDateTime dataSolicitacao) {
        this.dataSolicitacao = dataSolicitacao;
    }

    public List<PedidoSaida> getPedidosSaida() {
        return pedidosSaida;
    }

    public void setPedidosSaida(List<PedidoSaida> pedidosSaida) {
        this.pedidosSaida = pedidosSaida;
    }
}
