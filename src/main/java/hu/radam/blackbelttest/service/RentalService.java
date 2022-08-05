package hu.radam.blackbelttest.service;

import hu.radam.blackbelttest.model.*;
import hu.radam.blackbelttest.repository.CustomerRepo;
import hu.radam.blackbelttest.repository.RentalRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 *  Fetch the Rentals and calculate the amounts
 */
@Service
public class RentalService {

    private RentalRepo rentalRepo;
    private CustomerRepo customerRepo;
    private double customerAmount;

    @Autowired
    private void setRepos(RentalRepo rentalRepo, CustomerRepo customerRepo){
        this.rentalRepo = rentalRepo;
        this.customerRepo = customerRepo;
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
            e.printStackTrace();
            return null;
        }
    }

    /**
     *
     * @param id the id of the wanted Customer.
     * @return the Customer based on the id param.
     */
    public Customer findCustomerById(int id){
        try{
            return customerRepo.findById(id).get();
        }catch(Exception e){
            System.err.println(e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    /**
     *
     * @param rentals List of Rentals.
     * @return the response object, witch contains a list of rentals and the amounts of the rentals.
     */
    public List<RentalHelper> statement(List<Rental> rentals){
        List<RentalHelper> responseObject = new ArrayList<>();
        try{
            for (Rental rental :
                    rentals) {
                RentalHelper rentalHelper = new RentalHelper(rental, rental.getMovie().getAmount(rental.getDaysRented()));
                responseObject.add(rentalHelper);
            }
            return responseObject;
        }catch(Exception e){
            System.err.println(e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    /**
     *
     * @param rentals List of Rentals.
     * @return the total amount of a list of Rentals.
     */
    public double customerStatement(List<Rental> rentals){
        try{
            customerAmount = 0;
            for (Rental rental :
                    rentals) {
                customerAmount += rental.getMovie().getAmount(rental.getDaysRented());
            }
            return customerAmount;
        }catch(Exception e){
            System.err.println(e.getMessage());
            e.printStackTrace();
            return 0;
        }
    }

    /**
     *
     * @param customerId id of a Customer.
     * @return a list of Rentals based on a customer id.
     */
    public List<Rental> findByCustomer(Integer customerId){
        try{
            Customer customer = findCustomerById(customerId);
            List<Rental> rentals = rentalRepo.findByCustomer(customer);
            return rentals;
        }catch (Exception e){
            System.err.println(e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    /**
     *
     * @param rentals list of Rentals.
     * @return the amount of frequent points based on a list of Rentals.
     */
    public Integer frequentRenterPoints(List<Rental> rentals){
        Integer points = 0;
        for (Rental rental :
                rentals) {
            points++;
            if (rental.getMovie() instanceof NewRealeaseMovie && rental.getDaysRented() > 1) {
                points++;
            }
            }
        return points;
    }

}
