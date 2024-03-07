package com.example.demo.common;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Base64;
import java.util.Date;
import java.util.Map;
import java.security.Key;

import javax.crypto.spec.SecretKeySpec;

import com.example.demo.model.entity.Token;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.SecurityException;
// import io.jsonwebtoken.SignatureException;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class JwtUtils {
    /**
     * JWT token 签名
     * 签名密钥长度至少32位
     */
    private static final String JWT_SIGN_KEY = "showmebug_jwt_token_json_sign_key";

    private static final String BASE64_SECURITY = Base64.getEncoder().encodeToString(JWT_SIGN_KEY.getBytes(StandardCharsets.UTF_8));

    /**
     * @Description: 创建令牌
     */
    public static Token createJwt(Map<String, String> user, long expire) {
      // 选择签名算法
      SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
      // 获取当前时间
      long nowMillis = System.currentTimeMillis();
      Date now = new Date(nowMillis);
      // 生成签名密钥
      byte[] apiKeySecretBytes = Base64.getDecoder().decode(BASE64_SECURITY);
      Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

      // 创建一个 JWT 构建器对象 builder，并设置 JWT 的头部参数（Header Parameter）。
      JwtBuilder builder = Jwts.builder().setHeaderParam("typ", "JsonWebToken");

      // builder::claim 将会对每个用户信息的键值对调用 claim 方法，将其添加到 JWT 的载荷中。
      user.forEach(builder::claim);

      // 设置 JWT 的有效期
      long expMillis = nowMillis + expire * 1000;
      Date exp = new Date(expMillis);
      builder.setIssuedAt(now).setNotBefore(now).setExpiration(exp).signWith(signatureAlgorithm, signingKey);

      // 组装 JWT 信息
      Token tokenInfo = new Token();
      tokenInfo.setToken(builder.compact());
      tokenInfo.setExpire(expire);
      tokenInfo.setExpiration(localDateTime(exp));
      return tokenInfo;
    }


    /**
     * Date转换为LocalDateTime
     *
     * @param date 日期
     */
    public static LocalDateTime localDateTime(Date date) {
        if (date == null) {
            return LocalDateTime.now();
        }
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        return instant.atZone(zoneId).toLocalDateTime();
    }

    /**
     * @Description: 获取Claims
     */
    public static Claims getClaims(String token, long allowedClockSkewSeconds) {
        if (StrUtil.isEmpty(token)) {
            return null;
        }

        return parseJwt(token, allowedClockSkewSeconds);
    }

    /**
     * @Description: 解析 JWT
     */
    public static Claims parseJwt(String jsonWebToken, long allowedClockSkewSeconds) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(Base64.getDecoder().decode(BASE64_SECURITY))
                    .setAllowedClockSkewSeconds(allowedClockSkewSeconds)
                    .build()
                    .parseClaimsJws(jsonWebToken)
                    .getBody();
        } catch (ExpiredJwtException ex) {
            log.error("token过期", ex);
            //过期
            //抛异常 让系统捕获到返回到前端
        } catch (SecurityException ex) {
            log.error("签名错误", ex);
            //签名错误
            //抛异常 让系统捕获到返回到前端
        } catch (IllegalArgumentException ex) {
            log.error("token为空", ex);
            //token 为空
            //抛异常 让系统捕获到返回到前端
        } catch (Exception e) {
            log.error("解析token异常", e);
            //抛异常 让系统捕获到返回到前端
        }
        return null;
    }

    // 示例
    public static void main(String[] args) {
        Map<String, String> param = MapUtil.newHashMap();
        param.put("userId", "123455");
        param.put("userName", "leixiaohong");

        Token token = JwtUtils.createJwt(param, 100000L);

        System.out.println("token：" + token.getToken());
        System.out.println("token的有效期：" + token.getExpire());
        System.out.println("token过期时间：" + token.getExpiration());


        Claims claims = JwtUtils.getClaims(token.getToken(), 60L);


        System.out.println("从token中获取的userId：" +  Convert.toStr(claims.get("userId")));
        System.out.println("从token中获取userName：" +  Convert.toStr(claims.get("userName")));
    }


  
}
