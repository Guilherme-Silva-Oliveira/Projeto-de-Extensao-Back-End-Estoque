package school.sptech.sistema_estoque.model.estoque;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "codigo_barras")
public class CodigoBarras {

    @Id
    private String codigo;

    @ManyToOne
    @JoinColumn(name = "fk_material")
    private Material material;

    public CodigoBarras() {
    }

    public CodigoBarras(String codigo, Material material) {
        this.codigo = codigo;
        this.material = material;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }
}
