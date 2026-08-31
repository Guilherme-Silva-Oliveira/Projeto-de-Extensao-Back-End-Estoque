package school.sptech.sistema_estoque.adapter;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import school.sptech.sistema_estoque.model.estoque.*;
import school.sptech.sistema_estoque.port.SolicitacaoPort;
import school.sptech.sistema_estoque.repository.*;

import java.util.List;
import java.util.Optional;

@Component
public class SolicitacaoAdapter implements SolicitacaoPort {
    private final SolicitacaoRepository solicitacaoRepository;
    private final HistoricoRepository historicoRepository;
    private final StatusRepository statusRepository;
    private final AlertaDevolucaoRepository alertaDevolucaoRepository;
    private final ListaMaterialRepository listaMaterialRepository;
    private final AlertaSolicitacaoRepository alertaSolicitacaoRepository;

    public SolicitacaoAdapter(SolicitacaoRepository solicitacaoRepository, HistoricoRepository historicoRepository, StatusRepository statusRepository, AlertaDevolucaoRepository alertaDevolucaoRepository, ListaMaterialRepository listaMaterialRepository, AlertaSolicitacaoRepository alertaSolicitacaoRepository) {
        this.solicitacaoRepository = solicitacaoRepository;
        this.historicoRepository = historicoRepository;
        this.statusRepository = statusRepository;
        this.alertaDevolucaoRepository = alertaDevolucaoRepository;
        this.listaMaterialRepository = listaMaterialRepository;
        this.alertaSolicitacaoRepository = alertaSolicitacaoRepository;
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
    public Historico saveHistorico(Historico historico) {
        return historicoRepository.save(historico);
    }

    @Override
    public Optional<Status> findStatusById(Integer id) {
        return statusRepository.findById(id);
    }

    @Override
    public List<Optional<Historico>> findBySolicitacaoId(Integer id) {
        return historicoRepository.findBySolicitacaoId(id);
    }

    @Override
    public void salvarAlerta(AlertaDevolucao alerta) {
        alertaDevolucaoRepository.save(alerta);
    }

    @Override
    public void salvarLista(ListaMaterial lista) {
        listaMaterialRepository.save(lista);
    }

    @Override
    public List<Optional<AlertaDevolucao>> findAlertaBySolicitacaoId(Integer solicitacaoId) {
        return alertaDevolucaoRepository.findAllBySolicitacaoId(solicitacaoId);
    }

    @Override
    public List<Optional<ListaMaterial>> findListaBySolicitacaoId(Integer solicitacaoId) {
        return listaMaterialRepository.findAllBySolicitacaoId(solicitacaoId);
    }

    @Override
    public Optional<Solicitacao> findByProfessorId(Integer professorId) {
        return solicitacaoRepository.findByProfessorId(professorId);
    }

    @Override
    public void salvarAlertaSolicitacao(AlertaSolicitacao alertaSolicitacao) {
        alertaSolicitacaoRepository.save(alertaSolicitacao);
    }
}
