package hu.radam.blackbelttest.model;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Customer entity
 * <p>
 *     Hibernate POJO class. Stores the Customer data
 * </p>
 */
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    @OneToMany
    private List<Rental> rentals = new ArrayList<>();

    public Customer(){}
    public Customer(String name) {
        this.name = name;
    }

    /**
     *
     * @param arg type: Rental. You can add a Rental to a Customer.
     */
    public void addRental(Rental arg) {
        rentals.add(arg);
    }

    /**
     *
     * @return the name of the Customer
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return the id of the Customer.
     */
    public Integer getId() {
        return id;
    }

}
