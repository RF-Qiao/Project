package com.feng.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.impl.ClaimsHolder;
import com.auth0.jwt.impl.JWTParser;
import com.auth0.jwt.interfaces.Claim;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TokenUtils {
    //设置过期时间
    private static final long EXPIRE_DATE = 30 * 60 * 1000;
    //token秘钥
    private static final String TOKEN_SECRET = "ZCfasfhuaUUHufguGuwu2020BQWE";

    public static String token(Integer id, Integer role) {
        String token = "";
        try {
            //过期时间
            Date date = new Date(System.currentTimeMillis() + EXPIRE_DATE);
            //秘钥及加密算法
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            //设置头部信息
            Map<String, Object> header = new HashMap<>();
            header.put("typ", "JWT");
            header.put("alg", "HS256");
            //携带id，role信息，生成签名
            token = JWT.create()
                    .withHeader(header)
                    .withClaim("id", id)
                    .withClaim("role", role)
                    .withExpiresAt(date)
                    .sign(algorithm);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return "Bearer " + token;
    }

    public static Integer verify(String token) {
        /**
         * @desc 验证token，通过返回true
         * @params [token]需要校验的串
         **/
        try {
            //密钥及加密算法
            Map<String, Claim> claims = JWT.decode(token).getClaims();
            return claims.get("role").asInt();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static Date datadecode(String token) {

        Date claims = JWT.decode(token).getExpiresAt();

        return claims;
    }

}

