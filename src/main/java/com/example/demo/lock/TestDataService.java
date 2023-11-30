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
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        log.info("testMethod1 end = {} time = {} ",testData, LocalDateTime.now());
    }

    @Transactional
    public void testMethod2(){
        log.info("testMethod2 start time = {}",LocalDateTime.now());
        TestData testData = testDataRepository.findByName("hamster1");
        log.info("안녕안녕");
        log.info("안녕안녕");
        log.info("안녕안녕");
        log.info("testMethod2 end = {} time = {} ",testData, LocalDateTime.now());
    }

    @Transactional
    public void save(TestData testData){
        log.info("save start time = {}",LocalDateTime.now());
        TestData save = testDataRepository.save(testData);
        log.info("save = {} time = {} ",save, LocalDateTime.now());
    }

    @Transactional
    public void update(){

    }

//    @Transactional
    public void delete(long id){
        log.info("delete start");
        testDataRepository.deleteById(id);
        log.info("제거제거제거");
        log.info("제거제거제거");
        log.info("제거제거제거");
        log.info("제거제거제거");
        log.info("delete end");
    }


}
