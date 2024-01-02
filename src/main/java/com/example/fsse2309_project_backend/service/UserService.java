package com.example.fsse2309_project_backend.service;


import com.example.fsse2309_project_backend.data.user.domainObject.FirebaseUserData;
import com.example.fsse2309_project_backend.data.user.entity.UserEntity;

public interface UserService {
    UserEntity getEntityByFirebaseUserData(FirebaseUserData firebaseUserData);


}
