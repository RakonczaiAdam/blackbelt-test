package hu.radam.blackbelttest.controller;

import hu.radam.blackbelttest.model.Rental;
import hu.radam.blackbelttest.model.RentalHelper;
import hu.radam.blackbelttest.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Rental receipt controller
 * <p>
 *     Two endpoint is implemented here. You can make a request with the signature of
 *     {API_URL}/receipt/rental/{rentalId}, or {API_URL}/receipt/customer/{customerId}
 * </p>
 * <p>
 *  Forexample:
 *  http://localhost:8080/receipt/rental/25
 *  http://localhost:8080/receipt/cusomer/1
 * </p>
 */
@org.springframework.stereotype.Controller
@RequestMapping("/receipt")
public class Controller {

    private RentalService rentalService;

    @Autowired
    private void setRentalService(RentalService rentalService){
        this.rentalService = rentalService;
    }

    /**
     *
     * @param rentalId type: Iteger. The id of the rental. Based on the rental id we can calculate the rental amount.
     * @param model With model (type: org.springframework.ui.Model), we can send the attributes to the template.
     * @return the template rental, if the rentalId was valid. If it is not, the noSuchReceipt template will return
     */
    @GetMapping("/rental/{rentalId}")
    public String rental(@PathVariable Integer rentalId, Model model){
        Rental rental = rentalService.findById(rentalId);
        if(rental == null)
            return "noSuchReceipt";
        model.addAttribute("rental", rental);
        model.addAttribute("amount", rental.getMovie().getAmount(rental.getDaysRented()));
        model.addAttribute("movie", rental.getMovie());
        model.addAttribute("customer", rental.getCustomer());
        return "rental";
    }

    /**
     *
     * @param customerId type: Iteger. The id of the customer. Based on the customer id we can calculate the rental amount.
     * @param model With model (type: org.springframework.ui.Model), we can send the attributes to the template.
     * @return the template customer, if the customerId was valid. If it is not, the noSuchReceipt template will return
     */
    @GetMapping("/customer/{customerId}")
    public String customer(@PathVariable Integer customerId, Model model){
        List<Rental> rentals = rentalService.findByCustomer((customerId));
        if(rentals == null)
            return "noSuchReceipt";
        List<RentalHelper> rentalHelpers =  rentalService.statement(rentals);
        model.addAttribute("responseObject",rentalHelpers);
        model.addAttribute("customer", rentalService.findCustomerById(customerId));
        model.addAttribute("customerAmount", rentalService.customerStatement(rentals));
        model.addAttribute("frequentRenterPoints", rentalService.frequentRenterPoints(rentals));
        return "customer";
    }

}
