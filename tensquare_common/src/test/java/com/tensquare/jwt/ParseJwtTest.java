package com.tensquare.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>测试解析token</p>
 * 测试运行，当未过期时可以正常读取，当过期时会引发 io.jsonwebtoken.ExpiredJwtException异常
 * @Author: ShangJiaPeng
 * @Since: 2019-08-13 11:52
 */
public class ParseJwtTest {

    public static void main(String[] args) {
        String token="eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIyIiwic3ViIjoic2hhbmciLCJpYXQiOjE1NjU2Nzk5NDYsImV4cCI6MTU2NTY4MDAwNSwicm9sZXMiOiJhZG1pbiIsInNleCI6IueUtyJ9.ny7ejKTQ9Cz6fLLZsxmTHu_3cCtJXHbgiifRxpzRrMI";
        Claims claims = Jwts.parser().setSigningKey("shang").parseClaimsJws(token).getBody();
        System.out.println("id:"+claims.getId());
        System.out.println("subject:"+claims.getSubject());
        System.out.println("roles:"+claims.get("roles"));
        System.out.println("sex:"+claims.get("sex"));
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy‐MM‐dd hh:mm:ss");
        System.out.println("签发时间:"+dateFormat.format(claims.getIssuedAt()));
        System.out.println("过期时间:"+dateFormat.format(claims.getExpiration()));
        System.out.println("当前时间:"+dateFormat.format(new Date()));
    }
}
