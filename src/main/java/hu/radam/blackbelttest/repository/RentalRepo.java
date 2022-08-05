package hu.radam.blackbelttest.repository;

import hu.radam.blackbelttest.model.Customer;
import org.springframework.data.repository.CrudRepository;
import hu.radam.blackbelttest.model.Rental;

import java.util.List;

public interface RentalRepo extends CrudRepository<Rental, Integer>{

    List<Rental> findByCustomer(Customer c);

}
