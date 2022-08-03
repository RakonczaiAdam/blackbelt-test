package hu.radam.blackbelttest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class controller {

    @GetMapping("/receipt/{rentalId}")
    public String receipt(@PathVariable String rentalId){
        return "receipt";
    }

}
