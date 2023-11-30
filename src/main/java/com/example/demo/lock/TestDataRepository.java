package com.example.demo.lock;

import jakarta.persistence.LockModeType;
import jakarta.persistence.QueryHint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;



public interface TestDataRepository extends JpaRepository<TestData,Long> {

//    @QueryHints({@QueryHint(name="jakarta.persistence.lock.timeout",value = "50000")})
//    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Lock(LockModeType.PESSIMISTIC_READ)
    TestData findByName(String name);

    TestData deleteByName(String name);

}
