package com.example.demo.model.entity;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description
 * @Author ziying
 * @Date 2024/2/3 22:49
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SecurityUser implements UserDetails{

  // 这是我们扩展的用户信息
  private User user;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return null;
  }

  @Override
  public String getPassword() {
    return user.getUserPassword();
  }

  @Override
  public String getUsername() {
    return user.getUserName();
  }

  @Override
  public boolean isAccountNonExpired() {
    // false 用户帐号已过期
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    // false 用户帐号已被锁定
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    // false 用户凭证已过期
    return true;
  }

  @Override
  public boolean isEnabled() {
    // false 用户已失效
    return true;
  }
  
}
