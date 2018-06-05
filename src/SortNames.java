import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;


public class SortNames {
	
	//Loads the text file into an ArrayList of name objects 
	public static ArrayList<Name> loadToArrayList(String filename) {
		ArrayList<Name> namesList = new ArrayList<Name>();
		try { //Try to open the text file, throw exception if unable to do so
    		FileReader fr = new FileReader(filename); //Opens a file reader to convert the text file into an array
    		BufferedReader br = new BufferedReader(fr); //This reader is used to read the lines of the file
    		String nameInfo[]; //Will be input for name
    		String str;
    		int maleCount = 0;
    		int femaleCount = 0;
    		Name n;
    		while((str = br.readLine())!=null){ //Continue while there are lines to read
    			nameInfo = str.split(",");
    			if (nameInfo[1].equals("F")) {
    				femaleCount++; //Rank of female name
    				n = new Name(nameInfo[0],nameInfo[1],Integer.parseInt(nameInfo[2]), femaleCount);//Female name object to be added to arraylist
    			} else {
    				maleCount++; //Rank of male name
    				n = new Name(nameInfo[0],nameInfo[1],Integer.parseInt(nameInfo[2]), maleCount); //Male name object to be added to arraylist
    			}
    			namesList.add(n); //Adds name to arraylist
    		}
    		fr.close(); //Close reader
    	} catch (FileNotFoundException e) {
    		System.out.println("Error: Unable to open file " + filename); //Prints out the error message if the file cannot be found
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
		return namesList;
	}
	
	//Searches an array list for a name, if the name is both male and female it returns both
	public static Name[] searchNameArrayList(String name, ArrayList<Name> namesList) {
		Name[] femaleMaleArray = new Name[2]; //Array of the male and female names to be returned
		for (Name n : namesList) { //Iterates through every name in the arraylist
			if (n.getName().equalsIgnoreCase(name)) { //If the name is the same as the one requested, then it will discern if it is male or female
				if(n.getGender().equals("F")) {
					femaleMaleArray[0] = n; //Adds it to the female slot in the array
				} else if (n.getGender().equals("M")) {
					femaleMaleArray[1] = n; //Adds it to the male slot in the array
				}
			}
		}
		return femaleMaleArray; //Returns the array with the male and female names if they exist
	}
	
	//Counts the total words that were recorded in the file, for the percentages
	public static int countWords(ArrayList<Name> namesList, int choice) {
		int count = 0;
		if (choice == 0) { //Counts female names if the choice is 0
			for(int i = 0; i < namesList.size(); i++) { //Iterates through every name in the lsit
				if (namesList.get(i).getGender().equals("F")) {
					count = count + namesList.get(i).getCount(); //Adds up every name count in the file
				}
			}
		}
		if (choice == 1) { //Couunts male names if the choice is 1
			for(int i = 0; i < namesList.size(); i++) { //Iterates through every name in the list
				if (namesList.get(i).getGender().equals("M")) {
					count = count + namesList.get(i).getCount(); //Adds up every name count in the file
				}
			}
		}
		return count; //Returns the count
	}
	
	//Loads the file of names into a hashmap
	public static HashMap<String,Name> loadToHashMap(String filename, int choice) {
		HashMap<String,Name> namesHashMap = new HashMap<String,Name>();
		try { //Try to open the text file, throw exception if unable to do so
    		FileReader fr = new FileReader(filename); //Opens a file reader to convert the text file into a hashmap
    		BufferedReader br = new BufferedReader(fr); //This reader is used to read the lines of the file
    		String nameInfo[]; //Will be input for name
    		String str;
    		int maleCount = 0;
    		int femaleCount = 0;
    		Name n = null;;
    		while((str = br.readLine())!=null){ //Continue while there are lines to read
    			nameInfo = str.split(",");
    			if (nameInfo[1].equals("F") && choice == 0) { //Creates a female names hashmap if choice is 0
    				femaleCount++; //Rank of female name
    				n = new Name(nameInfo[0],nameInfo[1],Integer.parseInt(nameInfo[2]), femaleCount);//Female name object to be added to hashmap
    				namesHashMap.put(n.getName(),n); //Adds name object with name key to hashmap
    			} else if (nameInfo[1].equals("M") && choice == 1){ //Creates a male names hashmap if choice is 1
    				maleCount++; //Rank of male name
    				n = new Name(nameInfo[0],nameInfo[1],Integer.parseInt(nameInfo[2]), maleCount); //Male name object to be added to hashmap
    				namesHashMap.put(n.getName(),n); //Adds name object with name key to hashmap
    			}
    		}
    		fr.close(); //Close reader
    	} catch (FileNotFoundException e) {
    		System.out.println("Error: Unable to open file " + filename); //Prints out the error message if the file cannot be found
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
		return namesHashMap; //Returns hashmap with all the male or female names in it
	}
	
	//Searches a hashmap for a name, if the name is both male and female it returns both
	public static Name searchNameHashMap(String name, HashMap<String, Name> namesHashMap) {
		Name foundName = null; //The name to be returned
		for (String n : namesHashMap.keySet()) { //Goes through every key in the keyset
			if (n.equalsIgnoreCase(name)) {
				foundName = namesHashMap.get(n); //Sets the foundname as the name object with the same .getname()
			}
		}
		return foundName; //Returns the name or null if it doesnt exist
	}
	
	//Prints out the 10 most popular names using the hashmap data structure
	public static void mostPopularHashMap (String year, String dataStructure, HashMap<String,Name> maleNamesHashMap, HashMap<String,Name> femaleNamesHashMap, int totalCountFemale, int totalCountMale) {
		System.out.println();
		System.out.println("Female name   Frequency      %      Male name   Frequency      %"); //The table of name information
		Name[] names = new Name[2]; //The male and female names to be printed
		NumberFormat formatter = new DecimalFormat("0.00000000000"); //Formats the number so that it uses decimals instead of E-n 
		for(int i = 1; i <= 10; i++) {
			for(String n : maleNamesHashMap.keySet()) {
				if(maleNamesHashMap.get(n).getRank() == i) { //Checks that the rank equals i which is between 1 and 10
					names[1] = maleNamesHashMap.get(n);
				} 
			}
			for (String k : femaleNamesHashMap.keySet()) {
				if (femaleNamesHashMap.get(k).getRank() == i) { //Checks that the rank equals i which is between 1 and 10
					names[0] = femaleNamesHashMap.get(k);
				}
			} //Prints out the information for the female and male names with that rank then goes to the next
			System.out.println("  "+ names[0].getName() + "   " + names[0].getCount() + "   " + formatter.format(((double)names[0].getCount()/(double)totalCountFemale)*100.0) + "  "+ names[1].getName() + "   " + names[1].getCount() + "   " + formatter.format(((double)names[1].getCount()/(double)totalCountMale)*100.0));
		}
		System.out.println();
	}
	
	//Prints out the 10 most popular names using the arraylist data structure
	public static void mostPopularArrayList (String year, String dataStructure, ArrayList<Name> namesList, int totalCountFemale, int totalCountMale) {
		System.out.println("Selected Data Structure: " + dataStructure);
		System.out.println();
		System.out.println("Year: " + year);
		System.out.println();
		System.out.println("Female name   Frequency      %      Male name   Frequency      %"); //Table for the information of female and male names
		NumberFormat formatter = new DecimalFormat("0.00000000000"); //Formats the number so that it uses decimals instead of E-n 
		Name[] names = new Name[2]; //Array with the female and male names to be displayed
		for(int i = 1; i <= 10; i++) {
			for(Name n : namesList) {
				if (n.getRank() == i && n.getGender().equals("F")) { //Checks that the rank is correct
					names[0] = n;
				}else if(n.getRank() == i && n.getGender().equals("M")) { //Check that the rank is correct
					names[1] = n;
				} 
			} //Prints out the information for the female and male names with that rank then goes to the next
			System.out.println("  "+ names[0].getName() + "   " + names[0].getCount() + "   " + formatter.format(((double)names[0].getCount()/(double)totalCountFemale)*100.0) + "  "+ names[1].getName() + "   " + names[1].getCount() + "   " + formatter.format(((double)names[1].getCount()/(double)totalCountMale)*100.0));
		}
		System.out.println();
	}
	
	//Uses the hashmap data structure to print out every name alphabetically
	public static void ShowNameAlphabeticallyHashMap(String year, String dataStructure, HashMap<String,Name> maleNamesHashMap, HashMap<String,Name> femaleNamesHashMap, int totalCountFemale, int totalCountMale) {
		System.out.println("Selected Data Structure: " + dataStructure);
		System.out.println();
		System.out.println("Year: " + year);
		System.out.println();
		System.out.println("Name   Gender  Frequency      %      "); //Table to be printed under
		List<String> keyList1 = new ArrayList<>(maleNamesHashMap.keySet()); //A list of the male keys
		List<String> keyList2 = new ArrayList<>(femaleNamesHashMap.keySet()); //A list of the female keys
		keyList1.addAll(keyList2); //These two lists are added together
		Collections.sort(keyList1); //And then they are sorted alphabetically
		NumberFormat formatter = new DecimalFormat("0.00000000000"); //Formats the numbers to use decimals instead of E-n
		for (String n : keyList1) { //For every key in the complete sorted list, print out the info if it is female and male or just one, then continues on
			if (femaleNamesHashMap.get(n) != null) {
				System.out.println("  "+ femaleNamesHashMap.get(n).getName() + "       " + femaleNamesHashMap.get(n).getGender() + "   " + femaleNamesHashMap.get(n).getCount() + "   " + formatter.format(((double)femaleNamesHashMap.get(n).getCount()/(double)totalCountFemale)*100.0));
			} else if (maleNamesHashMap.get(n) != null) {
				System.out.println("  "+ maleNamesHashMap.get(n).getName() + "       " + maleNamesHashMap.get(n).getGender() + "   " + maleNamesHashMap.get(n).getCount() + "   " + formatter.format(((double)maleNamesHashMap.get(n).getCount()/(double)totalCountMale)*100.0));
			} else {
				continue;
			}
		}
	}
	
	//Uses the arraylist to show each name alphabetically
	public static void ShowNameAlphabeticallyArrayList(String year, String dataStructure, ArrayList<Name> namesList, int totalCountFemale, int totalCountMale) {
		System.out.println("Selected Data Structure: " + dataStructure);
		System.out.println();
		System.out.println("Year: " + year);
		System.out.println();
		System.out.println("Name   Gender  Frequency      %      "); //Table to be printed onto
		NumberFormat formatter = new DecimalFormat("0.00000000000"); //Formats the numbers to use decimals instead of E-n
		ArrayList<String> sortedNames = new ArrayList<String>(); //A list that will be the sorted names
		for (Name n : namesList) {
			sortedNames.add(n.getName()); //Adds each string name to this arraylist so it can be sorted
		}
		Collections.sort(sortedNames);
		for (String s : sortedNames) { //For every string name it finds its name object and then prints it, takes a long time
			for (Name n : namesList){
				if (n.getGender().equals("F") && n.getName().equals(s)) {
					System.out.println("  "+ n.getName() + "       " + n.getGender() + "   " + n.getCount() + "   " + formatter.format(((double)n.getCount()/(double)totalCountFemale)*100.0));
				} else if (n.getGender().equals("M") && n.getName().equals(s)) {
					System.out.println("  "+ n.getName() + "       " + n.getGender() + "   " + n.getCount() + "   " + formatter.format(((double)n.getCount()/(double)totalCountMale)*100.0));
				} else {
					continue;
				}
			}
		}
	}
	
	//Loads the name from the file into a BST, the int choice decides whether it is alphabetically or by count
	public static NameTree loadToNameTree(String filename, int choice, String gender) {
		NameTree BST = new NameTree();
		try { //Try to open the text file, throw exception if unable to do so
    		FileReader fr = new FileReader(filename); //Opens a file reader to convert the text file into an array
    		BufferedReader br = new BufferedReader(fr); //This reader is used to read the lines of the file
    		String nameInfo[]; //Will be input for name
    		String str;
    		int maleCount = 0;
    		int femaleCount = 0;
    		Name n;
    		while((str = br.readLine())!=null){ //Continue while there are lines to read
    			nameInfo = str.split(",");
    			if (nameInfo[1].equals("F")) {
    				femaleCount++; //Rank of female name
    				n = new Name(nameInfo[0],nameInfo[1],Integer.parseInt(nameInfo[2]), femaleCount);//Female name object to be added to BST
    			} else {
    				maleCount++; //Rank of male name
    				n = new Name(nameInfo[0],nameInfo[1],Integer.parseInt(nameInfo[2]), maleCount); //Male name object to be added to BST
    			}
    			if (choice == 1) {
    				BST.insertAlphabetically(BST.root, n); //Adds name to BST
    			} else {
    				BST.insertByCount(BST.root, n, gender);
    			}
    		}
    		fr.close(); //Close reader
    	} catch (FileNotFoundException e) {
    		System.out.println("Error: Unable to open file " + filename); //Prints out the error message if the file cannot be found
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
		return BST;
		
	}
}
