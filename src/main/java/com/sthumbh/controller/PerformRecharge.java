package com.sthumbh.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sthumbh.Entity.BloodDetailsEntity;
import com.sthumbh.dto.BloodDetailsDto;
import com.sthumbh.dto.RechargeDto;
import com.sthumbh.model.*;
import com.sthumbh.service.PerformRechargeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PerformRecharge {

    @Autowired
    private PerformRechargeService performRechargeService;


    @PostMapping(value = "/recharge")
    public ResponseEntity<CustomResponseModel> performRecharge(@RequestBody RechargeDto rechargeDto) {
        //String customResponseModel = performRechargeService.performRecharge(rechargeDto);
        RechargeDto CustomResponseModel1 = performRechargeService.performRecharge(rechargeDto);
        return new ResponseEntity<>(getResponse("200", "Success", "Recharge Done", CustomResponseModel1), HttpStatus.CREATED);
    }

    private CustomResponseModel getResponse(String statusCode, String status, String message, RechargeDto bloodDetailsEntity) {
        MetaDate metaDate = MetaDate.builder()
                .code(statusCode).status(status)
                .message(message).version("1.0")
                .build();
        ResourceData resourceData = ResourceData.builder()
                .data(bloodDetailsEntity)
                .build();
        return CustomResponseModel.builder()
                .metaDate(metaDate)
                .resourceData(resourceData)
                .build();
    }

    @GetMapping(value = "/find-recharges")
    public ResponseEntity<CustomResponseModel> getAllRecharge() throws JsonProcessingException {
        CustomResponseModel1 customResponseModel1 = performRechargeService.getAllRecharge();

        List<RechargeDto> rechargeDto1 = null;
        ResourceData1 resourceData1 = customResponseModel1.getResourceData();
        if (resourceData1 != null) {
            ObjectMapper objectMapper = new ObjectMapper();
            rechargeDto1 = objectMapper.convertValue(resourceData1.getData(), List.class);
        }
        MetaDate metaDate = MetaDate.builder()
                .code("200").status("OK")
                .message("Success").version("1.0")
                .build();
        ResourceData resourceData = ResourceData.builder()
                .data(rechargeDto1)
                .build();
        CustomResponseModel customResponseModel = CustomResponseModel.builder()
                .metaDate(metaDate)
                .resourceData(resourceData)
                .build();
        return new ResponseEntity<>(customResponseModel, HttpStatus.OK);
    }
}
