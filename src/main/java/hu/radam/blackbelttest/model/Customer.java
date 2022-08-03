package hu.radam.blackbelttest.model;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Vector;

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
    //private Vector rentals = new Vector();
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

    /* public String statement() {
        double totalAmount = 0;
        int frequentRenterPoints = 0;
        Enumeration rentals = this.rentals.elements();
        String result = "Rental Record for " + getName() + "\n";
        while (rentals.hasMoreElements()) {
            double thisAmount = 0;
            Rental each = (Rental) rentals.nextElement();
            switch (each.getMovie().getPriceCode()) {
                case Movie.REGULAR:
                    thisAmount += 2;
                    if (each.getDaysRented() > 2) {
                        thisAmount += (each.getDaysRented() - 2) * 1.5;
                    }
                    break;
                case Movie.NEW_RELEASE:
                    thisAmount += each.getDaysRented() * 3;
                    break;
                case Movie.CHILDRENS:
                    thisAmount += 1.5;
                    if (each.getDaysRented() > 3) {
                        thisAmount += (each.getDaysRented() - 3) * 1.5;
                    }
                    break;
            }

            // add frequent renter points
            frequentRenterPoints++;

            // add bonus for a two day new release rental
            if ((each.getMovie().getPriceCode() == Movie.NEW_RELEASE) &&
                    (each.getDaysRented() > 1)) {
                frequentRenterPoints++;

            }

            // show figures for this rental
            result += "\t" + each.getMovie().getTitle() +
                    "\t" + String.valueOf(thisAmount) + "\n";
            totalAmount += thisAmount;
        }

        // add footer lines
        result += "Amount owed is " + String.valueOf(totalAmount) + "\n";
        result += "You earned " + String.valueOf(frequentRenterPoints) +
                " frequent renter points";
        return result;
    }*/
}
