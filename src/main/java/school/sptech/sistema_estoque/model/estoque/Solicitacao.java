package school.sptech.sistema_estoque.model.estoque;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Solicitacao {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne private Professor professor;
    private String escala;
    private String descricao;
    private LocalDateTime dataSolicitacao;

    public Solicitacao(Integer id, Professor professor, String escala, String descricao, LocalDateTime dataSolicitacao) {
        this.id = id;
        this.professor = professor;
        this.escala = escala;
        this.descricao = descricao;
        this.dataSolicitacao = dataSolicitacao;
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

    public String getEscala() {
        return escala;
    }

    public void setEscala(String escala) {
        this.escala = escala;
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
}
