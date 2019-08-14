package com.tensquare.jwt;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

/**
 * <p>测试生成token并添加过期时间</p>
 *
 * @Author: ShangJiaPeng
 * @Since: 2019-08-13 11:56
 */
public class CreateJwtTest2 {
    //为了测试方便,将过期时间设置为1分钟
    public static void main(String[] args) {
        long currentTimeMillis = System.currentTimeMillis();//当前时间毫秒值
        long exp =currentTimeMillis+1000*60;//过期时间设置为1分钟
        JwtBuilder jwtBuilder = Jwts.builder()
                .setId("2")
                .setSubject("shang")
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, "shang")
                .setExpiration(new Date(exp));
        System.out.println(jwtBuilder.compact());
    }
}
