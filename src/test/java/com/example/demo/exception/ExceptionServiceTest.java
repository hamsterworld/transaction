package com.example.demo.exception;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class ExceptionServiceTest {

    @Autowired
    ExceptionService exceptionService;


    @Test
    void save() {
        try {
            exceptionService.save();
        } catch (Exception e) {
            exceptionService.findUser();
        }
    }

    @Test
    void findUser() {
        try {
            exceptionService.notSave();
        } catch (Exception e) {
            exceptionService.findUser();
        }
    }
}