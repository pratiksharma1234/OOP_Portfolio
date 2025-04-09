package hospital;

public class Receptionist extends Employee {
    public Receptionist(int empId, String name) {
        super(empId, name);
    }

    public void answerCalls() {
        System.out.println("Receptionist Emp#" + empId + " is answering calls.");
    }
}
