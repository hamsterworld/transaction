package com.example.demo.apply;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Slf4j
@SpringBootTest
public class InternalCallV2Test {

    @Autowired CallService callService;

    @Test
    void printProxy(){
        log.info("callService class = {}",callService.getClass());
    }

    @Test
    void externalCallV2(){
        callService.external();
    }

    @TestConfiguration
    static class InternalCallV1TestConfig{

        @Bean
        CallService callService(InternalService internalService){
            return new CallService(internalService);
        }

        @Bean
        InternalService internalService(){
            return new InternalService();
        }

    }

    /**
     * 아래처럼 external 에서는 transaction 이 필요없고 internal method 에서는 transaction 이 필요한 상황이라고 가정해보자.
     */
    @Slf4j
    @RequiredArgsConstructor
    static class CallService {

        private final InternalService internalService;

        public void external(){
            log.info("call external");
            printTransactionInfo();
            internalService.internal();
        }


        private void printTransactionInfo(){
            boolean transactionActive = TransactionSynchronizationManager.isActualTransactionActive();
            log.info("transaction active = {}",transactionActive);
            boolean readOnly = TransactionSynchronizationManager.isCurrentTransactionReadOnly();
            log.info("transaction readOnly = {}",readOnly);
        }

    }

    @Slf4j
    static class InternalService{
        @Transactional
        public void internal(){
            log.info("call internal");
            printTransactionInfo();
        }

        private void printTransactionInfo(){
            boolean transactionActive = TransactionSynchronizationManager.isActualTransactionActive();
            log.info("transaction active = {}",transactionActive);
            boolean readOnly = TransactionSynchronizationManager.isCurrentTransactionReadOnly();
            log.info("transaction readOnly = {}",readOnly);
        }

    }

}
