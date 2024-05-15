package pl.piomin.services.rest.customer.repository;



import pl.piomin.services.rest.customer.model.Customer;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class CustomerRepositoryLocal {

    private List<Customer> customers;
    AtomicLong id;

    public CustomerRepositoryLocal(List<Customer> customers) {
        this.customers = customers;
        this.id = new AtomicLong();
        this.id.set(customers.size());
    }

    public Customer findById(Long id) {
        return customers.stream().filter(it -> it.getId() == id).findFirst().orElseThrow();
    }

    public Customer findByPesel(String pesel) {
        return customers.stream().filter(it -> it.getPesel().equals(pesel)).findFirst().orElseThrow();
    }

    public List<Customer> findAll() {
        return customers;
    }

    public Customer add( String name, String pesel) {
        Customer c = new Customer(id.incrementAndGet(),name,pesel);
        return c;
    }
    public void updateCustomer(Long id, String name, String pesel){
        customers.stream().filter(it -> it.getId().equals(id)).findFirst().ifPresent(it->{
            it.setName(name);
            it.setPesel(pesel);
        });
    }
}
