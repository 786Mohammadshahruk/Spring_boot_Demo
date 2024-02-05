package com.sthumbh.repository;

import com.sthumbh.Entity.BloodDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BloodDonorRepository extends JpaRepository<BloodDetailsEntity,String> {
    Optional<BloodDetailsEntity> findByBloodType(String type);
}
