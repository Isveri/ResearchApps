package com.example.websocket.repository;

import com.example.websocket.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.example.websocket.model.Customer;

import java.util.Optional;


public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    void deleteByPesel(String pesel);
    @Transactional
    boolean existsCustomerByPesel(String pesel);

    Optional<Customer> findByPesel(String pesel);
}
