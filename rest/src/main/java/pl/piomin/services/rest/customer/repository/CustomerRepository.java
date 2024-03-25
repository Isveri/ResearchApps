package pl.piomin.services.rest.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pl.piomin.services.rest.customer.model.Customer;


public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    void deleteByPesel(String pesel);
    @Transactional
    boolean existsCustomerByPesel(String pesel);
}
