package com.example.demo;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@SpringBootTest
public class InitTransactionTest {

    @Autowired Hello hello;

    @Test
    void go(){
        //초기화 코드는 스프링이 초기화 시점에 호출한다.
    }

    @TestConfiguration
    static class InitTransactionTestConfig{
        @Bean
        Hello hello(){
            return new Hello();
        }
    }

    @Slf4j
    static class Hello {

        @PostConstruct
        @Transactional
        public void initV1(){
            boolean transactionActive = TransactionSynchronizationManager.isActualTransactionActive();
            log.info("transaction @PostConstruct active = {}",transactionActive);
        }

        @EventListener(ApplicationReadyEvent.class) // 스프링이 완전히 다 뜨면 시작해준다.
        @Transactional
        public void initV2(){
            boolean transactionActive = TransactionSynchronizationManager.isActualTransactionActive();
            log.info("transaction @EventListener(ApplicationReadyEvent.class) active = {}",transactionActive);
        }


    }

}
