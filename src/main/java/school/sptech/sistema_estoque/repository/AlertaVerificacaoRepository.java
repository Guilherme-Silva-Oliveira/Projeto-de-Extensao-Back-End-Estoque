package school.sptech.sistema_estoque.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.sistema_estoque.dto.mq.AlertaVerificacao;
import school.sptech.sistema_estoque.model.estoque.AlertaDevolucao;

import java.util.List;
import java.util.Optional;

public interface AlertaVerificacaoRepository extends JpaRepository<AlertaVerificacao, Integer> {
}
