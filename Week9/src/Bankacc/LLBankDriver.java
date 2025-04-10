package Bankacc;

public class LLBankDriver {
    public static void main(String[] args) {
        LLBankAccount[] customers = new LLBankAccount[30];

        customers[0] = new LLBankAccount("pratik", 1001);
        customers[1] = new LLBankAccount("ram", 1002);

        customers[0].deposit(500);
        customers[1].deposit(1000);

        customers[0].withdraw(400);
        customers[1].withdraw(1500); // Invalid - Insufficient funds

        customers[0].addInterest();
        customers[1].addInterest();

        customers[0].display();
        customers[1].display();
    }
}

