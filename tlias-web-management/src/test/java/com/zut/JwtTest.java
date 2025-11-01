package com.zut;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTest {
    @Test
    public void  testGenJwt(){
        Map<String,Object> claims = new HashMap<>();
        claims.put("id",1);
        claims.put("username","admin");
       String jwt = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256,"5Yqh5Lqn5ZWK5b+D5KeG5rW35Y2V")//签名算法
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*60*24))//设置过期时间
                .addClaims(claims)//添加自定义信息
                .compact();//生成JWT令牌
        System.out.println(jwt);
    }


//解析JWT令牌
    @Test
    public void  testParseJwt(){
       Claims claims =  Jwts.parser()
                .setSigningKey("5Yqh5Lqn5ZWK5b+D5KeG5rW35Y2V")
                .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJleHAiOjE3NjIwNDk0NTMsImlkIjoxLCJ1c2VybmFtZSI6ImFkbWluIn0.yJPtKw5rmAtQMc_ChdFL8I-l4XuFm6ffjNfrjSmC9og")
                .getBody(); //获取自定义信息
        System.out.println(claims);
    }
}
