package com.smec.mpaas.unicorn.filter;

import com.alibaba.fastjson.JSON;
import com.smec.mpaas.unicorn.adapter.MPaasSSOAuthentication;
import com.smec.mpaas.unicorn.pojo.Response;
import com.smec.mpaas.unicorn.pojo.UserProfile;
import com.smec.mpaas.unicorn.pojo.UserProfileThread;
import com.smec.mpaas.unicorn.property.SecurityProperty;

import com.smec.mpaas.unicorn.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Pattern;


@Component
public class SecurityFilter implements Filter {
    @Autowired
    private SecurityProperty securityProperty;
    @Autowired(required = false)
    private MPaasSSOAuthentication ssoAuthentication;
    @Autowired
    private JwtUtil jwtUtil;


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    /**
     * 1、在进行跨域请求时，浏览器会预先发送OPTIONS请求，探测后续请求是否可接受，OPTIONS请求要放行
     *
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        UserProfile userProfile = null;
        //放行 OPTIONS 请求
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        if (RequestMethod.OPTIONS.name().equals(httpServletRequest.getMethod())) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        //针对不同mode，分别获取UserProfile
        try {
            SecurityProperty.MODE_ENUM mode = Optional.ofNullable(securityProperty.getMode())
                    .map(SecurityProperty.MODE_ENUM::valueOf)
                    .orElse(SecurityProperty.MODE_ENUM.oam);
            switch (mode) {
                case oam:
                    userProfile = oamHandle(httpServletRequest);
                    break;
                case adfs:
                    userProfile = adfseHandle(httpServletRequest);
                    break;
                case custom:
                    userProfile = customHandle(httpServletRequest);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            userProfile = UserProfile.ANONYMOUS_OBJ;
        }

        //安全校验
        if (securityProperty.isOpen() && userProfile.isAnonymous()) {
            String uri = httpServletRequest.getRequestURI();
            boolean pub = false;
            if (securityProperty.getPublicRoute() != null) {
                for (String pr : securityProperty.getPublicRoute().split(",")) {
                    if (Pattern.compile(pr).matcher(uri).find()) {
                        pub = true;
                        break;
                    }
                }
            }

            if (!pub) {
                this.unAuthorized(servletResponse);
                return;
            }
        }
        UserProfileThread.setUserProfile(userProfile);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }

    /**
     * 未授权的处理
     *
     * @param servletResponse
     */
    private void unAuthorized(ServletResponse servletResponse) {
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        try {
            res.getOutputStream().write(JSON.toJSONString(Response.fail("Unauthorized")).getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    }

    /**
     * mode= oam，处理方式
     *
     * @param httpServletRequest
     * @return
     */
    private UserProfile oamHandle(HttpServletRequest httpServletRequest) {
        UserProfile userProfile = null;
        String uid = httpServletRequest.getHeader(securityProperty.headName());
        if (uid == null) {
            userProfile = UserProfile.ANONYMOUS_OBJ;
        } else {
            userProfile = new UserProfile(uid, false);
            //根据业务系统补充代码，通过uid 获取其他属性
        }
        return userProfile;
    }

    /**
     * mode= adfs，处理方式
     *
     * @param httpServletRequest
     * @return
     */
    private UserProfile adfseHandle(HttpServletRequest httpServletRequest) throws Exception {
        UserProfile userProfile = null;
        String token = httpServletRequest.getHeader(securityProperty.headName());
        if (token == null) {
            userProfile = UserProfile.ANONYMOUS_OBJ;
        } else {
            Map<String, Object> userMap = jwtUtil.parseAccessToken(token);
            String uid = (String) userMap.get("upn");
            String username = (String) userMap.get("unique_name");
            userProfile = new UserProfile(uid.substring(0, uid.indexOf("@")), false);
            userProfile.setUserName(username);
            //根据业务系统补充代码，通过uid 获取其他属性
        }
        return userProfile;
    }

    /**
     * mode= custom，处理方式
     *
     * @param httpServletRequest
     * @return
     */
    private UserProfile customHandle(HttpServletRequest httpServletRequest) {
        UserProfile userProfile = null;
        Cookie[] cookies = httpServletRequest.getCookies();
        Map<String, String> cookieMap = new HashMap<>();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                cookieMap.put(cookie.getName(), cookie.getValue());
            }
        }
        Map<String, String> headerMap = new HashMap<>();
        Enumeration<String> headers = httpServletRequest.getHeaderNames();
        while (headers.hasMoreElements()) {
            String label = headers.nextElement();
            headerMap.put(label, httpServletRequest.getHeader(label));
        }
        try {
            userProfile = ssoAuthentication.ssoAuth(headerMap, cookieMap);
        } catch (Throwable ex) {
            ex.printStackTrace();
            userProfile = UserProfile.ANONYMOUS_OBJ;
        }
        return userProfile;
    }

}

