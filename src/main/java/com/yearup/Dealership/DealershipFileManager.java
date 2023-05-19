package com.yearup.Dealership;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class DealershipFileManager {

    public Dealership getDealership() {
        Dealership dealership = null;
        try {
            //Dealership file gets open
            FileReader fileReader = new FileReader("01-DealershipVehicleList");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            //Get dealership information
            String input;
            input = bufferedReader.readLine();
            String[] details = input.split("\\|");
            String name = details[0];
            String address = details[1];
            String phone = details[2];
            dealership = new Dealership(name, address, phone);


            while ((input = bufferedReader.readLine()) != null) {
                  details = input.split("\\|");

                    String vin = details[0];
                    int year = Integer.parseInt(details[1]);
                    String make = details[2];
                    String model = details[3];
                    String vehicleType = details[4];
                    String color = details[5];
                    int odometer = Integer.parseInt(details[6]);
                    double price = Double.parseDouble(details[7]);

                    Vehicle vehicle = new Vehicle(vin,year,make,model,vehicleType,color,odometer,price);
                    dealership.addVehicle(vehicle);



            }
            } catch (IOException e) {
            System.out.println("File not found");
            System.exit(0);
        }
        return dealership;
    }

    public void saveDealership(Dealership dealerShip){

    }
}


