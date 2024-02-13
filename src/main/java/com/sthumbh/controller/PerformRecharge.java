package com.sthumbh.controller;

import com.sthumbh.Entity.BloodDetailsEntity;
import com.sthumbh.dto.BloodDetailsDto;
import com.sthumbh.dto.RechargeDto;
import com.sthumbh.model.CustomResponseModel;
import com.sthumbh.model.CustomResponseModel1;
import com.sthumbh.model.MetaDate;
import com.sthumbh.model.ResourceData;
import com.sthumbh.service.PerformRechargeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
}
