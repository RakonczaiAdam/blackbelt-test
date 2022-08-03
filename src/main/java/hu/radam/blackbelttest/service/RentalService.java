package hu.radam.blackbelttest.service;

import hu.radam.blackbelttest.model.Movie;
import hu.radam.blackbelttest.model.Rental;
import hu.radam.blackbelttest.repository.RentalRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *  Fetch the Rentals and calculate the amounts
 */
@Service
public class RentalService {

    private RentalRepo rentalRepo;

    @Autowired
    private void setRentalRepo(RentalRepo rentalRepo){
        this.rentalRepo = rentalRepo;
    }

    /**
     * Get a Rental based on Rental id.
     * @param id The id of the Rental.
     * @return the Rental based on the id.
     */
    public Rental findById(int id){
        try{
            return rentalRepo.findById(id).get();
        }catch(Exception e){
            System.err.println(e.getMessage());
            return null;
        }
    }

    /**
     * Calculates the amount of a Rental.
     * @param rental The rental for the calculation.
     * @return the calculated amount.
     */
    public double statement(Rental rental){
        try{
            double thisAmount = 0;
            switch (rental.getMovie().getPriceCode()) {
                case Movie.REGULAR:
                    thisAmount += 2;
                    if (rental.getDaysRented() > 2) {
                        thisAmount += (rental.getDaysRented() - 2) * 1.5;
                    }
                    break;
                case Movie.NEW_RELEASE:
                    thisAmount += rental.getDaysRented() * 3;
                    break;
                case Movie.CHILDRENS:
                    thisAmount += 1.5;
                    if (rental.getDaysRented() > 3) {
                        thisAmount += (rental.getDaysRented() - 3) * 1.5;
                    }
                    break;
            }
            return thisAmount;
        }catch(Exception e){
            System.err.println(e.getMessage());
            return 0;
        }
    }

}
