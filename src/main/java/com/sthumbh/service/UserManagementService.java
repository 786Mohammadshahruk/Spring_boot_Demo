package com.sthumbh.service;

import com.sthumbh.Entity.UserEntity;
import com.sthumbh.dto.UserRequestDto;
import com.sthumbh.dto.UserResponseDto;
import com.sthumbh.repository.UserManagementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserManagementService {

    @Autowired
    private UserManagementRepository userManagementRepository;

    public UserResponseDto createUser(UserRequestDto userRequestDto) {
        UserEntity userEntity = new UserEntity();
        userEntity.setName(userRequestDto.getName());
        userEntity.setAddress(userRequestDto.getAddress());
        userEntity.setLastName(userRequestDto.getLastName());
        userEntity.setMobileNumber(userRequestDto.getMobileNumber());

        UserEntity responseEntity = userManagementRepository.save(userEntity);

        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(responseEntity.getId());
        userResponseDto.setName(responseEntity.getName());
        userResponseDto.setLastName(responseEntity.getLastName());
        userResponseDto.setAddress(responseEntity.getAddress());
        userResponseDto.setMobileNumber(responseEntity.getMobileNumber());
        return userResponseDto;
    }

    public List<UserEntity> getUsers() {
        return userManagementRepository.findAll();
    }

    public UserEntity getUsersById(long userId) {
        Optional<UserEntity> userEntity = userManagementRepository.findById(userId);
        if (!userEntity.isEmpty()) {
            return userEntity.get();
        }
        return null;
    }

    public List<UserEntity> getUsersByName(String name) {
        Optional<List<UserEntity>> userEntityOptional = userManagementRepository.findByLastName(name);
        if (!userEntityOptional.isEmpty()) {
            return userEntityOptional.get();
        }
        return null;
    }

    public List<UserEntity> getUsersFindAllById() {
        List<Long> ids = Arrays.asList(1l, 2l, 3l, 4l,20l);
        List<UserEntity> userEntityList = userManagementRepository.findAllById(ids);
        return userEntityList;
    }

    public UserEntity updateUser(Long id, UserRequestDto userRequestDto) {
       Optional<UserEntity> optionalUserEntity =  userManagementRepository.findById(id);
       if(optionalUserEntity.isPresent()){
           UserEntity userEntity = optionalUserEntity.get();
           userEntity.setName(userRequestDto.getName());
           userEntity.setAddress(userRequestDto.getAddress());
           return userManagementRepository.save(userEntity);
       }
       return null;
    }

    public UserEntity finByMobileNumber(String mobileNumber) {
        return userManagementRepository.findEntityMobileNumber(mobileNumber);
    }
}
