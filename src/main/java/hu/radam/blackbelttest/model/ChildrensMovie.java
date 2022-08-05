package hu.radam.blackbelttest.model;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Subclass of movie. The getAmount function has a specific logic.
 */
@Entity
@Table(name = "movie")
public class ChildrensMovie extends Movie{

    public ChildrensMovie(String title){
        super(title);
    }

    public ChildrensMovie(){}

    /**
     *
     * @param dayRented The days of the rental.
     * @return the amount of a Rental based on the days the movie rented.
     */
    public double getAmount(int dayRented){
        double amount = 1.5;
        if(dayRented > 3){
            amount += (dayRented - 3) * 1.5;
        }
        return amount;
    }
}
