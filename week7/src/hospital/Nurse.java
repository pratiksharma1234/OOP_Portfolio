package hospital;

public class Nurse extends Employee {
    public Nurse(int empId, String name) {
        super(empId, name);
    }

    public void assist() {
        System.out.println("Nurse Emp#" + empId + " is assisting doctors and patients.");
    }
}
