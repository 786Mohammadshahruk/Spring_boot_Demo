package com.sthumbh.controller;

import com.sthumbh.Entity.BloodDetailsEntity;
import com.sthumbh.dto.BloodDetailsDto;
import com.sthumbh.model.CustomResponseModel;
import com.sthumbh.model.MetaDate;
import com.sthumbh.model.ResourceData;
import com.sthumbh.service.BloodDonorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BloodDonorController<T> {

    @Autowired
    private BloodDonorService bloodDonorService;


    @PostMapping(value = "/addBloodType")
    public ResponseEntity<CustomResponseModel> createBloodTypeDetails(@RequestBody @Valid BloodDetailsDto bloodDetailsDto) {
        BloodDetailsEntity bloodDetailsEntity = bloodDonorService.createBloodTypeDetails(bloodDetailsDto);
        return new ResponseEntity<>(getResponse("200", "Success", "Blood Details Added", bloodDetailsEntity), HttpStatus.CREATED);
    }


    @GetMapping("/getBloodType/{type}")
    public ResponseEntity<CustomResponseModel> getBloodTypeDetails(
            @PathVariable(name = "type")String type,
            @RequestParam(name = "isDonateBloodTo", required = false, defaultValue = "true") boolean isDonateBloodTo,
            @RequestParam(name = "isReceiveBloodFrom", required = false, defaultValue = "true")boolean isReceiveBloodFrom)
    {
        BloodDetailsEntity bloodDetailsEntity = bloodDonorService.getBloodTypeDetails(type,isDonateBloodTo,isReceiveBloodFrom);
        return new ResponseEntity<>(getResponse("200", "Success", "Blood Details Added", bloodDetailsEntity), HttpStatus.CREATED);
    }

    private CustomResponseModel getResponse(String statusCode, String status, String message, BloodDetailsEntity bloodDetailsEntity) {
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
