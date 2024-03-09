package io.gatling.kafka.demo.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
public
class Customer {
    private  Long id;
    private String pesel;
    private String name;
}
