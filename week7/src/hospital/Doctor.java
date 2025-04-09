package hospital;

public class Doctor extends Employee {
    public Doctor(int empId, String name) {
        super(empId, name);
    }

    public void diagnose() {
        System.out.println("Doctor Emp#" + empId + " is diagnosing patients.");
    }
}
