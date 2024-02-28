/**
 * Nicholas Dhannie
 * CEN 3024C - Software Development 1
 * February 28, 2024
 * DealershipTest.java
 * This class is what will be about unit testing for the car dealership management system!
 */

import static org.junit.jupiter.api.Assertions.*;

class DealershipTest {
    private Inventory inventory;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        // Create a new inventory before each test
        inventory = new Inventory();

        // Create and add some sample cars to the inventory
        Car car1 = new Car(1, 2002, "Toyota", "Supra", "White", "I6", "Manual", 90000.0);
        Car car2 = new Car(2, 2021, "Honda", "Civic", "Black", "V4", "Automatic", 50000.0);

        inventory.addCar(car1);
        inventory.addCar(car2);
    }

    @org.junit.jupiter.api.Test
    void addCar() {
        // Create a sample car
        Car car = new Car(3, 2012, "Lexus", "LFA", "White", "V10", "Manual", 90000.0);
        // Add the car to the inventory
        inventory.addCar(car);
        // Check if the car is added to the inventory
        assertTrue(inventory.containsCarId(3), "Car should be added to the inventory");
    }

    @org.junit.jupiter.api.Test
    void getNextAvailableId() {
        // Get the next available ID
        int nextId = inventory.getNextAvailableId();

        // Verify that the next available ID is 3
        assertEquals(3, nextId, "Next available ID should be 3 after adding two cars");
    }

    @org.junit.jupiter.api.Test
    void removeCar() {
        // Remove a car from the inventory using attribute
        inventory.removeCar("make", "Toyota", -1); // Remove the car with make "Toyota"

        // Verify that the car has been removed
        assertFalse(inventory.containsCarId(1), "Car with ID 1 should be removed from the inventory");
        assertTrue(inventory.containsCarId(2), "Car with ID 2 should still be in the inventory");
    }

    @org.junit.jupiter.api.Test
    void removeCarById() {
        // Remove a car by ID from the inventory
        inventory.removeCarById(1); // Remove the car with ID 1

        // Verify that the car has been removed
        assertFalse(inventory.containsCarId(1), "Car with ID 1 should be removed from the inventory");
        assertTrue(inventory.containsCarId(2), "Car with ID 2 should still be in the inventory");
    }

    @org.junit.jupiter.api.Test
    void updateCar() {
        // Update the car in the inventory
        //Car updatedCar = new Car(1, 2021, "Honda", "Civic", "Black", "I4", "Manual", 40000.0);
        Car updatedCar = new Car(1, 2002, "Nissan", "GTR", "White", "V6", "Manual", 90000.0);
        inventory.updateCar(1, updatedCar);

        // Verify that the car has been updated
        Car retrievedCar = inventory.findCarById(1);
        assertNotNull(retrievedCar, "Updated car should not be null");
        assertEquals(updatedCar.getYear(), retrievedCar.getYear(), "Year should be updated");
        assertEquals(updatedCar.getMake(), retrievedCar.getMake(), "Make should be updated");
        assertEquals(updatedCar.getModel(), retrievedCar.getModel(), "Model should be updated");
        assertEquals(updatedCar.getColor(), retrievedCar.getColor(), "Color should be updated");
        assertEquals(updatedCar.getEngine(), retrievedCar.getEngine(), "Engine should be updated");
        assertEquals(updatedCar.getTransmissionType(), retrievedCar.getTransmissionType(), "Transmission type should be updated");
        assertEquals(updatedCar.getPrice(), retrievedCar.getPrice(), "Price should be updated");
    }

    @org.junit.jupiter.api.Test
    void sellCar() {

        // Sell a car from the inventory
        inventory.sellCar(1); // Mark the car with the ID: sold

        // Check that the car has been moved to sold cars list
        assertFalse(inventory.containsCarId(1), "Car with ID 1 should be marked as sold");
    }
}