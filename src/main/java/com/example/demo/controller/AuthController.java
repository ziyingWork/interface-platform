package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.common.BaseResponse;
import com.example.demo.common.ErrorCode;
import com.example.demo.common.ResultUtils;
import com.example.demo.exception.BusinessException;
import com.example.demo.service.AuthService;

import jakarta.annotation.Resource;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


/**
 * @Description 认证API
 * @Author ziying
 * @Date 2024/2/4 23:02
 **/

@RestController
@RequestMapping("/auth")
public class AuthController {

  @Resource
  private AuthService authService;

  @PostMapping("/login")
  public BaseResponse<Long> postMethodName(@RequestBody JSONObject params) {
      // 对于用户名、密码等敏感参数一律使用POST请求
      String userAccount = params.getString("userAccount");
      String password = params.getString("password");
      if (!StringUtils.hasText(userAccount) || !StringUtils.hasText(password)) {
        throw new BusinessException(ErrorCode.PARAMS_ERROR, "用户或密码为空");
      } 
      // token 待实现
      Long userId = authService.login(userAccount, password);
      return ResultUtils.success(userId);
  }
  
 
}
