package com.smec.mpaas.unicorn.comm.adapter;


import com.smec.mpaas.unicorn.comm.exception.RPaasBusinessException;
import com.smec.mpaas.unicorn.comm.pojo.UserProfile;

import java.util.Map;

/**
 * 对外的接口
 */
public interface JWKSEnhanceUserProfile {

    /**
     * 将包含用户标识的Map对象，增强成自定义内容的UserProfile
     * @param originMap
     * @return
     * @throws RPaasBusinessException
     */
    UserProfile enhance(Map<String,Object> originMap)throws RPaasBusinessException;
}
