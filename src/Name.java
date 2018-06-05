//Class for each name object
public class Name {
	String name; //String for the name
	String gender; //String for the gender
	int count; //Count value for the name
	int rank; //Rank relative to other names with that gender
	Name root;
	public Name getRoot() { //Getter
		return root;
	}

	public void setRoot(Name root) { //Setter
		this.root = root;
	}

	public Name getLeftChild() { //Getter
		return leftChild;
	}

	public void setLeftChild(Name leftChild) { //Setter
		this.leftChild = leftChild;
	}

	public Name getRightChild() { //Getter
		return rightChild;
	}

	public void setRightChild(Name rightChild) { //Setter
		this.rightChild = rightChild;
	}

	Name leftChild; //Left child for tree data structure
	Name rightChild; //Right child for tree data structure
	
	//Constructor method for the name class, sets the name, gender, count, and rank
	public Name(String name, String gender, int count, int rank ) {
		this.name = name;
		this.gender = gender;
		this.count = count;
		this.rank = rank;
	}

	public int getRank() { //Getter
		return rank;
	}

	public void setRank(int rank) { //Setter
		this.rank = rank;
	}

	public String getName() { //Getter
		return name;
	}

	public void setName(String name) { //Setter
		this.name = name;
	}

	public String getGender() { //Getter
		return gender;
	}

	public void setGender(String gender) { //Setter
		this.gender = gender;
	}

	public int getCount() { //Getter
		return count;
	}

	public void setCount(int count) { //Setter
		this.count = count;
	}
}
