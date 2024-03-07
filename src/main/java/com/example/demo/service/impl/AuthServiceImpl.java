package com.example.demo.service.impl;

import org.springframework.stereotype.Service;
import com.example.demo.exception.BusinessException;
import com.example.demo.service.AuthService;
import com.example.demo.service.IUserService;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AuthServiceImpl implements AuthService {

  @Resource
  private IUserService iUserService;

  @Override
  public Long login(String userAccount, String password) {
      Long userId = null;
      try {
          // 调用iUserService中的userLogin方法，并传入用户账户和密码
          userId = iUserService.userLogin(userAccount, password);
      } catch (BusinessException e) {
          // 这里处理任何BusinessException异常，例如打印日志或转换异常信息
          log.error("业务异常: {}", e.getMessage());
      }
      return userId;
  }


  
}
