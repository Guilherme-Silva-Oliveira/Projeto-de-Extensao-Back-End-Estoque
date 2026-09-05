package school.sptech.sistema_estoque.dto.estoque.almoxarife;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import school.sptech.sistema_estoque.enums.Role;

public class AlmoxarifeDetalhes implements UserDetails {

  private final String nome;
  private final String email;
  private final String senha;
  private final Role role;

  public AlmoxarifeDetalhes(String nome, String email, String senha, Role role) {
    this.nome = nome;
    this.email = email;
    this.senha = senha;
      this.role = role;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
      String autoridade = (this.role != null) ? this.role.getRoleName() : Role.ALMOXARIFE.getRoleName();
      return List.of(new SimpleGrantedAuthority(autoridade));
  }

  public Role getRole() {
    return role;
  }

  public String getNome() {
    return nome;
  }

  @Override
  public String getPassword() {
      return this.senha;
  }

  @Override
  public String getUsername() {
      return this.email;
  }

  @Override
  public boolean isAccountNonExpired() {
      return true;
  }

  @Override
  public boolean isAccountNonLocked() {
      return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
      return true;
  }

  @Override
  public boolean isEnabled() {
      return true;
  }
}