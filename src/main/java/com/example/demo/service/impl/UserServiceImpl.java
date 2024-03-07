package com.example.demo.service.impl;

import com.example.demo.model.entity.User;
import com.example.demo.common.ErrorCode;
import com.example.demo.exception.BusinessException;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.IUserService;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.digest.DigestUtil;
import jakarta.annotation.Resource;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;


/**
 * <p>
 * 用户 服务实现类
 * </p>
 *
 * @author ziying
 * @since 2024-01-29
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

  @Resource
  private UserMapper userMapper;

  /**
  * 盐值，混淆密码
  */
  private static final String SALT = "ziying";

  @Override
  public User findUserByUsername(String username) {
    if (username == "") {
      throw new BusinessException(ErrorCode.PARAMS_ERROR);
    }
    return userMapper.selectByUsername(username);
  }

  @Override
  public Long userRegister(String userAccount, String userPassword, String checkPassword) {
    // 1. 校验
    if (StringUtils.isAnyBlank(userAccount, userPassword, checkPassword)) {
      throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数为空");
    }
    if (userAccount.length() < 4) {
      throw new BusinessException(ErrorCode.PARAMS_ERROR, "用户账号过短");
    }
    if (userPassword.length() < 8 || checkPassword.length() < 8) {
      throw new BusinessException(ErrorCode.PARAMS_ERROR, "用户密码过短");
    }
    // 密码和校验密码相同
    if (!userPassword.equals(checkPassword)) {
      throw new BusinessException(ErrorCode.PARAMS_ERROR, "两次输入的密码不一致");
    }
    synchronized (userAccount.intern()) {
      QueryWrapper<User> queryWrapper = new QueryWrapper<>();
      queryWrapper.eq("userAccount", userAccount);
      long count = userMapper.selectCount(queryWrapper);
      if (count > 0) {
        throw new BusinessException(ErrorCode.PARAMS_ERROR, "账号重复");
      }
      // 2. 加密
      String encryptPassword = DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes());
      // 3. 分配 accessKey, secretKey
      String accessKey = DigestUtil.md5Hex(SALT + userAccount + RandomUtil.randomNumbers(5));
      String secretKey = DigestUtil.md5Hex(SALT + userAccount + RandomUtil.randomNumbers(8));
      // 4. 插入数据
      User user = new User();
      user.setUserAccount(userAccount);
      user.setUserPassword(encryptPassword);
      user.setAccessKey(accessKey);
      user.setSecretKey(secretKey);
      int result = userMapper.insert(user);
      if (result == 1) {
        return user.getId();
      }else{
        throw new BusinessException(ErrorCode.SYSTEM_ERROR, "注册失败，请联系管理员");
      }
    }
  }

  @Override
  public Long userLogin(String userAccount, String password) {
    if (userAccount == "" || password == "") {
      throw new BusinessException(ErrorCode.PARAMS_ERROR,"账户或密码参数为空");
    }
    User queryUser = userMapper.selectByAccount(userAccount);
    if (queryUser == null) {
      throw new BusinessException(ErrorCode.NOT_FOUND_ERROR,"账户不存在");
    }else{
      String encryptPassword = DigestUtils.md5DigestAsHex((SALT + password).getBytes());
      if(encryptPassword.equals(queryUser.getUserPassword())){
        return queryUser.getId();
      }else {
        throw new BusinessException(ErrorCode.SYSTEM_ERROR, "登录失败，请重新登录");
      }
    }
  }


  








}
