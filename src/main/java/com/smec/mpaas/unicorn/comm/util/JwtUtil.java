package com.smec.mpaas.unicorn.comm.util;

import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.jwk.source.RemoteJWKSet;
import com.nimbusds.jose.proc.JWSKeySelector;
import com.nimbusds.jose.proc.JWSVerificationKeySelector;
import com.nimbusds.jose.proc.SecurityContext;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.proc.ConfigurableJWTProcessor;
import com.nimbusds.jwt.proc.DefaultJWTProcessor;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.Map;


@Component
public class JwtUtil {
    private static final String JWT_ENDPOINT="https://adfs.smec-cn.com/adfs/discovery/keys";

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
        JWKSource keySource= new RemoteJWKSet(new URL(JWT_ENDPOINT));
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
