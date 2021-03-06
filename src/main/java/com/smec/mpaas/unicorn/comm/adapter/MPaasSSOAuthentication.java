package com.smec.mpaas.unicorn.comm.adapter;

import com.smec.mpaas.unicorn.comm.exception.RPaasBusinessException;
import com.smec.mpaas.unicorn.comm.pojo.UserProfile;

import java.util.Map;

/**
 * 对外的接口
 */
public interface MPaasSSOAuthentication {
    /**
     * SSO认证，并返回UserProfile
     *
     * @param header
     * @param cookies
     * @return
     * @throws RPaasBusinessException
     */
     UserProfile ssoAuth(Map<String, String> header, Map<String, String> cookies) throws RPaasBusinessException;
}
