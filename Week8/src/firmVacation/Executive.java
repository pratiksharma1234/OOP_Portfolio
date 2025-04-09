package firmVacation;

public class Executive extends Employee {
    private double bonus;

    public Executive(String eName, String eAddress, String ePhone, double rate) {
        super(eName, eAddress, ePhone, rate);
        bonus = 0;
    }

    public void awardBonus(double execBonus) {
        bonus = execBonus;
    }

    public double pay() {
        double payment = super.pay() + bonus;
        bonus = 0;
        return payment;
    }

    public String vacation() {
        return "Executive vacation (4 weeks + 1 bonus week)";
    }
}
