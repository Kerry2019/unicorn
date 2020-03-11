package com.smec.mpaas.unicorn.util;

import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.jwk.source.RemoteJWKSet;
import com.nimbusds.jose.proc.JWSKeySelector;
import com.nimbusds.jose.proc.JWSVerificationKeySelector;
import com.nimbusds.jose.proc.SecurityContext;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.proc.ConfigurableJWTProcessor;
import com.nimbusds.jwt.proc.DefaultJWTProcessor;
import com.smec.mpaas.unicorn.exception.MPaasBusinessException;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.Map;

/**
 * @Copyright: Shanghai Definesys Company.All rights reserved.
 * @Description:
 * @author: kerry.wu
 * @since: 2020/3/7 23:07
 * @history: 1.2020/3/7 created by kerry.wu
 */
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
