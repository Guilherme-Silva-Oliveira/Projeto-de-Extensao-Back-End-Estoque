package school.sptech.sistema_estoque.model.estoque.compound_id;

import java.io.Serializable;
import java.util.Objects;

public class PedidoSaidaId implements Serializable {
    private Integer material;
    private Integer solicitacao;

    public PedidoSaidaId() {
    }

    public Integer getMaterial() {
        return material;
    }

    public void setMaterial(Integer material) {
        this.material = material;
    }

    public Integer getSolicitacao() {
        return solicitacao;
    }

    public void setSolicitacao(Integer solicitacao) {
        this.solicitacao = solicitacao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PedidoSaidaId that = (PedidoSaidaId) o;
        return Objects.equals(material, that.material) &&
               Objects.equals(solicitacao, that.solicitacao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(material, solicitacao);
    }
}
