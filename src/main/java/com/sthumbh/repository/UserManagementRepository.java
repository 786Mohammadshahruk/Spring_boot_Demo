package com.sthumbh.repository;

import com.sthumbh.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserManagementRepository extends JpaRepository<UserEntity, Long> {
    Optional<List<UserEntity>> findByLastName(String name);

    //JPQL
    @Query(value = "From UserEntity user WHERE user.mobileNumber =:mobileNumber")
    UserEntity findEntityMobileNumber(@Param("mobileNumber") String name);

    // 4 Delete Api
    // List<UserEntity>  from JPQL

}
