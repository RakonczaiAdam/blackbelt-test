package hu.radam.blackbelttest.repository;

import hu.radam.blackbelttest.model.Movie;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MovieRepo extends CrudRepository<Movie, Integer> {
    List<Movie> findAll();
}
