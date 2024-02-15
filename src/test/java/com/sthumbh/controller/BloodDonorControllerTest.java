package com.sthumbh.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sthumbh.Entity.BloodDetailsEntity;
import com.sthumbh.dto.BloodDetailsDto;
import com.sthumbh.service.BloodDonorService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = BloodDonorController.class)
@AutoConfigureMockMvc
public class BloodDonorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BloodDonorService bloodDonorService;

    String requestData = "{\n" +
            "    \"bloodType\": \"B+\",\n" +
            "    \"donateBloodTo\": [\n" +
            "        \"B+\",\n" +
            "        \"AB+\"\n" +
            "    ],\n" +
            "    \"receiveBloodFrom\": [\n" +
            "        \"B+\",\n" +
            "        \"B-\",\n" +
            "        \"O+\",\n" +
            "        \"O-\"\n" +
            "    ]\n" +
            "}";
    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void shouldReturn200Response() throws Exception {
        BloodDetailsEntity bloodDetailsEntity = new BloodDetailsEntity();
        bloodDetailsEntity.setBloodType("O+");
        //Mocking the service layer
        when(bloodDonorService.createBloodTypeDetails(any(BloodDetailsDto.class))).thenReturn(bloodDetailsEntity);
        mockMvc.perform(MockMvcRequestBuilders.post("/addBloodType")
                .contentType("application/json")
                .content(requestData)).andExpect(status().isCreated());
    }

    @Test
    void shouldReturn200AndBloodTypeResponse() throws Exception {
        BloodDetailsEntity bloodDetailsEntity = new BloodDetailsEntity();
        bloodDetailsEntity.setBloodType("O+");
        //Mocking the service layer
        when(bloodDonorService.createBloodTypeDetails(any(BloodDetailsDto.class))).thenReturn(bloodDetailsEntity);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/addBloodType")
                .contentType("application/json")
                .content(requestData)).andExpect(status().isCreated()).andReturn();

        String responseObject = mvcResult.getResponse().getContentAsString();
        Assertions.assertNotNull(responseObject);
    }

}
