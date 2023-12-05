package com.example.demo.isolation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface IsolationRepository extends JpaRepository<IsolationTestData,Long> {
}
