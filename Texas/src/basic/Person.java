package basic;

public class Person implements Comparable<Person>{
	private String id;
	private int jetton;// ³ïÂë
	
	public Person(String name, int jetton) {
		this.id = name;
		this.jetton = jetton;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	// Ê£Óà³ïÂë
	public int getJetton() {
		return jetton;
	}
	
	public void setJetton(int jetton) {
		this.jetton = jetton;
	}

	@Override
	public int compareTo(Person o) {
		return (this.jetton < o.jetton)?-1:1;
	}
}
