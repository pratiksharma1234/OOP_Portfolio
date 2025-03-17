package week4;

public class String0bjects {
    public static void main(String[] args) {
        String college = "Leeds Beckett University";
        String town = "Anytown, UK"; // Part (a)
        
        int stringLength = college.length(); // Part (b)
        
        System.out.println(college + " contains " + stringLength + " characters.");
        
        String change1 = college.toUpperCase(); // Part (c)
        String change2 = change1.replace('E', '*'); // Part (d)
        String change3 = college.concat(town); // Part (e)
        
        System.out.println("The final string is " + change3);
    }
}
