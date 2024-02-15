package com.sthumbh.service;

import com.sthumbh.Entity.BloodDetailsEntity;
import com.sthumbh.dto.BloodDetailsDto;
import com.sthumbh.exception.handler.BloodDetailNotFoundException;
import com.sthumbh.repository.BloodDonorRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class BloodDonorServiceTest {
    @InjectMocks
    BloodDonorService bloodDonorService;
    @Mock
    BloodDonorRepository bloodDonorRepository;
    BloodDonorScheduler bloodDonorScheduler = mock(BloodDonorScheduler.class);

    @Test
    void createBloodTypeDetailsTest() {
        doNothing().when(bloodDonorScheduler).checkRecords();
        when(bloodDonorRepository.save(any(BloodDetailsEntity.class))).thenReturn(getBloodDetailEntity());
        BloodDetailsEntity bloodDetailsEntity = bloodDonorService.createBloodTypeDetails(getBloodDetailsDto());
        Assertions.assertEquals(getBloodDetailEntity(), bloodDetailsEntity);
    }

    private BloodDetailsDto getBloodDetailsDto() {
        BloodDetailsDto bloodDetailsDto = new BloodDetailsDto();
        bloodDetailsDto.setBloodType("O+");
        bloodDetailsDto.setDonateBloodTo(new ArrayList<>());
        bloodDetailsDto.setReceiveBloodFrom(new ArrayList<>());
        return bloodDetailsDto;
    }

    private BloodDetailsEntity getBloodDetailEntity() {
        BloodDetailsEntity bloodDetailsEntity = new BloodDetailsEntity();
        bloodDetailsEntity.setBloodType("O+");
        bloodDetailsEntity.setDonateBloodTo(new ArrayList<>());
        bloodDetailsEntity.setReceiveBloodFrom(new ArrayList<>());
        bloodDetailsEntity.setId("testId");
        return bloodDetailsEntity;
    }

    @Test
    void getBloodTypeDetailsTest() throws BloodDetailNotFoundException {
        when(bloodDonorRepository.findByBloodType(anyString())).thenReturn(Optional.of(getBloodDetailEntity()));
        BloodDetailsEntity actualBloodDetailsEntity = bloodDonorService.getBloodTypeDetails("O+", true, true);
        Assertions.assertEquals(getBloodDetailEntity(), actualBloodDetailsEntity);
    }

    @Test
    void getBloodTypeDetailsExceptionTest() {
        when(bloodDonorRepository.findByBloodType(anyString())).thenReturn(Optional.empty());
        Assertions.assertThrows(BloodDetailNotFoundException.class, () ->
                bloodDonorService.getBloodTypeDetails("O+", true, true));
    }
}
