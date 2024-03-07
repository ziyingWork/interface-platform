package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;


/**
 * @Description RedisTemplate 序列化配置
 * @Author ziying
 * @Date 2024/2/04 21:22
 **/
@Configuration
public class RedisConfiguration {

  @Bean
  public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
    RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());
        template.afterPropertiesSet();
        return template;
  }  
}

// 示例代码：使用RedisTemplate进行序列化和反序列化操作
// @Autowired
// private RedisTemplate<String, Object> redisTemplate;

// public void setObject(String key, Object value) {
//   redisTemplate.opsForValue().set(key, value);
// }

// public Object getObject(String key) {
//   return redisTemplate.opsForValue().get(key);
// }