package hu.radam.blackbelttest.repository;

import org.springframework.data.repository.CrudRepository;
import hu.radam.blackbelttest.model.Rental;

public interface RentalRepo extends CrudRepository<Rental, Integer>{

}
