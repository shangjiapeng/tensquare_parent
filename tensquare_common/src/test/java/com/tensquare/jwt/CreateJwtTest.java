package com.tensquare.jwt;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

/**
 * <p>测试生成token</p>
 * iss: jwt签发者
 * sub: jwt所面向的用户
 * aud: 接收jwt的一方
 * exp: jwt的过期时间，这个过期时间必须要大于签发时间
 * nbf: 定义在什么时间之前，该jwt都是不可用的.
 * iat: jwt的签发时间
 * jti: jwt的唯一身份标识，主要用来作为一次性token,从而回避重放攻击。
 * 注意:secret是保存在服务器端的，jwt的签发生成也是在服务器端的，secret就是用 来进行jwt的签发和jwt的验证，
 * 所以，它就是你服务端的私钥，在任何场景都不应该流 露出去。一旦客户端得知这个secret, 那就意味着客户端是可以自我签发jwt了
 * @Author: ShangJiaPeng
 * @Since: 2019-08-13 11:39
 */
public class CreateJwtTest {
    public static void main(String[] args) {
        JwtBuilder jwtBuilder = Jwts.builder()
                .setId("2")
                .setSubject("shang")  //设置面向的用户
                .setIssuedAt(new Date())  //setIssuedAt用于设置签发时间
                .signWith(SignatureAlgorithm.HS256,"shang"); //signWith用于设置签名秘钥
        System.out.println(jwtBuilder.compact());


    }

}
