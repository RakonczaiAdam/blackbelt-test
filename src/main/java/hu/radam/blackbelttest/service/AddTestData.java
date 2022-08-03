package hu.radam.blackbelttest.service;

import hu.radam.blackbelttest.model.*;
import hu.radam.blackbelttest.repository.CustomerRepo;
import hu.radam.blackbelttest.repository.MovieRepo;
import hu.radam.blackbelttest.repository.RentalRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * This class implements the CommandLineRunner interface, to upload the database with test data.
 * <p>
 * We make 144 Rentals, starts with the id 13.
 * </p>
 */
@Component
public class AddTestData implements CommandLineRunner {

    private CustomerRepo customerRepo;
    private MovieRepo movieRepo;
    private RentalRepo rentalRepo;

    @Autowired
    private void setRepos(CustomerRepo customerRepo, MovieRepo movieRepo, RentalRepo rentalRepo){
        this.customerRepo = customerRepo;
        this.movieRepo = movieRepo;
        this.rentalRepo = rentalRepo;
    }

    @Override
    public void run(String... args) throws Exception {
        try{
            saveMovies();
            saveCustomer();
            saveRental();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Creates 6 Movie instances
     */
    private void saveMovies(){
        List<Movie> movies = new ArrayList<Movie>();

        movies.add(new ChildrensMovie("Harry Potter"));
        movies.add(new RegularMovie("Lord Of The Rings"));
        movies.add(new RegularMovie("Kill Bill"));
        movies.add(new NewRealeaseMovie("The Gentlemen"));
        movies.add(new NewRealeaseMovie("Interstellar"));
        movies.add(new ChildrensMovie("Shrek"));
       /* movies.add(new Movie("Harry Potter", 2));
        movies.add(new Movie("Lord Of The Rings", 0));
        movies.add(new Movie("Kill Bill", 0));
        movies.add(new Movie("The Gentlemen", 1));
        movies.add(new Movie("Interstellar", 1));
        movies.add(new Movie("Shrek", 2));*/
        movies.forEach((Movie movie) -> {
            Movie added = movieRepo.save(movie);
            System.out.println(added.getTitle());
        });
    }

    /**
     * Creates 6 Customer instances.
     */
    private void saveCustomer(){
        List<Customer> customers = new ArrayList<Customer>();
        customers.add(new Customer("John Wick"));
        customers.add(new Customer("Woodie Herelson"));
        customers.add(new Customer("Emma Watson"));
        customers.add(new Customer("Nicolas Cage"));
        customers.add(new Customer("Scarlet Johanson"));
        customers.add(new Customer("Kovács István"));
        customers.forEach((Customer customer) -> {
            Customer added = customerRepo.save(customer);
            System.out.println(added.getName());
        });
    }

    /**
     * Creates 144 Rental instances.
     */
    private void saveRental(){
        List<Movie> movies = movieRepo.findAll();
        List<Customer> customers = customerRepo.findAll();
        movies.forEach((Movie movie)->{
            for(int i = 1; i <= 24; i++){
                Rental rental = rentalRepo.save(new Rental(movie, i, customers.get(i%6)));
                //customers.get(i%6).addRental(rental);
                System.out.println(rental.getId()+" - days rented: "+rental.getDaysRented()+", movie: "+rental.getMovie().getTitle());
            }
        });

    }
}
