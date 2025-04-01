package week5_6;
public class Sphere {
    private double diameter; 
    // Constructor 
    public Sphere(double diameter) {
        this.diameter = diameter;
    }

    // Getter method 
    public double getDiameter() {
        return diameter;
    }

    // Setter method 
    public void setDiameter(double diameter) {
        this.diameter = diameter;
    }

    // calculate and return the volume of the sphere
    public double getVolume() {
        double radius = diameter / 2;
        return (4.0 / 3) * Math.PI * Math.pow(radius, 3);
    }

    // Method to calculate and return the surface area of the sphere
    public double getSurfaceArea() {
        double radius = diameter / 2;
        return 4 * Math.PI * Math.pow(radius, 2);
    }

    // Override toString method to return a one-line description of the sphere
    @Override
    public String toString() {
        return "Sphere [Diameter = " + diameter + ", Volume = " + getVolume() + ", Surface Area = " + getSurfaceArea() + "]";
    }

    public static void main(String[] args) {
        // Create and initialize several Sphere objects
        Sphere sphere1 = new Sphere(10.0);
        Sphere sphere2 = new Sphere(15.0);
        Sphere sphere3 = new Sphere(20.0);

        // Print the details
        System.out.println(sphere1);
        System.out.println(sphere2);
        System.out.println(sphere3);

        // Update the diameter of one sphere and print its updated details
        sphere1.setDiameter(12.0);
        System.out.println("After updating diameter of sphere1: " + sphere1);
    }
}