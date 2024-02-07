package com.sthumbh.controller;

import com.sthumbh.Entity.UserEntity;
import com.sthumbh.dto.UserRequestDto;
import com.sthumbh.dto.UserResponseDto;
import com.sthumbh.model.CustomResponseModel;
import com.sthumbh.model.MetaDate;
import com.sthumbh.model.ResourceData;
import com.sthumbh.service.UserManagementService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "CURD REST API for User Management",
        description = "Create, Update, Delete, Get"
)
@RestController
@Slf4j
public class UserManagementController {

    private final UserManagementService userManagementService;

    public UserManagementController(UserManagementService userManagementService) {
        this.userManagementService = userManagementService;
    }


    @Operation(
            summary = "Creating a User",
            description = "Creating User REST API"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Http Status 201 Created"),
            @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
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
            message = "No User Found With Id : " + userId;
        }
        MetaDate metaDate = MetaDate.builder().code("200").status("Success").message(message).version("1.0").build();
        ResourceData resourceData = ResourceData.builder().data(userEntities).build();
        CustomResponseModel customResponseModel = CustomResponseModel.builder().metaDate(metaDate).resourceData(resourceData).build();

        return new ResponseEntity<>(customResponseModel, HttpStatus.OK);

    }

    @GetMapping(value = "/get-user/{name}")
    public ResponseEntity<CustomResponseModel> getUsersByName(@PathVariable(name = "name") String name) {
        List<UserEntity> userEntities = userManagementService.getUsersByName(name);

        String message = "Success";
        if (userEntities == null) {
            message = "No User Found With Name : " + name;
        }
        MetaDate metaDate = MetaDate.builder().code("200").status("Success").message(message).version("1.0").build();
        ResourceData resourceData = ResourceData.builder().data(userEntities).build();
        CustomResponseModel customResponseModel = CustomResponseModel.builder().metaDate(metaDate).resourceData(resourceData).build();
        return new ResponseEntity<>(customResponseModel, HttpStatus.OK);

    }

    @GetMapping(value = "/get-user-byIds")
    public ResponseEntity<CustomResponseModel> getUsersFindAllById() {
        List<UserEntity> userEntities = userManagementService.getUsersFindAllById();

        String message = "Success";
        if (userEntities == null) {
            message = "No User Found With Name : ";
        }
        MetaDate metaDate = MetaDate.builder().code("200").status("Success").message(message).version("1.0").build();
        ResourceData resourceData = ResourceData.builder().data(userEntities).build();
        CustomResponseModel customResponseModel = CustomResponseModel.builder().metaDate(metaDate).resourceData(resourceData).build();
        return new ResponseEntity<>(customResponseModel, HttpStatus.OK);

    }

    @PutMapping(value = "/update-user/{id}")
    public ResponseEntity<CustomResponseModel> updateUser(@PathVariable(name = "id") Long id,
                                                          @RequestBody UserRequestDto userRequestDto) {
        UserEntity userEntity = userManagementService.updateUser(id, userRequestDto);
        if (userEntity == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        MetaDate metaDate = MetaDate.builder().code("200").status("Success").message("Success").version("1.0").build();
        ResourceData resourceData = ResourceData.builder().data(userEntity).build();
        CustomResponseModel customResponseModel = CustomResponseModel.builder().metaDate(metaDate).resourceData(resourceData).build();
        return new ResponseEntity<>(customResponseModel, HttpStatus.OK);
    }

    @GetMapping(value = "/getUser-byMobileNumber/{mobile_number}")
    public ResponseEntity<CustomResponseModel> getByMobileNumber(@PathVariable(name = "mobile_number")
                                                                 String mobileNumber) {
        UserEntity userEntity = userManagementService.finByMobileNumber(mobileNumber);

        MetaDate metaDate = MetaDate.builder().code("200").status("Success").message("Success").version("1.0").build();
        ResourceData resourceData = ResourceData.builder().data(userEntity).build();
        CustomResponseModel customResponseModel = CustomResponseModel.builder().metaDate(metaDate).resourceData(resourceData).build();
        return new ResponseEntity<>(customResponseModel, HttpStatus.OK);
    }

    @DeleteMapping(value = "/deleteByName/{id}")
    public ResponseEntity<CustomResponseModel> deleteByName(@PathVariable(name = "id") Long id) {
        userManagementService.deleteByName(id);
        MetaDate metaDate = MetaDate.builder().code("200").status("Success").message("Deleted Successfully").version("1.0").build();
        CustomResponseModel customResponseModel = CustomResponseModel.builder().metaDate(metaDate).build();
        return new ResponseEntity<>(customResponseModel, HttpStatus.OK);
    }

    @GetMapping(value = "/getUser-byId-MobileNumber")
    public ResponseEntity<CustomResponseModel> getUserByNativeQuery(@RequestParam(name = "id") long id,
                                                                    @RequestParam(name = "mobile_number") String mobileNumber,
                                                                    @RequestParam(name = "address") String address) {
        UserEntity userEntity = userManagementService.getUserByNativeQuery(id, mobileNumber, address);

        MetaDate metaDate = MetaDate.builder().code("200").status("Success").message("Success").version("1.0").build();
        ResourceData resourceData = ResourceData.builder().data(userEntity).build();
        CustomResponseModel customResponseModel = CustomResponseModel.builder().metaDate(metaDate).resourceData(resourceData).build();
        return new ResponseEntity<>(customResponseModel, HttpStatus.OK);
    }

}
