package com.example.demo;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SpringBootTest
class DemoApplicationTests {

	@Autowired
	BasicService basicService;

	@Test
	void proxyCheck(){
		log.info("aop class = {}",basicService.getClass());
		assertThat(AopUtils.isAopProxy(basicService)).isTrue();
	}

	@Test
	void txTest(){
		basicService.tx();
		basicService.nontx();
	}

	@TestConfiguration
	static class TxApplyBasicConfig{
		@Bean
		BasicService basicService(){
			return new BasicService();
		}
	}

	@Slf4j
	static class BasicService {

		@Transactional
		public void tx(){
			boolean actualTransactionActive = TransactionSynchronizationManager.isActualTransactionActive();
			log.info("tx active = {}",actualTransactionActive);
		}



		public void nontx(){
			boolean actualTransactionActive = TransactionSynchronizationManager.isActualTransactionActive();
			log.info("tx active = {}",actualTransactionActive);
		}

	}


}
