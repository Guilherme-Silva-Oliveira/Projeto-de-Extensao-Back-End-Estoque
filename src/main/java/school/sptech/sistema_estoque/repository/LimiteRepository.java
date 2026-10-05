package school.sptech.sistema_estoque.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.sistema_estoque.model.estoque.Limite;

import java.util.List;

public interface LimiteRepository extends JpaRepository<Limite, Integer> {
    List<Limite> findByTipoLimite_TipoIgnoreCase(String tipo);
}