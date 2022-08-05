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
/*
    public static final int CHILDRENS = 2;

    public static final int REGULAR = 0;

    public static final int NEW_RELEASE = 1;*/

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String title;

    public Movie(){}
    public Movie(String title) {
        this.title = title;
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

    /**
     * The Movie subclasses override this method
     * @param dayRented The days of the movie Rental.
     * @return the amount of a Rental.
     */
    public double getAmount(int dayRented){
        return 0;
    }
}
