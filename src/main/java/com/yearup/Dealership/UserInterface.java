package com.yearup.Dealership;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * MenuInterface is a superclass (base class) that can be used
 * to develop menu-driven user interfaces that run on ANSI
 * standard terminals and emulators such as XTerm, MinTerm,
 * etc.
 * <p>
 * The general appearance of a CLI menu is divided
 * into four sections:
 * <p>
 * heading     - A Heading, that introduces the menu.
 * menu        - A list of options, identified by numbers or letters.
 * status      - A status line that can be used to report input errors.
 * menu_prompt - That asks the user for the appropriate inputThis class is not strongly encapsulated.
 * <p>
 * Subclasses are granted access to the following
 * protected variables to make coding simple.
 * <p>
 * String heading
 * String menu
 * String menu_prompt
 * String status
 * Scanner inputScanner
 * <p>
 * This superclass is not really very versatile.  It is specific to
 * the dealership app and expects to work with
 * the following domain classes:
 * <p>
 * Vehicle    -   it will hold information about a specific vehicle.
 * Dealership -   it will hold information about the dealership and maintain a list of vehicles with methods to search
 * for matching vehicles as well as add/remove vehicles.
 * DealershipFileManage - it will read the dealership csv file, parse, and create a Dealership object full of vehicles
 * the file. I will also save a dealership and the vehicles back into the file with the pipe-delimited format.
 * <p>
 * <p>
 * A subclass must provide its own constructor, and
 * must @Override the following methods:
 * <p>
 * doInputUntilDone()
 */
public class UserInterface {
    Dealership dealership;

    private void init() {
        DealershipFileManager dfm = new DealershipFileManager();
        dealership = dfm.getDealership();
    }

    public void display() {
        ////Load the dealership data
        init();

        Scanner scanner = new Scanner(System.in);
        int input;
        do {
            System.out.print("""
                    ----------Used Car Dealership-----------
                    1. Get Vehicles by Price
                    2. Get Vehicles by Make and Model
                    3. Get Vehicles by Year
                    4. Get Vehicles by Color
                    5. Get Vehicles by Mileage
                    6. Get Vehicles by Vehicle Type
                    7. Get All Vehicles
                    8. Add a Vehicle
                    9. Remove a Vehicle
                    0. Exit
                    Please enter your choice: 
                    """);
            input = Integer.parseInt(scanner.nextLine());


            switch (input) {
                case 1 -> processGetByPriceRequest();
                case 2 -> processGetByMakeModelRequest();
                case 3 -> processGetByYearRequest();
                case 4 -> processGetByColorRequest();
                case 5 -> processGetByMileageRequest();
                case 6 -> processGetByVehicleTypeRequest();
                case 7 -> processGetAllVehicleRequest();
                case 8 -> processAddVehicleRequest();
                case 9 -> processRemoveVehicleRequest();
                case 0 -> {
                    System.out.println("See You On The Road ;) ");
                    System.exit(0);
                }
                default -> System.out.println("Please enter a valid option");


            }
        } while (input != 0);


    }

    public void displayVehicles(ArrayList<Vehicle> inventory) {

        for (Vehicle v : inventory) {
            System.out.printf("%-20s %-7s %-15s %-15s %-10s %-10s %-20d %-20.2f \n",
                    v.getVin(),
                    v.getYear(),
                    v.getMake(),
                    v.getModel(),
                    v.getVehicleType(),
                    v.getColor(),
                    v.getOdometer(),
                    v.getPrice());
        }

    }


    private void processGetByPriceRequest() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter a minimum price");
        int min = scanner.nextInt();
        System.out.println("Please enter a maximum price");
        int max = scanner.nextInt();
        this.dealership.getVehiclesByPrice(min, max);

    }

    private void processGetByYearRequest() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the minimum Year of the vehicle");
        int minYear = scanner.nextInt();
        System.out.println("Please enter the maximum Year of the vehicle");
        int maxYear = scanner.nextInt();
        this.dealership.getVehiclesByYear(minYear, maxYear);
    }

    private void processGetByMakeModelRequest() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the make of the vehicle");
        String make = scanner.nextLine();
        System.out.println("Please enter the model of the vehicle");
        String model = scanner.nextLine();
        this.dealership.getVehiclesByMakeModel(make, model);
    }

    private void processGetByColorRequest() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the color of the vehicle");
        String color = scanner.nextLine();
        this.dealership.getVehiclesByColor(color);
    }

    private void processGetByMileageRequest() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the minimum mileage of the vehicle");
        int min = scanner.nextInt();
        System.out.println("Please enter the maximum mileage of the vehicle");
        int max = scanner.nextInt();
        this.dealership.getVehiclesByMileage(min, max);
    }

    private void processGetByVehicleTypeRequest() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the type of the vehicle");
        String type = scanner.nextLine();
        this.dealership.getVehiclesByType(type);
    }

    private void processGetAllVehicleRequest() {
        ArrayList list = (ArrayList) dealership.getAllVehicles();
        displayVehicles(list);
    }

    private void processAddVehicleRequest() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the vin of the vehicle: ");
        String vin = scanner.next();
        System.out.println("Please enter the year of the vehicle: ");
        int year = scanner.nextInt();
        System.out.println("Please enter the make of the vehicle: ");
        String make = scanner.next();
        System.out.println("Please enter the model of the vehicle: ");
        String model = scanner.next();
        System.out.println("Please enter the type of the vehicle: ");
        String vehicleType = scanner.next();
        System.out.println("Please enter the color of the vehicle: ");
        String color = scanner.next();
        System.out.println("Please enter the odometer of the vehicle: ");
        int odometer = scanner.nextInt();
        System.out.println("Please enter the price of the vehicle: ");
        double price = scanner.nextDouble();
        System.out.println(" \n Vehicle added successfully. Thank You!");
        Vehicle vehicle = new Vehicle(vin, year, make, model, vehicleType, color, odometer, price);
        dealership.addVehicle(vehicle);

        DealershipFileManager DFM = new DealershipFileManager();
        DFM.saveDealership(dealership);

    }

    private void processRemoveVehicleRequest() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the VIN of the vehicle that you would like to remove: ");
        String vin = scanner.next();

        List<Vehicle> allVehicles = this.dealership.getAllVehicles();
        for (Vehicle v : allVehicles) {
            if (v.getVin().equals(vin)) {
                System.out.println("Vehicle has been removed.");
                this.dealership.removeVehicle(v);
                return;
            }
        }

        System.out.println("No vehicle found with the specified VIN.");
    }
}

