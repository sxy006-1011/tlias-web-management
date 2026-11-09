package com.itheima.utils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.util.Map;

/**
 * JWT工具类
 */
public class JwtUtils {
    // 密钥（和测试类保持一致）
    private static final String signKey = "SVRIRUlNQQ==";
    // 过期时间：12小时，单位毫秒 12*60*60*1000 = 43200000
    private static final Long expire = 43200000L;

    /**
     * 生成JWT令牌
     * @param claims 载荷数据
     * @return jwt字符串
     */
    public static String generateJwt(Map<String,Object> claims){
        String jwt = Jwts.builder()
                .addClaims(claims)
                .signWith(SignatureAlgorithm.HS256, signKey)
                .setExpiration(new Date(System.currentTimeMillis() + expire))
                .compact();
        return jwt;
    }

    /**
     * 解析JWT令牌
     * @param jwt JWT令牌
     * @return 载荷Claims
     */
    public static Claims parseJWT(String jwt){
        Claims claims = Jwts.parser()
                .setSigningKey(signKey)
                .parseClaimsJws(jwt)
                .getBody();
        return claims;
    }
}