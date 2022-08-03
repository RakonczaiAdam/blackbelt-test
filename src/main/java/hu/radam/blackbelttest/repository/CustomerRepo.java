package hu.radam.blackbelttest.repository;

import hu.radam.blackbelttest.model.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CustomerRepo extends CrudRepository<Customer, Integer> {
    List<Customer> findAll();
}
