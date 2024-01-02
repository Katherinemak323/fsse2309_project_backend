package com.example.fsse2309_project_backend.util;

import com.example.fsse2309_project_backend.data.user.domainObject.FirebaseUserData;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

public class JwtUtil {
    public static FirebaseUserData getFirebaseUser (JwtAuthenticationToken jwt) {
        return new FirebaseUserData(jwt);
    }
}
