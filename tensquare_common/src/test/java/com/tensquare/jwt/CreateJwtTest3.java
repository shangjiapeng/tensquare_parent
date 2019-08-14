package com.tensquare.jwt;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

/**
 * <p>自定义claims生成token</p>
 * 如果想存储更多的信息可以定义自定义claims
 * @Author: ShangJiaPeng
 * @Since: 2019-08-13 12:04
 */
public class CreateJwtTest3 {
    //为了测试方便,将过期时间设置为1分钟
    public static void main(String[] args) {
        long currentTimeMillis = System.currentTimeMillis();//当前时间毫秒值
        long exp =currentTimeMillis+1000*60;//过期时间设置为1分钟
        JwtBuilder jwtBuilder = Jwts.builder()
                .setId("2")
                .setSubject("shang")
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, "shang")
                .setExpiration(new Date(exp))
                .claim("roles","admin")
                .claim("sex","男");
        System.out.println(jwtBuilder.compact());
    }
}
