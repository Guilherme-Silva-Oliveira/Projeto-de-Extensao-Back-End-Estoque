package school.sptech.sistema_estoque.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import school.sptech.sistema_estoque.dto.estoque.almoxarife.AlmoxarifeDetalhes;
import school.sptech.sistema_estoque.model.estoque.Almoxarife;
import school.sptech.sistema_estoque.port.AlmoxarifePort;

@Service
public class AutenticacaoService implements UserDetailsService {
    @Autowired
    AlmoxarifePort almoxarifePort;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Almoxarife almoxarife = almoxarifePort.findByEmail(username)
            .orElseThrow(() -> new UsernameNotFoundException(String.format("Usuário: %s não encontrado", username)));
        return new AlmoxarifeDetalhes(
                almoxarife.getNome(),
                almoxarife.getEmail(), 
                almoxarife.getSenha(),
                almoxarife.getRole());
    }
}
