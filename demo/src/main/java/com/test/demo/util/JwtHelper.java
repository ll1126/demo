package com.test.demo.util;


import com.alibaba.fastjson.JSONObject;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Map;

/**
 * JWT工具类
 */
public class JwtHelper {
    private static final Logger logger = LoggerFactory.getLogger(JwtHelper.class);

    /**
     * 该方法 在验证jsonWebToken有效性之后调用
     * @param jsonWebToken
     * @return
     */
    public static Integer getUserId(String jsonWebToken,String base64Security){
        Claims c = parseJWT(jsonWebToken,base64Security);
        JSONObject json = (JSONObject) JSONObject.toJSON(c);
        System.out.println(json.toString());
        return json.getInteger("userId");


    }

    public static Map<String,String> getRolesMap(String jsonWebToken,String base64Security){
        Claims c = parseJWT(jsonWebToken,base64Security);
        JSONObject json = (JSONObject) JSONObject.toJSON(c);
        return (Map<String, String>) json.get("rolesInfo");
    }

    public static Claims parseJWT(String jsonWebToken, String base64Security){
        try{
            Claims claims = Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(base64Security))
                    .parseClaimsJws(jsonWebToken).getBody();
            return claims;
        }catch(Exception ex)
        {
            return null;
        }
    }

    /**
     * 生成JWT
     * @param userId
     * @param TTLMillis
     * @param base64Security
     * @return
     */
    public static String createJWT(String userId,long TTLMillis, String base64Security){
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        //生成签名密钥
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(base64Security);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        //添加构成JWT的参数
        JwtBuilder builder = Jwts.builder().setHeaderParam("typ", "JWT")
                // .claim("role", role)
               // .claim("userInfo", user)
                .claim("userId",userId)
                //    .setIssuer(issuer)
//                .setSubject()
                //    .setAudience(audience)
                .signWith(signatureAlgorithm, signingKey);
        //添加Token过期时间
        if (TTLMillis >= 0) {
            long expMillis = nowMillis + TTLMillis;
            System.out.println(expMillis);
            Date exp = new Date(expMillis);
            builder.setExpiration(exp).setNotBefore(now);
        }
        //生成JWT
        return builder.compact();
    }

    /**
     *
     * @param userId
     * @param TTLMillis 自定义
     * @param secret
     * @return
     */
    public static String createZJYJWT(String userId,long TTLMillis, Integer  expHour,String secret){
        String header = "{\"typ\":\"JWT\", \"alg\" : \"HS256\"}";
        JSONObject json = new JSONObject();
        json.put("userId",userId);
        long nowMillis = System.currentTimeMillis();
//        Date now = new Date(nowMillis);
        if (TTLMillis >= 0) {
            long expMillis = nowMillis + TTLMillis;
//            Date exp = new Date(expMillis);
            json.put("exp",expMillis);
            json.put("expHour",expHour);
        }

        String base64Header = Base64.encodeBase64URLSafeString(header.getBytes());
//        System.out.println(new String (Base64.decodeBase64(base64Header.getBytes())));
        System.out.println(json.toString());
        String base64Claim = Base64.encodeBase64URLSafeString(json.toString().getBytes());
//        System.out.println(new String (Base64.decodeBase64(base64Claim.getBytes())));
        String signature = null;
        try {
            signature = Hmacsha256(secret, base64Header + "." + base64Claim);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        String jwt = base64Header + "." + base64Claim  + "." + signature;

        return jwt;
    }

    public static JSONObject parseZdyJWT(String jsonWebToken, String secret){
        try{
            String[] jwtStr = jsonWebToken.split("\\.");
//            String base64Header = new String(Base64.decodeBase64(jwtStr[0].toString().getBytes()));
//            String base64Claim = new String(Base64.decodeBase64(jwtStr[1].toString().getBytes()));
            String signature = Hmacsha256(secret, jwtStr[0] + "." + jwtStr[1]);
//            System.out.println(base64Claim);
            if (!signature.equals(jwtStr[2].toString())){
                return  null;
            }
            String base64Claim = new String(Base64.decodeBase64(jwtStr[1].toString().getBytes()));
            JSONObject claim = JSONObject.parseObject(base64Claim);
            System.out.println(claim.toString());
            long exp = claim.getLong("exp");
            long nowMillis = System.currentTimeMillis();
            if (nowMillis >=exp) {
               return  null;
            }
            return  claim;
        }catch(Exception ex)
        {
            return null;
        }
    }

    /**
     * 该方法 在验证jsonWebToken有效性之后调用
     * @param jsonWebToken
     * @return
     */
    public static Integer getZdyUserId(String jsonWebToken,String base64Security){
        JSONObject c = parseZdyJWT(jsonWebToken,base64Security);
        if (c==null){
            return  null;
        }
        return Integer.parseInt(c.getString("userId"));


    }


    private static final  String MAC_INSTANCE_NAME = "HMacSHA256";

    public static String Hmacsha256(String secret, String message) throws NoSuchAlgorithmException, InvalidKeyException {
        Mac hmac_sha256 = Mac.getInstance(MAC_INSTANCE_NAME);
        SecretKeySpec key = new SecretKeySpec(secret.getBytes(), MAC_INSTANCE_NAME);
        hmac_sha256.init(key);
        byte[] buff = hmac_sha256.doFinal(message.getBytes());
        return Base64.encodeBase64URLSafeString(buff);
    }

    public static void main(String[] args) {

        String t ="eyJ0eXAiOiJKV1QiLCAiYWxnIiA6ICJIUzI1NiJ9.eyJleHBIb3VyIjoxLCJleHAiOjE1MjQ5MTkxNjU4NDYsInVzZXJJZCI6IjIwIn0.qrOR3i_xtkvuUsbQqHOs7aVUNVRgC9Xnw5hLw4iCXPU";
        System.out.println(t);

        Integer userId = getZdyUserId(t,"1abc$def2.TEZ");
        System.out.println(userId);

    }
}
