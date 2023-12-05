package com.example.demo.isolation;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class IsolationService {

    private final IsolationRepository isolationRepository;

    @Transactional
    public void save(IsolationTestData isolationTestData){
        isolationRepository.save(isolationTestData);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void nonRepeatableRead1(){

        Optional<IsolationTestData> isolationTestData1 = isolationRepository.findById(1L);

        log.info("first = {}",isolationTestData1.get());

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Optional<IsolationTestData> isolationTestData2 = isolationRepository.findById(1L);

        log.info("seconds = {}",isolationTestData2.get());

    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void nonRepeatableRead2(){
        Optional<IsolationTestData> isolationTestData1 = isolationRepository.findById(1L);
        isolationTestData1.get().setName("mutant Hamster");
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void phantomRead1(){
        List<IsolationTestData> all = isolationRepository.findAll();

        log.info("first = {}",all);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        List<IsolationTestData> all2 = isolationRepository.findAll();

        log.info("seconds = {}",all2);

    }




}
