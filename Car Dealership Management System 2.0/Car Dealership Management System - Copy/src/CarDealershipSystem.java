/**
 * Nicholas Dhannie
 * CEN 3024C - Software Development 1
 * February 13, 2024
 * CarDealershipSystem.java
 * This class is what will be about the Car Dealership Management System Software which
 * will have the application that the user will be using to access the "Car Database".
 * It contains all the necessary options that was given in the project requirements for this
 * assignment and is *not the final product*.
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class CarDealershipSystem {
    public static void main(String[] args) {
        Inventory inventory = new Inventory(); //cars will be added to this inventory
        Scanner scan = new Scanner(System.in);

        //STARTING THE CAR DATABASE SYSTEM
        System.out.println("*************************************************");
        System.out.println(" Welcome to the Car Dealership Management System");
        System.out.println("*************************************************");
        System.out.println("\nInstructions:\n-When it asks for id or attribute to remove\nto select ID type id and then you will be asked to enter the value such as the number.");
        System.out.println("-If you want to select an attribute you can type: year, make , model, color, engine transmission or price\nthen type the name or number of the selected attribute such as red if color was selected.");

        // Step 1: Ask for file name and adding the car objects to the inventory
        while (true) {
            System.out.print("\nTo start please enter the file location: ");
            String fileName = scan.nextLine();
            try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] data = line.split(","); // Separate based on "," from txt file
                    int id = Integer.parseInt(data[0]); // Parse ID from data
                    Car car = new Car(id, Integer.parseInt(data[1]), data[2], data[3], data[4], data[5], data[6], Double.parseDouble(data[7]));
                    inventory.addCar(car);
                }
                break;
            } catch (IOException e) {
                //e.printStackTrace();
                System.err.println("Error reading the file: " + e.getMessage());
            }
        }

        // Step 2: Printing the inventory
        System.out.println("The car database will be displayed shortly...");
        inventory.printInventory();

        // Step 3: Removing an object
        while (true) {
            System.out.print("Enter attribute or ID to remove: ");
            String attributeIdRemove = scan.nextLine();
            if (!attributeIdRemove.equalsIgnoreCase("id") && !attributeIdRemove.matches("year|make|model|color|engine|transmission|price")) {
                System.out.println("Invalid input. Please enter 'id' or a valid attribute.");
                continue;
            }
            if (attributeIdRemove.equalsIgnoreCase("id")) {
                System.out.print("Enter value of ID to remove: ");
                String idToRemoveStr = scan.nextLine();

                // Validate if the entered ID is a valid integer
                try {
                    int idRemove = Integer.parseInt(idToRemoveStr);
                    boolean removed = inventory.removeCarById(idRemove);
                    if (!removed) {
                        System.out.println("Car with ID " + idRemove + " not found in inventory.");
                        continue;
                    }
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid ID. Please enter a valid integer.");
                    continue;
                }
            } else {
                System.out.print("Enter value of attribute to remove: ");
                String valueRemove = scan.nextLine();
                //Making sure that the user enters a number for year & price
                if (attributeIdRemove.equalsIgnoreCase("year") || attributeIdRemove.equalsIgnoreCase("price")) {
                    try {
                        int numericValue = Integer.parseInt(valueRemove);
                        inventory.removeCar(attributeIdRemove, null, numericValue);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a valid number for " + attributeIdRemove + ".");
                        continue; // Prompt user again due to invalid input
                    }
                } else if (attributeIdRemove.equalsIgnoreCase("color") || attributeIdRemove.equalsIgnoreCase("make") ||
                        attributeIdRemove.equalsIgnoreCase("model") || attributeIdRemove.equalsIgnoreCase("transmission") ||
                        attributeIdRemove.equalsIgnoreCase("engine")) {
                    //Making sure that the user enters a string for the attributes
                    try {
                        Integer.parseInt(valueRemove);
                        System.out.println("Invalid input. Please enter a valid string for " + attributeIdRemove + ".");
                        continue; // Prompt user again due to invalid input
                    } catch (NumberFormatException e) {
                        //remove if it is string
                        inventory.removeCar(attributeIdRemove, valueRemove, -1);
                    }
                } else {
                    //remove if it is number
                    inventory.removeCar(attributeIdRemove, valueRemove, -1);
                }
            }
            break;
        }
        inventory.printInventory();


        // Step 4: Remove using second attribute
        while (true) {
            System.out.print("Enter attribute or ID to remove: ");
            String secondAttributeIdRemove = scan.nextLine();
            if (!secondAttributeIdRemove.equalsIgnoreCase("id") && !secondAttributeIdRemove.matches("year|make|model|color|engine|transmission|price")) {
                System.out.println("Invalid input. Please enter 'id' or a valid attribute.");
                continue;
            }
            if (secondAttributeIdRemove.equalsIgnoreCase("id")) {
                System.out.print("Enter value of ID to remove: ");
                String idToRemoveStr = scan.nextLine();

                // Validate if the entered ID is a valid integer
                try {
                    int idRemove = Integer.parseInt(idToRemoveStr);
                    boolean removed = inventory.removeCarById(idRemove);
                    if (!removed) {
                        System.out.println("Car with ID " + idRemove + " not found in inventory.");
                        continue;
                    }
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid ID. Please enter a valid integer.");
                    continue;
                }
            } else {
                System.out.print("Enter value of attribute to remove: ");
                String valueRemove = scan.nextLine();
                //Making sure that the user enters a number for year & price
                if (secondAttributeIdRemove.equalsIgnoreCase("year") || secondAttributeIdRemove.equalsIgnoreCase("price")) {
                    try {
                        int numericValue = Integer.parseInt(valueRemove);
                        inventory.removeCar(secondAttributeIdRemove, null, numericValue);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a valid number for " + secondAttributeIdRemove + ".");
                        continue;
                    }
                } else if (secondAttributeIdRemove.equalsIgnoreCase("color") || secondAttributeIdRemove.equalsIgnoreCase("make") ||
                        secondAttributeIdRemove.equalsIgnoreCase("model") || secondAttributeIdRemove.equalsIgnoreCase("transmission") ||
                        secondAttributeIdRemove.equalsIgnoreCase("engine")) {
                    //Making sure that the user enters a string for the attributes
                    try {
                        Integer.parseInt(valueRemove);
                        System.out.println("Invalid input. Please enter a valid string for " + secondAttributeIdRemove + ".");
                        continue; // Prompt user again due to invalid input
                    } catch (NumberFormatException e) {
                        //remove if it is string
                        inventory.removeCar(secondAttributeIdRemove, valueRemove, -1);
                    }
                } else {
                    //remove if it is number
                    inventory.removeCar(secondAttributeIdRemove, valueRemove, -1);
                }
            }
            break;
        }
        inventory.printInventory();

        // Step 5: Update an object based on the id that user selects
        while (true) {
            try {
                System.out.print("Enter ID of car to update: ");
                int carIdToUpdate = Integer.parseInt(scan.nextLine());

                // Check if the car ID exists in the inventory
                if (!inventory.containsCarId(carIdToUpdate)) {
                    System.out.println("Car with ID " + carIdToUpdate + " not found in inventory.");
                    continue; // Exit method
                }

                System.out.print("Enter updated year: ");
                int updatedYear = Integer.parseInt(scan.nextLine());
                System.out.print("Enter updated make: ");
                String updatedMake = scan.nextLine();
                System.out.print("Enter updated model: ");
                String updatedModel = scan.nextLine();
                System.out.print("Enter updated color: ");
                String updatedColor = scan.nextLine();
                System.out.print("Enter updated engine: ");
                String updatedEngine = scan.nextLine();
                System.out.print("Enter updated transmission: ");
                String updatedTransmission = scan.nextLine();
                System.out.print("Enter updated price: ");
                double updatedPrice = Double.parseDouble(scan.nextLine());

                // Update the car in the inventory
                Car updatedCar = new Car(carIdToUpdate, updatedYear, updatedMake, updatedModel, updatedColor, updatedEngine, updatedTransmission, updatedPrice);
                inventory.updateCar(carIdToUpdate, updatedCar);
                inventory.printInventory();

                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer for car ID, year, and price.");
            }
        }

        // Step 6: Custom operation which will mark the car that the user selects as sold
        while (true) {
            try {
                System.out.print("Enter the ID of the car to mark as sold: ");
                int carIdSell = Integer.parseInt(scan.nextLine());

                // Check if the car ID exists in the inventory
                if (!inventory.containsCarId(carIdSell)) {
                    System.out.println("Car with ID " + carIdSell + " not found in inventory.");
                    continue; // Prompt the user again for a valid car ID
                }

                // Mark the car as sold
                inventory.sellCar(carIdSell);
                inventory.printSoldCars();
                inventory.printInventory();

                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer for car ID.");
            }
        }

        // Step 7: Prompt the user to add a new car to the database
        System.out.println("\nAdd a new car to the database:");
        int year;
        while (true) {
            try {
                System.out.print("Enter year: ");
                year = Integer.parseInt(scan.nextLine());
                break; // Exit the loop if a valid integer is entered
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer for year.");
            }
        }
        String make;
        System.out.print("Enter make: ");
        make = scan.nextLine();
        String model;
        System.out.print("Enter model: ");
        model = scan.nextLine();
        String color;
        System.out.print("Enter color: ");
        color = scan.nextLine();
        String engine;
        System.out.print("Enter engine: ");
        engine = scan.nextLine();
        String transmission;
        System.out.print("Enter transmission: ");
        transmission = scan.nextLine();
        double price;
        while (true) {
            try {
                System.out.print("Enter price: ");
                price = Double.parseDouble(scan.nextLine());
                break; // Exit the loop if a valid double is entered
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number for price.");
            }
        }

        // Create a new car object
        int nextId = inventory.getNextAvailableId(); // You need to implement this method to generate a unique ID for the new car
        Car newCar = new Car(nextId, year, make, model, color, engine, transmission, price);

        // Add the new car to the inventory
        inventory.addCar(newCar);
        System.out.println("New car added to the inventory.");

        // Print the updated inventory
        inventory.printInventory();


        scan.close();
    }
}