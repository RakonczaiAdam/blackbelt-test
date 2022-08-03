package hu.radam.blackbelttest.model;

import javax.persistence.*;

/**
 * Rental entity
 * <p>
 *     Hibernate POJO class. Stores the data of one movie Rental.
 * </p>
 */
@Entity
public class Rental {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "movie_id", referencedColumnName = "id")
    private Movie movie;
    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;
    private int daysRented;

    public Rental(){}
    public Rental(Movie movie, int daysRented, Customer customer) {
        this.movie = movie;
        this.daysRented = daysRented;
        this.customer = customer;
    }

    /**
     *
     * @return the id of the Rental.
     */
    public Integer getId() {
        return id;
    }

    /**
     *
     * @return the amount of rental days.
     */
    public int getDaysRented() {
        return daysRented;
    }

    /**
     *
     * @return the Movie of the Rental.
     */
    public Movie getMovie() {
        return movie;
    }

    /**
     *
     * @return the Customer of the Rental.
     */
    public Customer getCustomer(){
        return  customer;
    }
}

