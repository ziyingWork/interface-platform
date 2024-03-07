package com.example.demo.mapper;

import com.example.demo.model.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 用户 Mapper 接口
 * </p>
 *
 * @author ziying
 * @since 2024-01-29
 */
public interface UserMapper extends BaseMapper<User> {

  @Select("SELECT * FROM user WHERE userName = #{username}")
  User selectByUsername(@Param("username") String username);

  @Select("SELECT * FROM user WHERE userAccount = #{userAccount}")
  User selectByAccount(@Param("userAccount") String userAccount);

  
  
}
