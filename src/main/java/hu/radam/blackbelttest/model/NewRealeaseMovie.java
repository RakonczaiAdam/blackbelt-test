package hu.radam.blackbelttest.model;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Subclass of movie. The getAmount function has a specific logic.
 */
@Entity
@Table(name = "movie")
public class NewRealeaseMovie extends Movie{

    public NewRealeaseMovie(String title){
        super(title);
    }

    public NewRealeaseMovie(){}

    /**
     *
     * @param dayRented The days of the rental.
     * @return the amount of a Rental based on the days the movie rented.
     */
    public double getAmount(int dayRented){
        double amount = 0;
        amount += dayRented * 3;
        return amount;
    }
}
