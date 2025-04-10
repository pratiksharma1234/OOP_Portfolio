package Postcode;

public class PostEntry {
    private String firstName;
    private String lastName;
    private String postalCode;

    public PostEntry(String firstName, String lastName, String postalCode) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.postalCode = postalCode;
    }

    public void display() {
        System.out.printf("Name: %s %s\tPost Code: %s%n", firstName, lastName, postalCode);
    }
}
