package hu.radam.blackbelttest;

import hu.radam.blackbelttest.model.*;
import hu.radam.blackbelttest.repository.CustomerRepo;
import hu.radam.blackbelttest.repository.MovieRepo;
import hu.radam.blackbelttest.repository.RentalRepo;
import hu.radam.blackbelttest.service.RentalService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * Amount calculation and frequent point tests.
 */
@SpringBootTest
class BlackbeltTestApplicationTests {

	private RentalRepo rentalRepo;
	private MovieRepo movieRepo;
	private CustomerRepo customerRepo;
	private RentalService rentalService;
	private Customer customer;

	@Autowired
	private void setRentalService(RentalService rentalService){
		this.rentalService = rentalService;
	}

	@Autowired
	private void setRepos(RentalRepo rentalRepo, MovieRepo movieRepo, CustomerRepo customerRepo){
		customer = customerRepo.save(new Customer("Rakonczai Adam"));
		this.rentalRepo = rentalRepo;
		this.movieRepo = movieRepo;
		this.customerRepo = customerRepo;
	}


	/* Original logic
		thisAmount += 1.5;
		if (each.getDaysRented() > 3) {
			thisAmount += (each.getDaysRented() - 3) * 1.5;
		}
	 */

	/**
	 * Test: Child movie rental for 1 day
	 */
	@Test
	void childrenAmountCalculate1(){
		Movie movie = movieRepo.save(new ChildrensMovie("Children1"));
		Rental rental = rentalRepo.save(new Rental(movie, 1, customer));
		Assertions.assertEquals(1.5, rental.getMovie().getAmount(rental.getDaysRented()), "Bad calculation child movie, days 1");
	}

	/**
	 * Test: Child movie rental for 4 day
	 */
	@Test
	void childrenAmountCalculate2(){
		Movie movie = new ChildrensMovie("Children1");
		Rental rental = new Rental(movie, 4, customer);
		movieRepo.save(movie);
		rentalRepo.save(rental);
		Assertions.assertEquals(3, rental.getMovie().getAmount(rental.getDaysRented()), "Bad calculation child movie, days 4");
	}

	/**
	 * Test: Child movie rental for 10 day
	 */
	@Test
	void childrenAmountCalculate4(){
		Movie movie = new ChildrensMovie("Children1");
		Rental rental = new Rental(movie, 10, customer);
		movieRepo.save(movie);
		rentalRepo.save(rental);
		Assertions.assertEquals(12, rental.getMovie().getAmount(rental.getDaysRented()), "Bad calculation child movie, days 10");
	}


	/* Original logic
		thisAmount += each.getDaysRented() * 3;
	 */

	/**
	 * Test: New release movie rental for 1 day
	 */
	@Test
	void newReleaseAmountCalculate1(){
		Movie movie = new NewRealeaseMovie("New Release");
		Rental rental = new Rental(movie, 1, customer);
		movieRepo.save(movie);
		rentalRepo.save(rental);
		Assertions.assertEquals(3, rental.getMovie().getAmount(rental.getDaysRented()), "Bad calculation new release movie, days 1");
	}

	/**
	 * Test: New release movie rental for 10 day
	 */
	@Test
	void newReleaseAmountCalculate2(){
		Movie movie = new NewRealeaseMovie("New Release");
		Rental rental = new Rental(movie, 10, customer);
		movieRepo.save(movie);
		rentalRepo.save(rental);
		Assertions.assertEquals(30, rental.getMovie().getAmount(rental.getDaysRented()), "Bad calculation new release movie, days 10");
	}

	/* Original logic
	thisAmount += 2;
	if (each.getDaysRented() > 2) {
		thisAmount += (each.getDaysRented() - 2) * 1.5;
	}*/

	/**
	 * Test: Regular movie rental for 1 day
	 */
	@Test
	void regularAmountCalculate1(){
		Movie movie = new RegularMovie("Regular");
		Rental rental = new Rental(movie, 1, customer);
		movieRepo.save(movie);
		rentalRepo.save(rental);
		Assertions.assertEquals(2, rental.getMovie().getAmount(rental.getDaysRented()), "Bad calculation regular movie, days 1");
	}

	/**
	 * Test: Regular movie rental for 3 day
	 */
	@Test
	void regularAmountCalculate2(){
		Movie movie = new RegularMovie("Regular");
		Rental rental = new Rental(movie, 3, customer);
		movieRepo.save(movie);
		rentalRepo.save(rental);
		Assertions.assertEquals(3.5, rental.getMovie().getAmount(rental.getDaysRented()),  "Bad calculation regular movie, days 3");
	}

	/**
	 * Test: Regular movie rental for 10 day
	 */
	@Test
	void regularAmountCalculate3(){
		Movie movie = new RegularMovie("Regular");
		Rental rental = new Rental(movie, 10, customer);
		movieRepo.save(movie);
		rentalRepo.save(rental);
		Assertions.assertEquals(14, rental.getMovie().getAmount(rental.getDaysRented()),"Bad calculation regular movie, days 10");
	}

	/**
	 * Test: Customer total amount and frequent points test.
	 */
	@Test
	void customerTest(){
		Customer c = customerRepo.save(new Customer("Jack Sparrow"));

		Movie m1 = new RegularMovie("Regular");
		Rental r1 = new Rental(m1, 10, c);	// 14
		movieRepo.save(m1);
		rentalRepo.save(r1);
		Movie m2 = new RegularMovie("Regular");
		Rental r2 = new Rental(m2, 3, c);	// 3.5
		movieRepo.save(m2);
		rentalRepo.save(r2);
		Movie m3 = new NewRealeaseMovie("New Release");
		Rental r3 = new Rental(m3, 10, c);	// 30
		movieRepo.save(m3);
		rentalRepo.save(r3);
		Movie m4 = movieRepo.save(new ChildrensMovie("Children1"));
		Rental r4 = rentalRepo.save(new Rental(m4, 1, c));	// 1.5

		List<Rental> rentals = rentalService.findByCustomer(c.getId());

		Double totalAmount = rentalService.customerStatement(rentals);

		Assertions.assertEquals(49, totalAmount, "Bad total amount");
		Assertions.assertEquals(5, rentalService.frequentRenterPoints(rentals), "Bad frequent rental points.");
	}


}
