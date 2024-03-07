package com.example.demo.service;

import com.example.demo.model.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户 服务类
 * </p>
 *
 * @author ziying
 * @since 2024-01-29
 */
public interface IUserService extends IService<User> {

  public User findUserByUsername(String username);

  public Long userRegister(String userAccount, String userPassword, String checkPassword);

  public Long userLogin(String userAccount, String password);

}
