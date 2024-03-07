package com.example.demo.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.model.entity.SecurityUser;
import com.example.demo.model.entity.User;
import com.example.demo.service.IUserService;

import jakarta.annotation.Resource;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  @Resource
  private IUserService userService;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userService.findUserByUsername(username);
    if (null == user) {
      throw new RuntimeException(String.format("not found [%s]", username));
    }
    return new SecurityUser(user);
  }
}
