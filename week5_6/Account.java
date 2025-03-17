package week5_6;

public class Account {
    private String name;
    private String accountNumber;
    private double balance;

    public Account(String name, String accountNumber) {
        this.name = name;
        this.accountNumber = accountNumber;
        this.balance = 0.0;
    }

    public String getName() {
        return name;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public String toString() {
        return "Account Name: " + name + "\nAccount Number: " + accountNumber + "\nBalance: " + balance;
    }

    public static void main(String[] args) {
        Account acc1 = new Account("Mr pratik", "123456789");
        System.out.println(acc1);
    }
}