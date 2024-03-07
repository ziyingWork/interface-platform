package com.example.demo.model.entity;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class Token {
  /**
   * token
   */
  private String token;
  /**
   * 有效时间：单位：秒
   */
  private Long expire;
  /**
   * 过期时间
   */
  private LocalDateTime expiration;

  public Token(String token, Long expire, LocalDateTime expiration) {
      this.token = token;
      this.expire = expire;
      this.expiration = expiration;
  }

}

