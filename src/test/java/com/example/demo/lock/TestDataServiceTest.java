package com.example.demo.lock;

import com.example.demo.order.OrderRepository;
import com.example.demo.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class TestDataServiceTest {

    @Autowired
    TestDataService testDataService;
    @Autowired
    TestDataRepository testDataRepository;

    @Test
    void testMethod() {

        TestData testData1 = new TestData();
        testData1.setName("hamster1");
        testData1.setNumber(1);
        testData1.setDescription("testData1");
        testDataService.save(testData1);

        TestData testData2 = new TestData();
        testData2.setName("hamster2");
        testData2.setNumber(2);
        testData2.setDescription("testData2");
        testDataService.save(testData2);

        ExecutorService executorService = Executors.newFixedThreadPool(5);

        for(int i =0; i<1; i++){
            executorService.submit(()-> testDataService.testMethod1());
        }
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
//        testDataService.testMethod2();
        testDataService.delete(1L);

        Optional<TestData> byId = testDataRepository.findById(1L);
        log.info("제거시간됏냐? = {}",byId.isEmpty());

        log.info("최종시간 = {}", LocalDateTime.now());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}