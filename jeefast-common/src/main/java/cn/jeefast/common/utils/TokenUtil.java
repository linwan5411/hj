package cn.jeefast.common.utils;

import cn.jeefast.common.enums.ResultEnum;
import cn.jeefast.common.exception.BusinessException;
import cn.jeefast.entity.HjUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/5/16.
 */
public class TokenUtil {

    private static final String secret = "omoDD246F5YjsAHhBrDhfKHinzy2b9CreB1UKeMssPs";
    private static final String subject = "hjn";

    /**
     * 创建token
     * @param clasims
     * @return
     */
    public static String createToken(Map<String,Object> clasims){
        return createJWT(clasims,subject,null);
    }


    public static String createJWT(Map<String,Object> clasims,String subject){
        return createJWT(clasims,subject,null);
    }

    public static String createJWT(Map<String,Object> clasims ,String subject,Long ttlMillis) {

        //The JWT signature algorithm we will be using to sign the token
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        //We will sign our JWT with our ApiKey secret
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secret);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        //Let's set the JWT Claims
        JwtBuilder builder = Jwts.builder()
                //.setId(id)
                .setIssuedAt(now)  //发起时间
                .setSubject(subject)  //主题
                //.setIssuer(issuer)
                .addClaims(clasims)
                .signWith(signatureAlgorithm, signingKey);

        //if it has been specified, let's add the expiration
        if (ttlMillis!=null && ttlMillis > 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);  //过期时间
        }

        //Builds the JWT and serializes it to a compact, URL-safe string
        return builder.compact();
    }


    /**
     * 解析token
     * @param jwt
     * @return
     */
    public static Claims parseJWT(String jwt) {
        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(secret))
                .parseClaimsJws(jwt).getBody();
        /*System.out.println("ID: " + claims.getId());
        System.out.println("Subject: " + claims.getSubject()); //主题
        System.out.println("Issuer: " + claims.getIssuer());
        System.out.println("IssuedAt: " + claims.getIssuedAt());  //时间戳
        System.out.println("userMobile: " + claims.get("userMobile")); //自定义数据
        System.out.println("partnerId: " + claims.get("partnerId")); //自定义数据
        System.out.println("Expiration: " + claims.getExpiration());  //过期时间*/
        return claims;
    }

    /**
     * 解析获的对应的用户ID
     * @param jwt
     * @return
     */
    public static Long parseUserId(String jwt){
        try {
            Claims c = parseJWT(jwt);
            Object userId =  c.get("userId");
            if(userId == null){
                throw new BusinessException("登陆信息不正确", ResultEnum.LOGIN_EXP.getCode());
            }
            return Long.valueOf(userId.toString());
        }catch (Exception e){
            throw new BusinessException("登陆信息不正确", ResultEnum.LOGIN_EXP.getCode());
        }

    }

    /**
     * 解析获的对应的用户ID
     * @param jwt
     * @return
     */
    public static HjUser parseUser(String jwt){
        try {
            Claims c = parseJWT(jwt);
            HjUser user = new HjUser();

            Object userId =  c.get("userId");
            if(userId == null){
                throw new BusinessException("登陆信息不正确", ResultEnum.LOGIN_EXP.getCode());
            }
            user.setUserId(Long.valueOf(userId.toString()));
            return user;
        }catch (Exception e){
            throw new BusinessException("登陆信息不正确", ResultEnum.LOGIN_EXP.getCode());
        }
    }

    /**
     * 登陆，注册时获取的相关头像，昵称
     * @param user
     * @return
     */
    public static Map<String,Object> loginRps(HjUser user){
        Map<String,Object> map = new HashMap<>();
       /* map.put("userName",user.getUserName());
        map.put("userMobile",user.getUserMobile());
        map.put("hideUserMobile",MobileUtils.subMobile(user.getUserMobile()));
        map.put("userPortrait",user.getUserPortrait());
        map.put("userType",user.getUserType());
        map.put("authType",user.getAuthType());*/
        map.put("userId",user.getUserId());
        String token = TokenUtil.createToken(map);
        map.put("token",token);
        map.remove("userId");
        return map;
    }


    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis());
        System.out.println("1539915263928".length());


        Map<String,Object> clasims = new HashMap<>();
        clasims.put("userMobile","11");
        clasims.put("partnerId","22");
        for(int i = 0 ;i< 100;i++) {
            long s = System.currentTimeMillis();
            System.out.println(TokenUtil.createJWT(clasims, "wallet系统"));
            System.out.println(System.currentTimeMillis() - s);
        }
        //parseJWT("eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE1MjY1NDQxOTQsInN1YiI6IndhbGxldOezu-e7nyIsInVzZXJNb2JpbGUiOiIxMSIsInBhcnRuZXJJZCI6IjIyIn0.nNI8UwcROeFjjL6WQBlT4Yp79bHk8k9U9TZWFtFlFZs");
    }
}
