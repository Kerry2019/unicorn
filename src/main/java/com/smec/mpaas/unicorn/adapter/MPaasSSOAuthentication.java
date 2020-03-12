package com.smec.mpaas.unicorn.adapter;

import com.smec.mpaas.unicorn.exception.MPaasBusinessException;
import com.smec.mpaas.unicorn.pojo.UserProfile;

import java.util.Map;


public interface MPaasSSOAuthentication {
    /**
     * 单点登录认证
     *
     * @param header
     * @param cookies
     * @return
     * @throws MPaasBusinessException
     */
     UserProfile ssoAuth(Map<String, String> header, Map<String, String> cookies) throws MPaasBusinessException;
}
