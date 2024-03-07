package com.example.demo.model.dto.user;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginRequest implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
  * 账号
  */
  private String userAccount;

  /**
  * 密码
  */
  private String userPassword;

}
