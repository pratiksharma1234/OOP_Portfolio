package week3;
import java.util.HashMap;
import java.util.Map;
public class Punctuation {
	 public static void main(String[] args) {
	        String text = "Mary had a little lamb, her fleece was as white as snow, and everywhere Mary went, the lamb was sure to go. -that was a nice poem- the end.";

	        // Define punctuation characters to count
	        char[] punctuations = {'.', ',', '-', '!', '?', ';', ':', '\''};

	        // Map to store punctuation counts
	        Map<Character, Integer> punctuationCounts = new HashMap<>();

	        // Initialize map with punctuation keys
	        for (char p : punctuations) {
	            punctuationCounts.put(p, 0);
	        }

	        // Count punctuation occurrences
	        for (char c : text.toCharArray()) {
	            if (punctuationCounts.containsKey(c)) {
	                punctuationCounts.put(c, punctuationCounts.get(c) + 1);
	            }
	        }

	        // Print the result
	        System.out.println("Punctuation Count:");
	        for (Map.Entry<Character, Integer> entry : punctuationCounts.entrySet()) {
	            System.out.println("'" + entry.getKey() + "': " + entry.getValue());
	        }
	    }
	}

