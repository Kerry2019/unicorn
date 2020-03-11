package com.smec.mpaas.unicorn.adapter;

import com.smec.mpaas.unicorn.exception.MPaasBusinessException;
import com.smec.mpaas.unicorn.pojo.UserProfile;

import java.util.Map;

/**
 * @Copyright: Shanghai Definesys Company.All rights reserved.
 * @Description:
 * @author: kerry.wu
 * @since: 2020/3/6 22:56
 * @history: 1.2020/3/6 created by kerry.wu
 */
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
