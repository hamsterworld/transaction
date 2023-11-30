package com.example.demo.lock;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Entity
@Getter
@Setter
public class TestData {

    @Id
    @GeneratedValue
    private long id;
    private String name;
    private int number;
    private String description;

}
