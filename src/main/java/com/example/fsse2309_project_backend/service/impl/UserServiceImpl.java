package com.example.fsse2309_project_backend.service.impl;

import com.example.fsse2309_project_backend.data.user.domainObject.FirebaseUserData;
import com.example.fsse2309_project_backend.data.user.entity.UserEntity;
import com.example.fsse2309_project_backend.repository.UserRepository;
import com.example.fsse2309_project_backend.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserEntity getEntityByFirebaseUserData(FirebaseUserData firebaseUserData) {
        Optional<UserEntity> optionalUserEntity =
                userRepository.findByFirebaseUid(firebaseUserData.getFirebaseUid());

        if (optionalUserEntity.isEmpty()) {
//            UserEntity userEntity = new UserEntity(firebaseUserData);
//            userEntity = userRepository.save(userEntity);
//            return userEntity;
//        }
            return userRepository.save(new UserEntity(firebaseUserData));
        }
        return optionalUserEntity.get();
    }
}

