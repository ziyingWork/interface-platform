package com.example.demo.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @Description SecurityConfiguration
 * @Author ziying
 * @Date 2024/2/4 21:55
 **/

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SecurityConfiguration {

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    //authorizeHttpRequests:针对http请求进行授权配置
    // login登录请求 需要匿名访问
    // permitAll() 具有所有权限，即可匿名访问
    // anyRequest() 任何请求，即所有请求
    // authenticated() 认证[登录]
    http
      // 禁用basic明文验证
      .httpBasic(basic -> basic.disable())
      // 前后端分离架构不需要csrf保护
      .csrf(csrf -> csrf.disable())
      // 禁用默认登录页
      .formLogin(formLogin -> formLogin.disable())
      // 禁用默认登出页面
      .logout(logout -> logout.disable())
      // 前后端分离是无状态的，不需要session了，直接禁用
      .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

      .authorizeHttpRequests(authorizeHttpRequests -> 
          authorizeHttpRequests
            .requestMatchers("/**").permitAll() // 允许所有请求
            // .requestMatchers("/auth/login").permitAll()
            // .requestMatchers("/user/register").permitAll()
            .anyRequest()
            .authenticated()
          );

    return http.build();
  }







}
