package com.example.springapi.api.model;

public class User {
    //Input variables class
    public static class Incoming{
        //initializing incoming variables with getters and setters
        private int traderId;
        private double playedAmount;
        private double odd;

        public int getTraderId() {
            return traderId;
        }

        public void setTraderId(int traderId) {
            this.traderId = traderId;
        }

        public double getPlayedAmount() {
            return playedAmount;
        }

        public void setPlayedAmount(double playedAmount) {
            this.playedAmount = playedAmount;
        }

        public double getOdd() {
            return odd;
        }

        public void setOdd(double odd) {
            this.odd = odd;
        }

    }
    //Output variables class
    public static class Outgoing{
        //Initializing outgoing variables with getters and setters
        private double possibleReturnAmount;
        private double possibleReturnAmountBefTax;
        private double possibleReturnAmountAfterTax;
        private double taxRate;
        private double taxAmount;

        public double getPossibleReturnAmount() {
            return possibleReturnAmount;
        }

        public void setPossibleReturnAmount(double possibleReturnAmount) {
            this.possibleReturnAmount = possibleReturnAmount;
        }

        public double getPossibleReturnAmountBefTax() {
            return possibleReturnAmountBefTax;
        }

        public void setPossibleReturnAmountBefTax(double possibleReturnAmountBefTax) {
            this.possibleReturnAmountBefTax = possibleReturnAmountBefTax;
        }

        public double getPossibleReturnAmountAfterTax() {
            return possibleReturnAmountAfterTax;
        }

        public void setPossibleReturnAmountAfterTax(double possibleReturnAmountAfterTax) {
            this.possibleReturnAmountAfterTax = possibleReturnAmountAfterTax;

        }
        public double getTaxRate() {
            return taxRate;
        }

        public void setTaxRate(double taxRate) {
            this.taxRate = taxRate;
        }

        public double getTaxAmount() {
            return taxAmount;
        }

        public void setTaxAmount(double taxAmount) {
            this.taxAmount = taxAmount;
        }

    }
    //Initializing Trader class with variables and getters and setters
    //We will be using this class to read specific information about the tax type counter from our Trader List
    public static class Trader{
        private int traderId;
        private String name;
        private String taxType;

        private String taxBase;

        public Trader(int traderId, String name, String taxType, String taxBase) { //Constructor for our Trader list
            this.traderId = traderId;
            this.name = name;
            this.taxType = taxType; //taxType can be General or Winning
            this.taxBase = taxBase; //taxType can be per rate or per amount
        }

        public int getTraderId() {
            return traderId;
        }

        public void setTraderId(Integer traderId) {
            this.traderId = traderId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;

        }

        public String getTaxType() {
            return taxType;
        }

        public void setTaxType(String taxType) {
            this.taxType = taxType;
        }

        public String getTaxBase() {
            return taxBase;
        }

        public void setTaxBase(String taxBase) {
            this.taxBase = taxBase;
        }

    }
}
