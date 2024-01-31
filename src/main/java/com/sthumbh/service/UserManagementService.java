package com.sthumbh.service;

import com.sthumbh.Entity.UserEntity;
import com.sthumbh.dto.UserRequestDto;
import com.sthumbh.dto.UserResponseDto;
import com.sthumbh.repository.UserManagementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}