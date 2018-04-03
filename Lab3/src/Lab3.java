import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Lab3 {

	public static void main(String[] args) {
		Person me = new Person(20, "Anisha");
		me.talk();
		//Declare a bunch of variables
		Person person;
		List<Person> list = new ArrayList<Person>();
		Scanner sc = new Scanner(System.in);
		int number;
		String name;
		int age;

		//Prompt user
		System.out.println("How many people do you want to make?");
		number = getInt(sc);
		
		//Make the people
		for (int i = 1; i <= number; i++) {
			System.out.println("Enter person "+i+"'s name:");
			name = sc.next();
			
			System.out.println("Enter person "+i+"'s age:");
			age = getInt(sc);
			person = new Person(age, name);
			list.add(person);
		}
		
		//Iterate over the Person list using an enhanced for loop
		for( Person human : list ){
			System.out.println(human.talk());
		}
		
		//Iterate over the Person list using an ordinary for loop
		for(int i = 0; i < list.size() ; i++){
			System.out.println("Person "+i+" is "+list.get(i).getAge()+" years old");
		}

	}
	
	//Just a simple helper function which returns an int
	private static int getInt(Scanner sc){
		while (!sc.hasNextInt()) {
			sc.next();
			System.out.println("Please enter an integer:");
		}
		return sc.nextInt();
	}

}