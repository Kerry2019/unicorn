package com.smec.mpaas.unicorn.pojo;

import java.util.Optional;


public class UserProfileThread {
    private static ThreadLocal<UserProfile> USER_PROFILE_TL =new ThreadLocal<>();

    public static void  setUserProfile(UserProfile userProfile){
        USER_PROFILE_TL.set(userProfile);
    }

    public static UserProfile getUserProfile() {
        return USER_PROFILE_TL.get();
    }

    public static String getCurrentUser() {
        return Optional.ofNullable(USER_PROFILE_TL.get())
                .map(UserProfile::getUid)
                .orElse(UserProfile.ANONYMOUS_USER);
    }
}
