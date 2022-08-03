package hu.radam.blackbelttest.model;

import javax.persistence.*;

/**
 * Movie entity
 * <p>
 *     Hibernate POJO class. Stores the Movie data.
 * </p>
 */
@Entity
@Inheritance
public class Movie {

    public static final int CHILDRENS = 2;

    public static final int REGULAR = 0;

    public static final int NEW_RELEASE = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String title;
    private int priceCode;

    public Movie(){}
    public Movie(String title, int priceCode) {
        this.title = title;
        this.priceCode = priceCode;
    }

    /**
     *
     * @return the price code of the Movie
     */
    public int getPriceCode() {
        return priceCode;
    }

    /**
     *
     * @param arg The price code you want to set as a new value.
     */
    public void setPriceCode(int arg) {
        priceCode = arg;
    }

    /**
     *
     * @return the title of the Movie.
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @return the id of the Movie.
     */
    public Integer getId() {
        return id;
    }

    public double getAmount(int dayRented){
        return 0;
    }
}
