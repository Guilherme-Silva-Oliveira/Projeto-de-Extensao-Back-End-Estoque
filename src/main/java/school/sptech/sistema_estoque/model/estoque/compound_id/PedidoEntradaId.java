package school.sptech.sistema_estoque.model.estoque.compound_id;

import java.io.Serializable;
import java.util.Objects;

public class PedidoEntradaId implements Serializable {
    private Integer fornecedor;
    private Integer material;

    public PedidoEntradaId() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PedidoEntradaId that = (PedidoEntradaId) o;
        return Objects.equals(fornecedor, that.fornecedor) &&
               Objects.equals(material, that.material);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fornecedor, material);
    }
}
