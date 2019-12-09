package com.xinghuofirst.kill.server.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhou_gc@suixingpay.com
 * @description  token 工具类
 * @date 2019/12/3 11:01
 */
public class TokenUtil {

    private static final String SECRET = "SECRETOPENLOCK";//还可以使用其他单映射加密算法进行生成加密性更高的密钥以实现密钥随机性
    private static final long DEFAULT_EXPIRE_TIME=900000L; //默认过期时间为15分钟
    private  static  final String ISSUER="TokenUtil";

    /**
     *@描述 获取token
     *@参数  传入需要封装在token中的信息和过期时间
     *@返回值  返回token 字符串
     *@创建人  zhou_gc@suixingpay.com
     *@创建时间  2019/12/3
     *@修改人和其它信息
     */
    public static String getToken(Map<String, String> claims, long expire_time){
        expire_time = (expire_time == 0? DEFAULT_EXPIRE_TIME :expire_time);
        Date expiresDate = new Date(System.currentTimeMillis() + expire_time);
        try {

            JWTCreator.Builder builder = JWT.create().withIssuer(ISSUER);
            claims.forEach((key,value)-> {
                builder.withClaim(key, value);
            });
            builder.withExpiresAt(expiresDate);
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            return builder.sign(algorithm);
        } catch (IllegalArgumentException  e) {
            System.out.println("getToken出现Exception..............please call zhou_gc to fix it");
            throw new RuntimeException(e);
        }
    }
    /**
     *@描述 验证token
     *@参数  token 字符串
     *@返回值  返回token中封装的信息
     *@创建人  zhou_gc@suixingpay.com
     *@创建时间  2019/12/3
     *@修改人和其它信息
     */
    public static Map<String,String> verifyToken(String token) throws Exception {
        Algorithm algorithm = Algorithm.HMAC256(SECRET);
        JWTVerifier verifier = JWT.require(algorithm).withIssuer(ISSUER).build();
        try {
            DecodedJWT jwt =  verifier.verify(token);
            Map<String, Claim> map = jwt.getClaims();
            Map<String, String> resultMap = new HashMap<>();
            map.forEach((k,v) -> resultMap.put(k, v.asString()));
            return resultMap;
        }catch ( JWTVerificationException  e)
        {
            throw new Exception("token失效");
        }


    }


}
