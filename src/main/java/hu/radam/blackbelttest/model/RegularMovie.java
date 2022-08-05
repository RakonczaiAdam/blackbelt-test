package hu.radam.blackbelttest.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Subclass of movie. The getAmount function has a specific logic.
 */
@Entity
@Table(name = "movie")
public class RegularMovie extends Movie{

    public RegularMovie(String title){
        super(title);
    }

    public RegularMovie(){}

    /**
     *
     * @param dayRented The days of the rental.
     * @return the amount of a Rental based on the days the movie rented.
     */
    public double getAmount(int dayRented){
        double amount = 2;
        if(dayRented > 2){
            amount += (dayRented - 2) * 1.5;
        }
        return amount;
    }
}
