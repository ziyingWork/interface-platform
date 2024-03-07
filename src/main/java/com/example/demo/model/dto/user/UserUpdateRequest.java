package com.example.demo.model.dto.user;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateRequest implements Serializable {

  
  private static final long serialVersionUID = 1L;
  /**
  * id
  */
  private Long id;

  /**
  * 用户昵称
  */
  private String userName;

  /**
  * 账号
  */
  private String userAccount;

  /**
  * 用户头像
  */
  private String userAvatar;

  /**
  * 性别
  */
  private Integer gender;

  /**
  * 用户角色: user, admin
  */
  private String userRole;

  /**
  * 密码
  */
  private String userPassword;

  
}
