package com.example.kafkatest.model;


import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public
class Customer {
    private  Long id;
    private String pesel;
    private String name;
}
