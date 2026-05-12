package school.sptech.sistema_estoque.adapter;

import org.springframework.stereotype.Component;
import school.sptech.sistema_estoque.model.estoque.Solicitacao;
import school.sptech.sistema_estoque.port.SolicitacaoPort;
import school.sptech.sistema_estoque.repository.SolicitacaoRepository;

import java.util.List;
import java.util.Optional;

@Component
public class SolicitacaoAdapter implements SolicitacaoPort {
    private final SolicitacaoRepository solicitacaoRepository;

    public SolicitacaoAdapter(SolicitacaoRepository solicitacaoRepository) {
        this.solicitacaoRepository = solicitacaoRepository;
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
}
