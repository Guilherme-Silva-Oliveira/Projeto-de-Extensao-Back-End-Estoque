package school.sptech.sistema_estoque.dto.estoque.almoxarife;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class AlmoxarifeDetalhesDto implements UserDetails {

  private final String nome;

  private final String email;

  private final String senha;


  public AlmoxarifeDetalhesDto(String nome, String email, String senha) {
    this.nome = nome;
    this.email = email;
    this.senha = senha;
  }


  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
      // TODO Auto-generated method stub
      return null;
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
