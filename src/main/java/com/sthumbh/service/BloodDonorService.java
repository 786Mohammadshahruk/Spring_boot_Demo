package com.sthumbh.service;

import com.sthumbh.Entity.BloodDetailsEntity;
import com.sthumbh.dto.BloodDetailsDto;
import com.sthumbh.exception.handler.BloodDetailNotFoundException;
import com.sthumbh.repository.BloodDonorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class BloodDonorService {
    @Autowired
    private BloodDonorRepository bloodDonorRepository;
    @Autowired
    private BloodDonorScheduler bloodDonorScheduler;

    public BloodDetailsEntity createBloodTypeDetails(BloodDetailsDto bloodDetailsDto) {
        String id = UUID.randomUUID().toString();
        BloodDetailsEntity bloodDetailsEntity = new BloodDetailsEntity();
        bloodDetailsEntity.setId(id);
        bloodDetailsEntity.setBloodType(bloodDetailsDto.getBloodType());
        bloodDetailsEntity.setDonateBloodTo(bloodDetailsDto.getDonateBloodTo());
        bloodDetailsEntity.setReceiveBloodFrom(bloodDetailsDto.getReceiveBloodFrom());
        bloodDonorScheduler.checkRecords();
        return bloodDonorRepository.save(bloodDetailsEntity);
    }


    public BloodDetailsEntity getBloodTypeDetails(String type, boolean isDonateBloodTo, boolean isDonateBloodTo1) throws BloodDetailNotFoundException {
        Optional<BloodDetailsEntity> optionalBloodDetailsEntity = bloodDonorRepository.findByBloodType(type);
        BloodDetailsEntity bloodDetailsEntity = null;

        if (!optionalBloodDetailsEntity.isPresent()) {
            throw new BloodDetailNotFoundException("Blood Detail Not Found");
        }
        bloodDetailsEntity = optionalBloodDetailsEntity.get();
        if (!isDonateBloodTo) {
            bloodDetailsEntity.setDonateBloodTo(null);
        }
        if (!isDonateBloodTo1) {
            bloodDetailsEntity.setReceiveBloodFrom(null);
        }

        return bloodDetailsEntity;
    }

}
