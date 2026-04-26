package school.sptech.sistema_estoque.model.estoque;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Fornecedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;

    private String email;

    private String telefone;

    @ManyToOne
    @JoinColumn(name = "tipo_fornecedor_id")
    private TipoFornecedor tipoFornecedor;

    @OneToMany(mappedBy = "fornecedor", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<PedidoEntrada> pedidosEntrada;

    public Fornecedor() {
    }

    public Fornecedor(Integer id, String nome, String email, String telefone, TipoFornecedor tipoFornecedor) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.tipoFornecedor = tipoFornecedor;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public TipoFornecedor getTipoFornecedor() {
        return tipoFornecedor;
    }

    public void setTipoFornecedor(TipoFornecedor tipoFornecedor) {
        this.tipoFornecedor = tipoFornecedor;
    }

    public List<PedidoEntrada> getPedidosEntrada() {
        return pedidosEntrada;
    }

    public void setPedidosEntrada(List<PedidoEntrada> pedidosEntrada) {
        this.pedidosEntrada = pedidosEntrada;
    }
}
