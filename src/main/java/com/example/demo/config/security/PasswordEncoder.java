package com.example.demo.config.security;

public interface PasswordEncoder {

  String encode(CharSequence rawPassword);
	// 实际上我们自己也可以写匹配规则只需实现PasswordEncoder接口
	// 重写matches()抽象方法
	// 注册到spring容器中即可
	// 我们公司就是自己定义的匹配规则
  boolean matches(CharSequence rawPassword, String encodedPassword);

  default boolean upgradeEncoding(String encodedPassword) {
    return false;
  }
  
}
