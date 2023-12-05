package com.example.demo.isolation;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
@SpringBootTest
class IsolationTestService {

    @Autowired
    IsolationService isolationService;

    @BeforeEach
    void beforeEach() {
        IsolationTestData data1 = new IsolationTestData();
        data1.setName("hamster1");
        IsolationTestData data2 = new IsolationTestData();
        data2.setName("hamster2");
        isolationService.save(data1);
        isolationService.save(data2);
    }

    // non-repeatableRead 가 안발생하는건 JPA 특징때문에 그런듯
    // 이미 가지고있는 entity 여서 또 select 를 안날려서 그런것같다.
    @Test
    void readUncommitted1() throws InterruptedException {
        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        threadPool.submit(() -> isolationService.nonRepeatableRead1());
        Thread.sleep(100);
        isolationService.nonRepeatableRead2();
        Thread.sleep(2000);
    }

    @Test
    void phantomRead()throws InterruptedException {
        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        threadPool.submit(() -> isolationService.phantomRead1());
        IsolationTestData data3 = new IsolationTestData();
        data3.setName("hamster3");
        Thread.sleep(100);
        isolationService.save(data3);
        Thread.sleep(2000);
    }

}