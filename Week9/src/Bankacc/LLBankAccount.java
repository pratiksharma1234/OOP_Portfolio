package Bankacc;

public class LLBankAccount {
    private String name;
    private int accNumber;
    private double balance;

    public LLBankAccount(String name, int accNumber) {
        this.name = name;
        this.accNumber = accNumber;
        this.balance = 0.0;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited " + amount + " into account " + accNumber);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public void withdraw(double amount) {
        if (amount > balance) {
            System.out.println("Insufficient funds in account " + accNumber);
        } else if (amount <= 0) {
            System.out.println("Invalid withdrawal amount.");
        } else {
            balance -= amount;
            System.out.println("Withdrew " + amount + " from account " + accNumber);
        }
    }

    public void addInterest() {
        balance += balance * 0.03;
    }

    public void display() {
        System.out.printf("Account: %d, Name: %s, Balance: %.2f%n", accNumber, name, balance);
    }
}

