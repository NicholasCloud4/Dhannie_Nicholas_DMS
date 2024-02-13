/**
 * Nicholas Dhannie
 * CEN 3024C - Software Development 1
 * February 13, 2024
 * Inventory.java
 * This class is what will be about the functions of the car Inventory which will consist of
 * adding, removing, updating and selling cars. It will store the cars.
 */

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Inventory {
    private List<Car> cars = new ArrayList<>(); //List that will hold all the cars
    private List<Car> soldCars = new ArrayList<>(); //List that will hold all the sold cars


    /**
     * Name: addCar
     * @param car
     * adds a car with the attributes
     */
    public void addCar(Car car) {
        cars.add(car);
    }

    /**
     * Name: removeCar
     * @param attribute
     * @param value
     * @param id
     *
     * It will iterate through the list of cars from the inventory
     * and remove a car that the user selects based on an attribute
     * that the user has selected and the value of the attribute
     * Ex: Color, White
     */
    public void removeCar(String attribute, String value, int id) {
        Iterator<Car> iterator = cars.iterator();
        while (iterator.hasNext()) {
            Car car = iterator.next();
            if (getAttributeValue(car, attribute).equalsIgnoreCase(value) || car.getId() == id) {
                iterator.remove();
                System.out.println("Car removed: " + car);
            }
        }
    }

    /**
     * Name: removeCarById
     * @param id
     *
     * This will remove the car by if the user selects to remove by ID
     * EX: id, 4
     */
    public void removeCarById(int id) {
        Iterator<Car> iterator = cars.iterator();
        while (iterator.hasNext()) {
            Car car = iterator.next();
            if (car.getId() == id) {
                iterator.remove();
                System.out.println("Car removed: " + car);
            }
        }
    }

    /**
     * Name: printInventory
     *
     * This is to print out the car inventory to show all the cars
     * that are currently available along with any changes that are made
     * later on.
     */
    public void printInventory() {
        System.out.println("\nPrinting car database:");
        for (Car car : cars) {
            System.out.println(car);
        }
    }


    /**
     * Name: updateCar
     * @param id
     * @param updatedCar
     *
     * When the user selects the id of a car that they want to update the attributes to.
     */
    public void updateCar(int id, Car updatedCar) {
        for (Car car : cars) {
            if (car.getId() == id) {
                car.setYear(updatedCar.getYear());
                car.setMake(updatedCar.getMake());
                car.setModel(updatedCar.getModel());
                car.setColor(updatedCar.getColor());
                car.setEngine(updatedCar.getEngine());
                car.setTransmissionType(updatedCar.getTransmissionType());
                car.setPrice(updatedCar.getPrice());
                System.out.println("Car updated: " + car);
                return;
            }
        }
        System.out.println("Car with ID " + id + " is not found.");
    }


    /**
     * Name: sellCar
     * @param id
     *
     * This is where the user will type an id to choose which car they would like to place
     * that has been sold and removed from the main inventory list to the soldCars list.
     */
    public void sellCar(int id) {
        Iterator<Car> iterator = cars.iterator();
        while (iterator.hasNext()) {
            Car car = iterator.next();
            if (car.getId() == id) {
                soldCars.add(car); // Add the car to the list of sold cars
                iterator.remove(); // Remove the car from the list of available cars
                System.out.println("Car with ID " + id + " has been sold.");
                return;
            }
        }
        System.out.println("Car with ID " + id + " not found in inventory.");
    }

    /**
     * Name: printSoldCars
     *
     * This will print the cars that have been sold
     */
    public void printSoldCars() {
        System.out.println("\nSold cars:");
        for (Car car : soldCars) {
            System.out.println(car);
        }
    }

    /**
     * Name: getAttributeValue
     * @param car
     * @param attribute
     * @return year, make, model, color, engine, transmissionType, price
     *
     * This will get all the attributes of the car and be used when the user
     * selects what specific attribute it is that they want to remove from the
     * list of the cars which will remove the cars that have the attribute from the list.
     *
     */
    private String getAttributeValue(Car car, String attribute) {
        switch (attribute.toLowerCase()) {
            case "year":
                return String.valueOf(car.getYear());
            case "make":
                return car.getMake();
            case "model":
                return car.getModel();
            case "color":
                return car.getColor();
            case "engine":
                return car.getEngine();
            case "transmission":
                return car.getTransmissionType();
            case "price":
                return String.valueOf(car.getPrice());
            default:
                return "";
        }
    }
}