package com.sthumbh.repository;

import com.sthumbh.Entity.EmployeeEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class EmployeeRepository {
    public void save(EmployeeEntity employee) {
        log.info("Employee : {}", employee);
    }
}
