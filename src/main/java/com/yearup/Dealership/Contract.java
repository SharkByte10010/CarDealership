package com.yearup.Dealership;

public abstract class Contract {
//
    private String date;
    private String customer_name;
    private String customer_email;
    private Vehicle vehicle;

    public Contract(String date, String customer_name, String customer_email, Vehicle vehicle) {
        this.date = date;
        this.customer_name = customer_name;
        this.customer_email = customer_email;
        this.vehicle = vehicle;
    }

    public abstract double getTotalPrice();

    public abstract double getMonthlyPayment();

    public abstract String getPersistenceString();

    public String getDate() {
        return date;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public String getCustomer_email() {
        return customer_email;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }
}
