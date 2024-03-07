package com.example.demo.model.dto.user;
import java.io.Serializable;
import lombok.Data;

@Data
public class UserRegisterRequest implements Serializable  {

  private static final long serialVersionUID = 1L;

  private String userAccount;

  private String userPassword;

  private String checkPassword;

}
