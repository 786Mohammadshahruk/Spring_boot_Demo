package com.sthumbh.service;

import com.sthumbh.Entity.BloodDetailsEntity;
import com.sthumbh.repository.BloodDonorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class BloodDonorScheduler {
    @Autowired
    private BloodDonorRepository bloodDonorRepository;

    int x = 1;

    @Scheduled(cron = "0 * * ? * *")
    public void doCheckBloodDonorDetail() {
        List<BloodDetailsEntity> bloodDetailsEntities = bloodDonorRepository.findAll();
        System.out.println(x++);
        for (BloodDetailsEntity b : bloodDetailsEntities) {

            log.info(" Blood Details : {}", b);
        }
    }
}
