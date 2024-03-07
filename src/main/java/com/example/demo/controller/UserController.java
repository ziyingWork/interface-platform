package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.common.BaseResponse;
import com.example.demo.common.ErrorCode;
import com.example.demo.common.ResultUtils;
import com.example.demo.exception.BusinessException;
import com.example.demo.model.dto.user.UserQueryRequest;
import com.example.demo.model.dto.user.UserRegisterRequest;
import com.example.demo.model.entity.User;
import com.example.demo.service.IUserService;

import jakarta.annotation.Resource;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



/**
 * <p>
 * 用户 前端控制器
 * </p>
 *
 * @author ziying
 * @since 2024-01-29
 */
@RestController
@RequestMapping("/user")
public class UserController {

  @Resource
  private IUserService iUserService;


  @GetMapping("/getUserByName")
  public BaseResponse<UserQueryRequest> getUserByName(@RequestParam(name = "userName") String userName) throws IllegalAccessException, InvocationTargetException {
    if (userName == "") {
      throw new BusinessException(ErrorCode.PARAMS_ERROR);
    }
    User user = iUserService.findUserByUsername(userName);
    System.out.println(user);
    UserQueryRequest userQueryRequest = new UserQueryRequest();
    BeanUtils.copyProperties(userQueryRequest, user);
    return ResultUtils.success(userQueryRequest);
  }

  @PostMapping("/register")
  public BaseResponse<Long> userRegister(@RequestBody UserRegisterRequest userRegisterRequest) {
    if (userRegisterRequest == null){
      throw new BusinessException(ErrorCode.PARAMS_ERROR);
    }
    String userAccount = userRegisterRequest.getUserAccount();
    String userPassword = userRegisterRequest.getUserPassword();
    String checkPassword = userRegisterRequest.getCheckPassword();
    if (StringUtils.isAnyBlank(userAccount, userPassword, checkPassword)) {
      throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数缺失");
    }
    Long result = iUserService.userRegister(userAccount, userPassword, checkPassword);
    return ResultUtils.success(result);
  }

  @PostMapping("/login")
  public BaseResponse<Long> postMethodName(@RequestBody UserQueryRequest userQueryRequest) {
    

    return null;
  }
  


}
