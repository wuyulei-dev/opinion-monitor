package com.tkww.demo.util;

import java.util.Date;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Value;
import com.tkww.demo.config.properties.JwtProperties;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * token工具类
 * 
 * @author wwp-pc
 */
public class JwtUtils {

    private static String SECRETY_KEY;
    //过期时间：单位ms
    private static long EXPIRED_TIME = 30 * 60 * 1000;

    //初始化变量值
    public static void init() {
        JwtProperties jwtProperties = SpringBootUtil.getBean(JwtProperties.class);
        SECRETY_KEY = jwtProperties.getSecretyKey();
    }

    /**
     * 生成token
     * 
     * @return
     */
    public static String getToken(
        String userId) {
        if (StrUtil.isEmpty(SECRETY_KEY)) {
            init();
        }

        JwtBuilder jwtBuilder = Jwts.builder()
                /**
                 * header
                 */
                .setHeaderParam("typ", "JWT").setHeaderParam("alg", "HS256")
                /**
                 * payload
                 */
                .setId(UUID.randomUUID().toString()) //{"jti":""}  jwtid:用于标识该jwt
                .setSubject("JJWT") //{"sub":""}  主题
                .setIssuedAt(new Date()) //{"iat":""}  发布时间
                //当时间过期时，解析token会抛出ExpiredJwtException 异常
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRED_TIME)) //{"exp":""} 过期时间
                .claim("userId", userId) //自定义参数
                /**
                 * signature
                 */
                .signWith(SignatureAlgorithm.HS256, SECRETY_KEY);

        String token = jwtBuilder.compact();
        return token;
    }

    /**
     * Token解析
     * 
     * @param token
     * @return
     */
    public static Claims parseToken(
        String token) {
        if (StrUtil.isEmpty(SECRETY_KEY)) {
            init();
        }
        if (StrUtil.isEmpty(token))
            return null;
        try {
            JwtParser parser = Jwts.parser();
            Jws<Claims> claims = parser.setSigningKey(SECRETY_KEY).parseClaimsJws(token);
            Claims body = claims.getBody();
            return body;
        }
        catch (Exception e) {
            //当时间过期时，解析token会抛出ExpiredJwtException 异常
            System.out.println(e.toString());
            return null;
        }
    }
}
