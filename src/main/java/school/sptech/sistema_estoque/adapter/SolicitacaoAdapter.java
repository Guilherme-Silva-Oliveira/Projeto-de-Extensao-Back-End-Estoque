package school.sptech.sistema_estoque.adapter;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import school.sptech.sistema_estoque.model.estoque.Historico;
import school.sptech.sistema_estoque.model.estoque.Solicitacao;
import school.sptech.sistema_estoque.model.estoque.Status;
import school.sptech.sistema_estoque.port.SolicitacaoPort;
import school.sptech.sistema_estoque.repository.HistoricoRepository;
import school.sptech.sistema_estoque.repository.SolicitacaoRepository;
import school.sptech.sistema_estoque.repository.StatusRepository;

import java.util.List;
import java.util.Optional;

@Component
public class SolicitacaoAdapter implements SolicitacaoPort {
    private final SolicitacaoRepository solicitacaoRepository;
    private final HistoricoRepository historicoRepository;
    private final StatusRepository statusRepository;

    public SolicitacaoAdapter(SolicitacaoRepository solicitacaoRepository, HistoricoRepository historicoRepository, StatusRepository statusRepository) {
        this.solicitacaoRepository = solicitacaoRepository;
        this.historicoRepository = historicoRepository;
        this.statusRepository = statusRepository;
    }

    @Override
    public Solicitacao save(Solicitacao solicitacao) {
        return solicitacaoRepository.save(solicitacao);
    }

    @Override
    public List<Solicitacao> findAll() {
        return solicitacaoRepository.findAll();
    }

    @Override
    public Optional<Solicitacao> findById(Integer id) {
        return solicitacaoRepository.findById(id);
    }

    @Override
    public void delete(Solicitacao solicitacao) {
        solicitacaoRepository.delete(solicitacao);
    }

    @Override
    public Page<Solicitacao> findByIsAceito(Boolean aceito, Pageable pageable) {
        return solicitacaoRepository.findByIsAceito(aceito, pageable);
    }

    @Override
    public Historico saveHistorico(Historico historico) {
        return historicoRepository.save(historico);
    }

    @Override
    public List<Optional<Historico>> findHistoricoById(Integer id) {
        return List.of(historicoRepository.findById(id));
    }

    @Override
    public Optional<Status> findStatusById(Integer id) {
        return statusRepository.findById(id);
    }
}
