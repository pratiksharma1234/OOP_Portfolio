package week5_6;

public class Bulb {
    private boolean isOn;
    
    // Constructor 
    public Bulb() {
        isOn = false;
    }
    
    // Method to turn on the bulb
    public void turnOn() {
        isOn = true;
        System.out.println("The bulb is now ON.");
    }
    
    // Method to turn off the bulb
    public void turnOff() {
        isOn = false;
        System.out.println("The bulb is now OFF.");
    }
    
    // Method to check the status of the bulb
    public void getStatus() {
        System.out.println("Bulb is " + (isOn ? "ON" : "OFF"));
    }

    public static void main(String[] args) {
        // Creating Bulb objects
        Bulb bulb1 = new Bulb();
        Bulb bulb2 = new Bulb();
        
        // Turn on the first bulb
        bulb1.turnOn();
        bulb1.getStatus();
        
        // Turn off the second bulb
        bulb2.turnOff();
        bulb2.getStatus();
    }
}