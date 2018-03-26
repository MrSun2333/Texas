package basic;

public class Incident {
	private Person person;
	private Action action;
	private int bet;// 押注

	public Incident(Person person, int bet, Action action) {
		super();// 默认继承Object这个类，super()在此处调用Object类的方法
		this.person = person;
		this.bet = bet;
		this.action = action;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public int getBet() {
		return bet;
	}

	public void setBet(int bet) {
		this.bet = bet;
	}

	public Action getAction() {
		return action;
	}

	public void setAction(Action action) {
		this.action = action;
	}
}
