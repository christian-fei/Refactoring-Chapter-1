package bad.robot.refactoring.chapter1;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CustomerTest {

    public static final String CUSTOMER_NAME = "Ferdinand";
    public static final String MOVIE_NAME = "Avatar";
    private Customer customer;

    @Before
    public void before() {
        customer = new Customer(CUSTOMER_NAME);
    }

    @Test
    public void zeroAmountForEmptyStatement() throws Exception {
        String statement = customer.statement();

        assertEquals(statement, "Rental record for Ferdinand\n" +
                "Amount owed is 0.0\n" +
                "You earned 0 frequent renter points");
    }

    @Test
    public void regular_movie_rental() throws Exception {
        int daysRented = 1;
        customer.addRental( new Rental( new Movie(MOVIE_NAME, Movie.REGULAR), daysRented));

        String statement = customer.statement();

        double totalAmount = 2.0;
        int frequentRentalPoints = 1;

        assertEquals(statement, statementFor(CUSTOMER_NAME, MOVIE_NAME, totalAmount, frequentRentalPoints));
    }

    @Test
    public void regular_movie_rental_for_more_than_two_days() throws Exception {
        int daysRented = 3;
        customer.addRental( new Rental( new Movie(MOVIE_NAME, Movie.REGULAR), daysRented));

        String statement = customer.statement();

        double totalAmount = 3.5;
        int frequentRentalPoints = 1;

        assertEquals(statement, statementFor(CUSTOMER_NAME, MOVIE_NAME, totalAmount, frequentRentalPoints));
    }

    @Test
    public void new_release_movie_rental() throws Exception {
        int daysRented = 1;
        customer.addRental( new Rental( new Movie(MOVIE_NAME, Movie.NEW_RELEASE), daysRented));

        String statement = customer.statement();


        double totalAmount = 3.0;
        int frequentRentalPoints = 1;

        assertEquals(statement, statementFor(CUSTOMER_NAME, MOVIE_NAME, totalAmount, frequentRentalPoints));
    }

    @Test
    public void new_release_movie_rented_for_more_than_one_day() throws Exception {
        int daysRented = 2;
        customer.addRental( new Rental( new Movie(MOVIE_NAME, Movie.NEW_RELEASE), daysRented));

        String statement = customer.statement();


        double totalAmount = 6.0;
        int frequentRentalPoints = 2;

        assertEquals(statement, statementFor(CUSTOMER_NAME, MOVIE_NAME, totalAmount, frequentRentalPoints));
    }

    private String statementFor( String customerName, String movieName, double totalAmount, int frequentRentalPoints ) {

        return "Rental record for " + customerName + "\n" +
                "\t" + movieName + "\t" + totalAmount + "\n" +
                "Amount owed is " + totalAmount + "\n" +
                "You earned " + frequentRentalPoints + " frequent renter points";
    }


}
