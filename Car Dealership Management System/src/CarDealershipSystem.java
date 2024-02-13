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
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Step 2: Printing the inventory
        System.out.println("The car database will be displayed shortly...");
        inventory.printInventory();

        // Step 3: Removing an object
        System.out.print("Enter attribute or ID to remove: ");
        String attributeIdRemove = scan.nextLine();
        if (attributeIdRemove.equalsIgnoreCase("id")) {
            System.out.print("Enter value of ID to remove: ");
            int idRemove = Integer.parseInt(scan.nextLine());
            inventory.removeCarById(idRemove);
        } else {
            System.out.print("Enter value of attribute to remove: ");
            String valueRemove = scan.nextLine();
            inventory.removeCar(attributeIdRemove, valueRemove, -1);
        }
        inventory.printInventory();

        // Step 4: Remove using second attribute
        System.out.print("Enter attribute or ID to remove: ");
        String secondAttributeIdRemove = scan.nextLine();
        if (secondAttributeIdRemove.equalsIgnoreCase("id")) {
            System.out.print("Enter value of ID to remove: ");
            int idToRemove = Integer.parseInt(scan.nextLine());
            inventory.removeCarById(idToRemove);
        } else {
            System.out.print("Enter value of attribute to remove: ");
            String secondValueRemove = scan.nextLine();
            inventory.removeCar(secondAttributeIdRemove, secondValueRemove, -1);
        }
        inventory.printInventory();

        // Step 5: Update an object based on the id that user selects
        System.out.print("Enter ID of car to update: ");  // Prompt user for the ID of the car to update
        int carIdToUpdate = Integer.parseInt(scan.nextLine());  // Read the ID from user input
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
        Car updatedCar = new Car(carIdToUpdate,updatedYear, updatedMake, updatedModel, updatedColor, updatedEngine, updatedTransmission, updatedPrice);
        inventory.updateCar(carIdToUpdate, updatedCar);
        inventory.printInventory();

        // Step 6: Custom operation which will mark the car that the user selects as sold
        System.out.print("Enter the ID of the car to mark as sold: ");
        int carIdSell = Integer.parseInt(scan.nextLine());
        inventory.sellCar(carIdSell);
        inventory.printSoldCars();

        inventory.printInventory();


        scan.close();
    }
}