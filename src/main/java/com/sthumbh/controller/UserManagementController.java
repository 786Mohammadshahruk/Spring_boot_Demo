package com.sthumbh.controller;

import com.sthumbh.Entity.UserEntity;
import com.sthumbh.dto.UserRequestDto;
import com.sthumbh.dto.UserResponseDto;
import com.sthumbh.model.CustomResponseModel;
import com.sthumbh.model.MetaDate;
import com.sthumbh.model.ResourceData;
import com.sthumbh.service.UserManagementService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class UserManagementController {

    private final UserManagementService userManagementService;

    public UserManagementController(UserManagementService userManagementService) {
        this.userManagementService = userManagementService;
    }


    @PostMapping(value = "/create")
    public ResponseEntity<CustomResponseModel> createUser(@RequestBody @Valid UserRequestDto userRequestDto) {
        UserResponseDto userResponseDto = userManagementService.createUser(userRequestDto);
        if (userRequestDto == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }


        MetaDate metaDate = MetaDate.builder()
                .code("201").status("Success")
                .message("User Created").version("1.0")
                .build();

        ResourceData resourceData = ResourceData.builder()
                .data(userResponseDto)
                .build();

        CustomResponseModel customResponseModel = CustomResponseModel.builder()
                .metaDate(metaDate)
                .resourceData(resourceData)
                .build();

        return new ResponseEntity<>(customResponseModel, HttpStatus.CREATED);
    }


    @GetMapping(value = "/getAll-user")
    public ResponseEntity<CustomResponseModel> getUsers() {

        List<UserEntity> userEntities = userManagementService.getUsers();

        /*MetaDate metaDate = new MetaDate();
        metaDate.setCode("200");
        metaDate.setStatus("Success");
        metaDate.setMessage("Success");
        metaDate.setVersion("1.0");

        ResourceData resourceData = new ResourceData();
        resourceData.setData(userEntities);

        CustomResponseModel customResponseModel = new CustomResponseModel();
        customResponseModel.setResourceData(resourceData);
        customResponseModel.setMetaDate(metaDate);*/

        MetaDate metaDate = MetaDate.builder().code("200").status("Success").message("Success").version("1.0").build();
        ResourceData resourceData = ResourceData.builder().data(userEntities).build();
        CustomResponseModel customResponseModel = CustomResponseModel.builder().metaDate(metaDate).resourceData(resourceData).build();

        return new ResponseEntity<>(customResponseModel, HttpStatus.OK);

    }

    @GetMapping(value = "/getAll-user/{id}")
    public ResponseEntity<CustomResponseModel> getUsersById(@PathVariable(name = "id") long userId) {

        UserEntity userEntities = userManagementService.getUsersById(userId);
        String message = "Success";
        if (userEntities == null) {
            message = "No User Found With Id : "+userId;
        }
        MetaDate metaDate = MetaDate.builder().code("200").status("Success").message(message).version("1.0").build();
        ResourceData resourceData = ResourceData.builder().data(userEntities).build();
        CustomResponseModel customResponseModel = CustomResponseModel.builder().metaDate(metaDate).resourceData(resourceData).build();

        return new ResponseEntity<>(customResponseModel, HttpStatus.OK);

    }



}