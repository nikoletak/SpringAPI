package com.example.springapi.api.controller;

import com.example.springapi.api.model.User;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@SpringBootApplication
@RestController
public class UserController {
    public static List<User.Trader> traderList = new ArrayList<>();

    @PostConstruct
    public void init() {
        // Adding trader objects to the list
        User.Trader trader1 = new User.Trader(1, "Trader1", "general", "rate");
        User.Trader trader2 = new User.Trader(2, "Trader2", "wining", "rate");
        User.Trader trader3 = new User.Trader(3, "Trader3", "general", "amount");
        User.Trader trader4 = new User.Trader(4, "Trader4", "wining", "amount");

        traderList.add(trader1);
        traderList.add(trader2);
        traderList.add(trader3);
        traderList.add(trader4);
    }

    @PostMapping("/user")//our path ex. http://localhost:8080/user for POST request
    public User.Outgoing calculateTax(@RequestBody User.Incoming incoming) {
        int traderIncoming = incoming.getTraderId();
        User.Trader currentTrader = null;//getting our trader id
        for (User.Trader trader : traderList) {
            if (trader.getTraderId() == traderIncoming) {
                currentTrader = trader;
                break;
            }
        }

        if (currentTrader == null) {//exception if the trader id is not on the list
            throw new IllegalArgumentException("Trader " + traderIncoming + " not found.");
        }

        double playedAmount = incoming.getPlayedAmount();//getting played amount
        double odd = incoming.getOdd();//getting odd
        double possibleReturnAmountBefTax = playedAmount * odd;

        double taxRateGen = 0.1;//tax rate for General count
        double taxRateWin = 0.1;//tax rate for Winning count
        double taxAmountGen = 2;//tax amount for General count
        double taxAmountWin = 1;//tax amount for Winning count
        double taxAmount = 0;//starting amount is 0, because we will initialize it depending on our trader id
        double possibleReturnAmountAfterTax;

        //Checking the TaxType count
        if (currentTrader.getTaxType().equals("general") && currentTrader.getTaxBase().equals("rate")) {
            possibleReturnAmountAfterTax = possibleReturnAmountBefTax - (possibleReturnAmountBefTax * taxRateGen);
        } else if (currentTrader.getTaxType().equals("wining") && currentTrader.getTaxBase().equals("rate")) {
            possibleReturnAmountAfterTax = possibleReturnAmountBefTax - (possibleReturnAmountBefTax * taxRateWin);
        } else if (currentTrader.getTaxType().equals("general") && currentTrader.getTaxBase().equals("amount")) {
            possibleReturnAmountAfterTax = possibleReturnAmountBefTax - taxAmountGen;
            taxAmount = taxAmountGen;
        } else if (currentTrader.getTaxType().equals("wining") && currentTrader.getTaxBase().equals("amount")) {
            possibleReturnAmountAfterTax = possibleReturnAmountBefTax - taxAmountWin;
            taxAmount = taxAmountWin;
        } else {
            throw new IllegalArgumentException("Invalid tax type or tax base");
        }
        //Setting our output values
        User.Outgoing outgoing = new User.Outgoing();
        outgoing.setPossibleReturnAmount(possibleReturnAmountAfterTax);
        outgoing.setPossibleReturnAmountBefTax(possibleReturnAmountBefTax);
        outgoing.setPossibleReturnAmountAfterTax(possibleReturnAmountAfterTax);
        outgoing.setTaxRate(taxRateGen);
        outgoing.setTaxAmount(taxAmount);

        return outgoing;
    }

    public static void main(String[] args) {
        SpringApplication.run(UserController.class, args);
    }
}


