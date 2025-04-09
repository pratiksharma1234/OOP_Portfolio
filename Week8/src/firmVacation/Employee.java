package firmVacation;

public class Employee {
    private String name;
    private String address;
    private String phone;
    protected double payRate;

    public Employee(String eName, String eAddress, String ePhone, double rate) {
        name = eName;
        address = eAddress;
        phone = ePhone;
        payRate = rate;
    }

    public String toString() {
        return "Name: " + name + "\nAddress: " + address + "\nPhone: " + phone;
    }

    public double pay() {
        return payRate;
    }

    public String vacation() {
        return "Standard vacation (2 weeks)";
    }

    public String getName() {
        return name;
    }
}
