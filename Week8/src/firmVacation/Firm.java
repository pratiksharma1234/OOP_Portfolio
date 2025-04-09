package firmVacation;


public class Firm {
    public static void main(String[] args) {
        Staff personnel = new Staff();
        personnel.payday();
        System.out.println("\n--- Vacation Info ---");
        personnel.showVacations();
    }
}
