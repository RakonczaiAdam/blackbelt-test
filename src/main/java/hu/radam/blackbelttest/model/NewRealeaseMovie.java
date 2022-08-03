package hu.radam.blackbelttest.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "movie")
public class NewRealeaseMovie extends Movie{

    public NewRealeaseMovie(String title){
        super(title, 1);
    }

    public NewRealeaseMovie(){}

    public double getAmount(int dayRented){
        double amount = 0;
        amount += dayRented * 3;
        return amount;
    }
}
