import java.text.NumberFormat;

public class NameTree {
	
	public Name root; //The name object that is the root of the file
	
	public NameTree() { //Constructor for class
		this.root = null; //Begins with null root
	}
	
	//Insterts Name objects into the tree by their count values in descending order
	public void insertByCount(Name node, Name child, String gender) { 
		boolean cont = true; //While continue is true
		if (node == null && child.getGender().equals(gender)) { //If there is no root and the new one matches the required gender, then that becomes the root
			this.setRoot(child);
			cont = false;
		} else if (node == null) { //If it is null but the new one does not match required gender, do not continue
			cont = false;
		}
		while (cont) {
	        if (child.getCount() <= node.getCount() && child.getGender().equals(gender)) { //If it is less than it, make it the left child
	            if (node.getLeftChild() == null){
	                node.setLeftChild(child);
	                break;
	            }
	            else
	                node = node.getLeftChild();
	        }
	        else {
	            if (node.getRightChild() == null){ //Or make it the right child
	                node.setRightChild(child);;
	                break;
	            }
	            else
	                node = node.getRightChild();
	        }
	    }
	}
	
	public Name getRoot() { //Getter
		return root;
	}

	public void setRoot(Name root) { //Setter
		this.root = root;
	}

	//Similar to insert by count but instead compares the two string alphabetically
	public void insertAlphabetically(Name node, Name child) { 
		boolean cont = true;
		if (node == null) { //If the root is null, the child becomes the root
			this.setRoot(child);
			cont = false;
		}
		while (cont) {
	        if (child.getName().compareToIgnoreCase(node.getName()) <= 0) { //Comes before it, put on left side
	            if (node.getLeftChild() == null){
	                node.setLeftChild(child);
	                break;
	            }
	            else
	                node = node.getLeftChild();
	        }
	        else {
	            if (node.getRightChild() == null){ //Or put it on the right side
	                node.setRightChild(child);;
	                break;
	            }
	            else
	                node = node.getRightChild();
	        }
	    }
	}
	
	//Searches through the tree for the requested name
	public Name searchNameTree(Name root, String name) {
		while(root != null){ //While there are still names to search
	        if(root.getName().equalsIgnoreCase(name)){
	           return root; //Return this if it is the one you are looking for
	        } else {
	            Name foundNode = root.getLeftChild();
	            if(foundNode == null) {
	                foundNode = root.getRightChild();
	            } 
	            root = foundNode;
	         }
	    } 
		return root; //Returns null if the root is not found
	}
	
	//Goes in order to print out the first 10 names of the tree
	public void mostPopularNameTree(Name root, int i, int count, NumberFormat formatter) {
		if (i < 10) {
			if (root != null) {
				i++; //This count is what tells the recursion to stop
				System.out.println("  "+ root.getName() + "   " + root.getCount() + "   " + formatter.format(((double)root.getCount()/(double)count)*100.0));
				mostPopularNameTree(root.getLeftChild(), i, count, formatter);
			}
		}
	}
	
	//Prints out every name in the tree alphabetically in order
	public void ShowNameAlphabeticallyNameTree(Name root, int totalCountFemale, int totalCountMale, NumberFormat formatter) {
		if (root != null) {
			ShowNameAlphabeticallyNameTree(root.getLeftChild(), totalCountFemale, totalCountMale, formatter);//Recursive call to continue down left side
			if (root.getGender().equals("F")) {
				System.out.println("  "+ root.getName() + "       " + root.getGender() + "   " + root.getCount() + "   " + formatter.format(((double)root.getCount()/(double)totalCountFemale)*100.0));
			} else {
				System.out.println("  "+ root.getName() + "       " + root.getGender() + "   " + root.getCount() + "   " + formatter.format(((double)root.getCount()/(double)totalCountMale)*100.0));
			} 
			ShowNameAlphabeticallyNameTree(root.getRightChild(), totalCountFemale, totalCountMale, formatter); //Recursive call to continue down right side
		}
	}
}
