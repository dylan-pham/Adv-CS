public class Account implements Comparable<Account> {
    private String lastName;
    private String firstName;
    private int pin;
    private double accountBalance;

    public Account(String lastName, String firstName, int pin, double accountBalance) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.pin = pin;
        this.accountBalance = accountBalance;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getFullName() {
        return lastName + ", " + firstName;
    }

    public int getPin() {
        return pin;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }

    public void deposit(double amount) {
        accountBalance += amount;
    }

    public void withdraw(double amount) {
        accountBalance -= amount;
    }

    public boolean equals(Object o) {
        Account a = (Account)o;

        return lastName.equals(a.getLastName()) && firstName.equals(a.getFirstName());
    }

    public int compareTo(Account a) {
        if (lastName.equals(a.getLastName())) {
            return firstName.compareTo(a.getFirstName());
        }

        return lastName.compareTo(a.getLastName());
    }

    public String toString() {
        return lastName + ", " + firstName + "\nPin: " + pin + "\nBalance: $" + accountBalance + "\n";
    }
}