package com.example.demo.lock;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class TestDataService {

    private final TestDataRepository testDataRepository;

    @Transactional
    public void testMethod1() {
        log.info("testMethod1 start time = {}",LocalDateTime.now());
        TestData testData = testDataRepository.findByName("hamster1");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        log.info("testMethod1 end = {} time = {} ",testData, LocalDateTime.now());
    }

    @Transactional
    public void testMethod2(){
        log.info("testMethod2 start time = {}",LocalDateTime.now());
        TestData testData = testDataRepository.findByName("hamster1");
        log.info("testMethod2 end = {} time = {} ",testData, LocalDateTime.now());
    }

    @Transactional
    public void save(TestData testData){
        log.info("save start time = {}",LocalDateTime.now());
        testDataRepository.save(testData);
        log.info("save = {} time = {} ",testData, LocalDateTime.now());
    }

}
