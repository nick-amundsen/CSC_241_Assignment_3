import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class BabyNames {

	
	public static void main(String[] args) {
		if( args.length != 1 ) {	//Checks for the correct number of arguments, prints something out if incorrect
			System.err.println("Error: Wrong number of arguments");
			System.exit(1);
		}
		String filename = args[0]; //The name of the file
		String year = filename.replaceAll("[^0-9]", ""); //Replaces all characters that arent numbers with nothing, pulling out the year
		String dataTypeChoice; //The string that will become the choice of data type
		String informationChoice; //The string that will become the choice of information
		int totalCountFemale = SortNames.countWords(SortNames.loadToArrayList(filename), 0); //The total number of female words that were counted in the file
		int totalCountMale = SortNames.countWords(SortNames.loadToArrayList(filename), 1); //The total number of male words that were counted in the file
		String dataStructure = null; //Initiate the variable
		ArrayList<Name> namesList = null; //Initiate the variable
		HashMap<String, Name> femaleNamesHashMap = null; //Initiate the map
		HashMap<String, Name> maleNamesHashMap = null; //Initiate the map
		NameTree bstAlphabetically = null; //BST for alphabetically sorted names of both genders
		NameTree bstCountMale = null; //BST for count sorted names that are male
		NameTree bstCountFemale = null; //BST for count sorted names that are female
		Scanner scan = new Scanner(System.in); //Console scanner for user input
		int quit = 0; //When this equals 1, the program quits
		while (quit != 1) { //Loop that will ask the user for input
			System.out.println("Please select a data type (1= tree, 2 = hash map, and 3= linear data structure) and the information");
			System.out.println("you would like to know  (1 = Search for a name, 2 = MostPopularName, and 3 = Show Name Alphabetically)");
			System.out.println("seperated by a space. Ex. 2 3");
			String choice = scan.nextLine(); //String to be split into two choices
			dataTypeChoice = choice.replaceAll(" .*", ""); //The choice for datatype
			informationChoice = choice.replaceAll(".* ", ""); //The choice for information wanted
			if (dataTypeChoice.equals("1")) { //First choice is tree
				dataStructure = "Tree";
				bstAlphabetically = SortNames.loadToNameTree(filename, 1, null);
				bstCountFemale = SortNames.loadToNameTree(filename, 0, "F");
				bstCountMale = SortNames.loadToNameTree(filename, 0, "M");
				
			} else if (dataTypeChoice.equals("2")) { //Second choice is hashmap
				dataStructure = "Hash Map";
				femaleNamesHashMap = SortNames.loadToHashMap(filename, 0);
				maleNamesHashMap = SortNames.loadToHashMap(filename, 1);
				
			} else if (dataTypeChoice.equals("3")) { //Third choice is linear data structure, or arraylist in this case
				dataStructure = "ArrayList";
				namesList = SortNames.loadToArrayList(filename);
				
			} else {
				System.out.println("Error: Wrong data type input, please try again"); //If the input for data type is incorrect then it asks again
				
				
			} if (informationChoice.equals("1") && dataTypeChoice.equals("1")) { //Tree and search name
				String NameTree = null; //The string that will either quit out of searching or not
				do {
					System.out.println("Please enter the name you would like to search for:");
					String name = scan.nextLine(); //The name to be searched for
					Name maleName = bstCountMale.searchNameTree(bstCountMale.root, name); //Male name searched for
					Name femaleName = bstCountFemale.searchNameTree(bstCountFemale.root, name); //Female name searched for
					if (femaleName == null && maleName == null) { //If they are both null, then it hasn't found one
						System.out.println("Name not found, please enter \"continue\" or \"quit\" to start again");
						NameTree = scan.nextLine(); //Try again or 'quit' tells the program to start over
					} else { //Or it prints out the found name or names
						System.out.println("Selected Data Structure: " + dataStructure);
						System.out.println();
						System.out.println("Selected Name : " + name);
						System.out.println();
						if (femaleName != null && maleName != null) {
							System.out.println("Year Male Rank-Male Female Rank-Female ");
							System.out.println(year +"    "+ maleName.getCount() +"   "+ maleName.getRank() +"   "+ femaleName.getCount() +"   "+ femaleName.getRank());
						} else if (femaleName == null && maleName != null) {
							System.out.println("Year Male Rank-Male");
							System.out.println(year +"    "+ maleName.getCount() +"   "+ maleName.getRank());
						} else {
							System.out.println("Year Female Rank-Female");
							System.out.println(year +"    "+ femaleName.getCount() +"   "+ femaleName.getRank());
						}
						System.out.println();
						System.out.println("Enter \"quit\" to quit or \"continue\" to search for another:"); //Asks to search again or quit
						NameTree = scan.nextLine();
					}
				} while (!NameTree.equals("quit")) ; //While the choice string isnt 'quit'
				
				
			} else if (informationChoice.equals("2") && dataTypeChoice.equals("1")) { //Tree and popular names
				System.out.println("Selected Data Structure: " + dataStructure);
				System.out.println();
				System.out.println("Year: " + year);
				System.out.println();
				NumberFormat formatter = new DecimalFormat("0.00000000000");
				System.out.println("Female name   Frequency      %"); //Table for the information of female names
				bstCountFemale.mostPopularNameTree(bstCountFemale.getRoot(), 0, totalCountFemale, formatter);
				System.out.println();
				System.out.println("Male name     Frequency      %"); //Table for the information of male names
				bstCountMale.mostPopularNameTree(bstCountMale.getRoot(), 0, totalCountMale, formatter);
				System.out.println();
				
			} else if (informationChoice.equals("3") && dataTypeChoice.equals("1")) { //Tree and alphabetically sorted names
				System.out.println("Selected Data Structure: " + dataStructure);
				System.out.println();
				System.out.println("Year: " + year);
				System.out.println();
				System.out.println("Name   Gender  Frequency      %      ");
				NumberFormat formatter = new DecimalFormat("0.00000000000");
				bstAlphabetically.ShowNameAlphabeticallyNameTree(bstAlphabetically.getRoot(), totalCountFemale, totalCountMale, formatter);
				
				
			} else if (informationChoice.equals("1") && dataTypeChoice.equals("2")) { //Hashmap and search for name
				String HashMapChoice = null; //The string that will either quit out of searching or not
				do {
					System.out.println("Please enter the name you would like to search for:");
					String name = scan.nextLine(); //The name to be searched for
					Name maleName = SortNames.searchNameHashMap(name, maleNamesHashMap); //Male name searched for
					Name femaleName = SortNames.searchNameHashMap(name, femaleNamesHashMap); //Female name searched for
					if (femaleName == null && maleName == null) { //If they are both null, then it hasnt found one
						System.out.println("Name not found, please enter \"continue\" or \"quit\" to start again");
						HashMapChoice = scan.nextLine(); //Try again or 'quit' tells the program to start over
					} else { //Or it prints out the found name or names
						System.out.println("Selected Data Structure: " + dataStructure);
						System.out.println();
						System.out.println("Selected Name : " + name);
						System.out.println();
						if (femaleName != null && maleName != null) {
							System.out.println("Year Male Rank-Male Female Rank-Female ");
							System.out.println(year +"   "+ maleName.getCount() +"     "+ maleName.getRank() +"     "+ femaleName.getCount() +"     "+ femaleName.getRank());
						} else if (femaleName == null && maleName != null) {
							System.out.println("Year Male Rank-Male");  
							System.out.println(year +"   "+ maleName.getCount() +"     "+ maleName.getRank());
						} else {
							System.out.println("Year Female Rank-Female");
							System.out.println(year +"   "+ femaleName.getCount() +"     "+ femaleName.getRank());
						}
						System.out.println();
						System.out.println("Enter \"quit\" to quit or \"continue\" to search for another:");//Asks to search again or quit
						HashMapChoice = scan.nextLine();
					}

				} while (!HashMapChoice.equals("quit")) ; //While the choice string isnt 'quit'
				
				
			} else if (informationChoice.equals("2") && dataTypeChoice.equals("2")) { //Hashmap and top 10 names
				SortNames.mostPopularHashMap(year, dataStructure, maleNamesHashMap, femaleNamesHashMap, totalCountFemale, totalCountMale); //The mostpopular names method for hashmaps
		
				
			} else if (informationChoice.equals("3") && dataTypeChoice.equals("2")) { //Hashmap and names alphabetically
				SortNames.ShowNameAlphabeticallyHashMap(year, dataStructure, maleNamesHashMap, femaleNamesHashMap, totalCountFemale, totalCountMale); //The alphabetic naames method for hashmaps
				
				
			} else if (informationChoice.equals("1") && dataTypeChoice.equals("3")) { //Linear data structure and search for name
					String ArrayChoice = null; //Tells the program to search again or quit
					do {
						System.out.println("Please enter the name you would like to search for:");
						String name = scan.nextLine(); //Name to be searched for
						Name[] femaleMaleArray = SortNames.searchNameArrayList(name, namesList); //Returns an array with the male and female names if they exist
						if (femaleMaleArray[0] == null && femaleMaleArray[1] == null) { //If they are both null, ask to quit or search again
							System.out.println("Name not found, please enter \"continue\" or \"quit\" to start again");
							ArrayChoice = scan.nextLine();
						} else { //Or it prints out the name or names
							System.out.println("Selected Data Structure: " + dataStructure);
							System.out.println();
							System.out.println("Selected Name : " + name);
							System.out.println();
							if (femaleMaleArray[0] != null && femaleMaleArray[1] != null) {
								System.out.println("Year Male Rank-Male Female Rank-Female ");
								System.out.println(year +"   "+ femaleMaleArray[1].getCount() +"     "+ femaleMaleArray[1].getRank() +"     "+ femaleMaleArray[0].getCount() +"     "+ femaleMaleArray[0].getRank());
							} else if (femaleMaleArray[0] == null && femaleMaleArray[1] != null) {
								System.out.println("Year Male Rank-Male");
								System.out.println(year +"   "+ femaleMaleArray[1].getCount() +"     "+ femaleMaleArray[1].getRank());
							} else {
								System.out.println("Year Female Rank-Female");
								System.out.println(year +"   "+ femaleMaleArray[0].getCount() +"     "+ femaleMaleArray[0].getRank());
							}
							System.out.println();
							System.out.println("Enter \"quit\" to quit or \"continue\" to search for another:"); //Quit or search again
							ArrayChoice = scan.nextLine();
						}
					} while (!ArrayChoice.equals("quit")) ; //While the choice isnt 'quit'
					
					
			} else if (informationChoice.equals("2") && dataTypeChoice.equals("3")) { //Linear data structure and top 10 names
				SortNames.mostPopularArrayList(year, dataTypeChoice, namesList, totalCountFemale, totalCountMale); //Most popular names method for arraylist
					
				
			} else if (informationChoice.equals("3") && dataTypeChoice.equals("3")) { //Linear data structure and alphabetically sorted list
				SortNames.ShowNameAlphabeticallyArrayList(year, dataTypeChoice, namesList, totalCountFemale, totalCountMale); //Alphabetical names method for arraylist
					
				
			} else {
				System.out.println("Error: Wrong information input, please try again"); //Tells the user the input for type of information was incorrect
			}
		}
	}

}
