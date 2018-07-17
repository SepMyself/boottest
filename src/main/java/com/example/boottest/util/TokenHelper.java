package com.example.boottest.util;

import com.example.boottest.config.CustomConfig;
import com.example.boottest.model.CustomCfg;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

public class TokenHelper {

    public static TokenInfo parseToken(String token){
        String[] strs = token.split(" ");
        if(strs.length != 2 || !strs[0].equals("CAuth")){
            return null;
        }else{
            // search in cache
            // search in db
        }
        TokenInfo tokenInfo = new TokenInfo();
        tokenInfo.setId("admin");
        return tokenInfo;
    }

    public static String toToken(TokenInfo info, String apiKey){
        // https://github.com/jwtk/jjwt
        Key key =  MacProvider.generateKey();
        String compactJws = Jwts.builder()
                .setSubject("Joe")
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();
        return compactJws;
    }
}
