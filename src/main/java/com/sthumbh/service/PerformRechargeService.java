package com.sthumbh.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sthumbh.dto.RechargeDto;
import com.sthumbh.model.CustomResponseModel;
import com.sthumbh.model.CustomResponseModel1;
import com.sthumbh.model.ResourceData1;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@Slf4j
public class PerformRechargeService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${recharge.url}")
    private String URL;
    @Value("${find.recharges.url}")
    private String findRechargesUrl;

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

    public CustomResponseModel1 getAllRecharge() throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Auth","Test");

        HttpEntity<RechargeDto> requestEntity = new HttpEntity<>(new RechargeDto(), headers);

        ResponseEntity<CustomResponseModel1> responseEntity = null;
        try {
            responseEntity = restTemplate
                    .exchange(findRechargesUrl,
                            HttpMethod.GET,
                            requestEntity,
                            new ParameterizedTypeReference<CustomResponseModel1>() {
                            });
        } catch (HttpStatusCodeException exception) {
            String exceptionBody = exception.getResponseBodyAsString();
            ObjectMapper objectMapper = new ObjectMapper();
            CustomResponseModel1 customResponseModel1 = objectMapper.readValue(exceptionBody, CustomResponseModel1.class);
            log.error("Getting Error while calling Recharge Service : {}", customResponseModel1);
            return customResponseModel1;
        }

        CustomResponseModel1 customResponseModel1 = responseEntity.getBody();
        return customResponseModel1;
    }
}
