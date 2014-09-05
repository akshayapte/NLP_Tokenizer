import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/*
 * @author: Sudarshan (Sid)
 */

public class WordCount {

	/*
	 * Function to update the HashMap with Frequency of words
	 */
	public static HashMap<String, Integer> fCount(String word,
			HashMap<String, Integer> fzMap) {
		if (fzMap.containsKey(word)) {
			fzMap.put(word, fzMap.get(word) + 1);
		} else {
			fzMap.put(word, 1);
		}
		return fzMap;
	}

	/*
	 * Main Function
	 */
	public static void main(String[] args) throws IOException {
		Scanner input = new Scanner(System.in);
		System.out.println("Enter the filename:\n");
		String filename = input.nextLine();

		BufferedReader br = new BufferedReader(new FileReader(filename));
		String line;

		/*
		 * HashMap to store word: frequency values
		 */
		HashMap<String, Integer> fzMap = new HashMap<String, Integer>();

		while (((line = br.readLine()) != null)) {
			if (line.length() > 0) { // check if a blank line. If so, skip the
										// line.
				for (String word : line.split(" ")) {
					/*
					 * Have removed all unwanted characters
					 */
					word = word.replaceAll("[^A-Za-z]", "").toLowerCase();
					if (word.length() > 0) { // to omit the blank words
						fzMap = fCount(word, fzMap);
					}
				}
			}
		}
		br.close();

		/*
		 * To sort the HasMap with respect to value
		 */
		class NewObjectComparator implements Comparator<String> {

			HashMap<String, Integer> base;

			public NewObjectComparator(HashMap<String, Integer> base) {
				this.base = base;
			}

			public int compare(String first, String second) {
				if (base.get(first) >= base.get(second)) {
					return -1;
				} else {
					return 1;
				}
			}
		}

		NewObjectComparator comp = new NewObjectComparator(fzMap);
		TreeMap<String, Integer> tempTreeMap = new TreeMap<String, Integer>(
				comp);
		tempTreeMap.putAll(fzMap);

		/*
		 * To take in the user input and display the statistics.
		 */
		int option = 0;
		int totalWords = 0;
		while (true) {
			System.out
					.println("\n\nEnter your choice:\n1. Print First 30 Words\n2. Number of Unique Words\n3. Number of Words occuring Once, Twice and Thrice\n4. Exit");
			option = input.nextInt();
			if (option == 1) {
				int countTillTen = 0;
				for (Map.Entry<String, Integer> entry : tempTreeMap.entrySet()) {
					if (countTillTen == 30) {
						break;
					} else {
						String key = entry.getKey();
						Integer value = entry.getValue();
						countTillTen++;
						System.out.println(key + ":" + value);
					}
				}
			} else if (option == 2) {
				System.out.println("The Number of Unique Words is:"
						+ fzMap.size());
			} else if (option == 3) {
				int countOneWords = 0;
				int countTwoWords = 0;
				int countThreeWords = 0;
				for (Map.Entry<String, Integer> entry : tempTreeMap.entrySet()) {
					Integer value = entry.getValue();
					totalWords += value;
					if (value == 1) {
						countOneWords++;
					} else if (value == 2) {
						countTwoWords++;
					} else if (value == 3) {
						countThreeWords++;
					}
				}

				System.out.println("No. of words occuring Once: "
						+ countOneWords + "\nNo. of words occuring twice: "
						+ countTwoWords + "\nNo. of words occuring twice: "
						+ countThreeWords+ "\nTotal no. of words: "
						+ totalWords);
			}	
			else if (option == 4) {
				System.out.println("Good Bye!!");
				System.exit(0);
			}
		}
	}
}