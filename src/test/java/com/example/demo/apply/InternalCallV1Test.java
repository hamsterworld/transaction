package com.example.demo.apply;

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
public class InternalCallV1Test {

    @Autowired CallService callService;

    @Test
    void printProxy(){
        log.info("callService class = {}",callService.getClass());
    }

    @Test
    void internalCall(){
        callService.internal();
    }

    @Test
    void externalCall(){
        callService.external();
    }

    @TestConfiguration
    static class InternalCallV1TestConfig{

        @Bean
        CallService callService(){
            return new CallService();
        }

    }

    /**
     * 아래처럼 external 에서는 transaction 이 필요없고 internal method 에서는 transaction 이 필요한 상황이라고 가정해보자.
     */
    static class CallService {

        public void external(){
            log.info("call external");
            printTransactionInfo();
            internal();
        }

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
