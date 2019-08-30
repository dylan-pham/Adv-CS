public class Account {
	private String name;
	private double balance;
	private int pin;
	private boolean access;

	public Account(String name, double balance, int pin) {
		this.name = name;
		this.balance = balance;
		this.pin = pin;
	}
 
	public String getName() {
		return name;	
	}

	public Double getBalance() {
		if (access) {
			return Math.round(balance * 100.0) / 100.0;
		}

		return 0.0;
	}

	public boolean getAccess() {
		return access;
	}

	public void setAccess(int pin, String name) {
		if (this.pin == pin && this.name.equals(name)) {
			access = true;
		}
	}

	public void withdraw(double amount) {
		if (access && balance - amount >= 0) {
			balance -= amount;
		}
	}

	public void deposit(double amount) {
		if (access) {
			balance += amount;
		}
	}

	public void logout() {
		access = false;
	}
}
