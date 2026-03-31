package school.sptech.sistema_estoque.model.estoque;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

public class SaidaHistorico {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDateTime dataSaida;
    private String nomeSolicitante;
    private String escala;
    private Integer quantidade;

    public SaidaHistorico(Integer id, LocalDateTime dataSaida, String nomeSolicitante, String escala, Integer quantidade) {
        this.id = id;
        this.dataSaida = dataSaida;
        this.nomeSolicitante = nomeSolicitante;
        this.escala = escala;
        this.quantidade = quantidade;
    }
    public SaidaHistorico() {}

    public Integer getId() {return id;}
    public void setId(Integer id) {this.id = id;}
    public LocalDateTime getDataSaida() {return dataSaida;}
    public void setDataSaida(LocalDateTime dataSaida) {this.dataSaida = dataSaida;}
    public String getNomeSolicitante() {return nomeSolicitante;}
    public void setNomeSolicitante(String nomeSolicitante) {this.nomeSolicitante = nomeSolicitante;}
    public String getEscala() {return escala;}
    public void setEscala(String escala) {this.escala = escala;}
    public Integer getQuantidade() {return quantidade;}
    public void setQuantidade(Integer quantidade) {this.quantidade = quantidade;}
}
