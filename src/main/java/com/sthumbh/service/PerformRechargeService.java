package com.sthumbh.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sthumbh.dto.RechargeDto;
import com.sthumbh.model.CustomResponseModel;
import com.sthumbh.model.CustomResponseModel1;
import com.sthumbh.model.ResourceData1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PerformRechargeService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${recharge.url}")
    private String URL;

    /*public String performRecharge(RechargeDto rechargeDto) {

        HttpMethod httpMethod  = HttpMethod.POST;

        HttpEntity<RechargeDto> requestEntity = new HttpEntity<>(rechargeDto);

        ResponseEntity<String> responseEntity = restTemplate.exchange(URL,httpMethod,requestEntity,String.class);
        return responseEntity.getBody();

    }*/
    public RechargeDto performRecharge(RechargeDto rechargeDto) {

        HttpEntity<RechargeDto> requestEntity = new HttpEntity<>(rechargeDto);

        ResponseEntity<CustomResponseModel1> responseEntity = restTemplate.exchange(URL,
                HttpMethod.POST, requestEntity, new ParameterizedTypeReference<CustomResponseModel1>() {
                });

        CustomResponseModel1 customResponseModel1 = responseEntity.getBody();

        ResourceData1 resourceData1 = customResponseModel1.getResourceData();
        ObjectMapper objectMapper = new ObjectMapper();
        RechargeDto rechargeDto1 = objectMapper.convertValue(resourceData1.getData(), RechargeDto.class);
        return rechargeDto1;

    }
}
