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
public class Material {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nomeMaterial;
    private String descricao;
    @ManyToOne private Categoria categoria;
    @ManyToOne private Almoxarifado almoxarifado;
    @ManyToOne private UnidadeMedida unidadeMedida;
    private Integer quantidade;
    @OneToMany(mappedBy = "material", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Limite> limites;
    @OneToMany(mappedBy = "material", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<PedidoEntrada> pedidosEntrada;
    @OneToMany(mappedBy = "material", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<PedidoSaida> pedidosSaida;

    @OneToMany(mappedBy = "material", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<CodigoBarras> codigosBarras;
}
