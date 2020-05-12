package com.smec.mpaas.unicorn.comm.util;

import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.jwk.source.DefaultJWKSetCache;
import com.nimbusds.jose.jwk.source.JWKSetCache;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.jwk.source.RemoteJWKSet;
import com.nimbusds.jose.proc.JWSKeySelector;
import com.nimbusds.jose.proc.JWSVerificationKeySelector;
import com.nimbusds.jose.proc.SecurityContext;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.proc.ConfigurableJWTProcessor;
import com.nimbusds.jwt.proc.DefaultJWTProcessor;
import com.smec.mpaas.unicorn.comm.property.SecurityProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.Map;
import java.util.concurrent.TimeUnit;


@Component
public class JwtUtil {
    JWKSetCache jwkSetCache= new DefaultJWKSetCache(1, TimeUnit.HOURS);

    @Autowired
    private SecurityProperty securityProperty;


    /**
     * 验证并解析 jwt access_token
     *  JWT_ENDPOINT 公钥地址
     * @param accessToken
     * @return
     * @throws Exception
     */
    public  Map<String, Object> parseAccessToken(String accessToken) throws Exception {
        JWTClaimsSet claimsSet = null;
        ConfigurableJWTProcessor jwtProcessor = new DefaultJWTProcessor();
        JWKSource keySource= new RemoteJWKSet(new URL(securityProperty.getJwksUri()),null,jwkSetCache);
        JWSAlgorithm expectedJWSAlg = JWSAlgorithm.RS256;
        JWSKeySelector keySelector = new JWSVerificationKeySelector(expectedJWSAlg, keySource);
        if (keySelector != null) {
            jwtProcessor.setJWSKeySelector(keySelector);
            SecurityContext ctx = null;
            claimsSet = jwtProcessor.process(accessToken, ctx);
        }
        return claimsSet.getClaims();
    }

}
