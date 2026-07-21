package school.sptech.sistema_estoque.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.sistema_estoque.model.estoque.Solicitacao;

import java.util.Optional;

public interface SolicitacaoRepository extends JpaRepository<Solicitacao, Integer> {
    Optional<Solicitacao> findByProfessorId(Integer professorId);
}
