package com.example.demo.isolation;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class IsolationTestData {

    @Id
    @GeneratedValue
    private long id;

    private String name;

}
