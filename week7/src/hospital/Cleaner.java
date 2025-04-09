package hospital;

public class Cleaner extends Employee {
    public Cleaner(int empId, String name) {
        super(empId, name);
    }

    public void clean() {
        System.out.println("Cleaner Emp#" + empId + " is sweeping the hospital.");
    }
}
