package com.yearup.Dealership;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;


public class DealershipFileManager {

    public Dealership getDealership() {
        Dealership dealership = null;
        try {
            //Dealership file gets open
            FileReader fileReader = new FileReader("01-DealershipVehicleList.csv");
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

    public void saveDealership(Dealership dealership) {
        try {
            FileWriter fileWriter = new FileWriter("01-DealershipVehicleList.csv", true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            // Save dealership information
            bufferedWriter.write(dealership.getName() + "|" + dealership.getAddress() + "|" + dealership.getPhone());
            bufferedWriter.newLine();

            // Save vehicles
            List<Vehicle> vehicles = dealership.getAllVehicles();
            for (Vehicle vehicle : vehicles) {
                bufferedWriter.write(
                        vehicle.getVin() + "|" +
                                vehicle.getYear() + "|" +
                                vehicle.getMake() + "|" +
                                vehicle.getModel() + "|" +
                                vehicle.getVehicleType() + "|" +
                                vehicle.getColor() + "|" +
                                vehicle.getOdometer() + "|" +
                                vehicle.getPrice()
                );
                bufferedWriter.newLine();
            }

            bufferedWriter.close();
            System.out.println("Dealership saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving dealership: " + e.getMessage());
        }
    }
}


