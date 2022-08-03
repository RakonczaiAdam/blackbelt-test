package hu.radam.blackbelttest.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "movie")
public class ChildrensMovie extends Movie{

    public ChildrensMovie(String title){
        super(title, 2);
    }

    public ChildrensMovie(){}

    public double getAmount(int dayRented){
        double amount = 1.5;
        if(dayRented > 3){
            amount += (dayRented - 3) * 1.5;
        }
        return amount;
    }
}
