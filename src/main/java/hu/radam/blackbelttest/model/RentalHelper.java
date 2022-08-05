package hu.radam.blackbelttest.model;

import org.springframework.stereotype.Component;

/**
 * This class helps to iterate through the data in the template.
 */
@Component
public class RentalHelper {

    private Rental rental;
    private Double amount;

    public  RentalHelper(){}

    public RentalHelper(Rental rental, Double amount){
        this.rental = rental;
        this.amount = amount;
    }

    /**
     *
     * @return the Rental of the response object.
     */
    public Rental getRental() {
        return rental;
    }

    /**
     *
     * @param rental The Rental what you want to set to the object.
     */
    public void setRental(Rental rental) {
        this.rental = rental;
    }

    /**
     *
     * @return the amount of the response object.
     */
    public Double getAmount() {
        return amount;
    }

    /**
     *
     * @param amount The amount what you want to set to the object.
     */
    public void setAmount(Double amount) {
        this.amount = amount;
    }

}
