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

    @Query(value = "From UserEntity user WHERE user.mobileNumber =:mobileNumber")
    UserEntity findEntityMobileNumber(@Param("mobileNumber") String name);

    void removeByName(String name);

    List<UserEntity> findByPriceGreaterThan(long l);

    @Query(value = "select * from user_table where user_id = :id And address = :address AND mobile_number = :mobile_number"
            , nativeQuery = true)
    UserEntity getUserByNativeQuery(@Param("id") long id,
                                    @Param("mobile_number") String mobileNumber,
                                    @Param("address") String address);

    // 4 Delete Api
    // List<UserEntity>  from JPQL


}
