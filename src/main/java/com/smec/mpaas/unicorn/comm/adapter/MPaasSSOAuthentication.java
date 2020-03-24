package com.smec.mpaas.unicorn.comm.adapter;

import com.smec.mpaas.unicorn.comm.exception.MPaasBusinessException;
import com.smec.mpaas.unicorn.comm.pojo.UserProfile;

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
