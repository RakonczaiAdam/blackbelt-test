package hu.radam.blackbelttest.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "movie")
public class RegularMovie extends Movie{

    public RegularMovie(String title){
        super(title, 0);
    }

    public RegularMovie(){}

    public double getAmount(int dayRented){
        double amount = 2;
        if(dayRented > 2){
            amount += (dayRented - 2) * 1.5;
        }
        return amount;
    }
}
