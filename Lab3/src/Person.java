

class Person {
	private int age;
	private String name;

	public Person(int myAge, String myName) {
		this.age = myAge;
		this.name = myName;
	}
	
	public String talk(){
		return "I'm "+name+" and I'm "+age+" years old!";
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

}