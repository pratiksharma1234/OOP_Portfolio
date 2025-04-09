package hospital;

//Base class
public class Employee {
 protected int empId;
 protected String name;

 public Employee(int empId, String name) {
     this.empId = empId;
     this.name = name;
 }

 public void work() {
     System.out.println(name + " (Emp#" + empId + ") is working.");
 }
}
