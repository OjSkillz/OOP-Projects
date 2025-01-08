import java.security.SecureRandom;

public class Account {
    private String customerName;
    private double balance;
    private String bvn;
    private String pin;
    private long accountNumber;

    SecureRandom random = new SecureRandom();

    public void setAccountNumber() {
       accountNumber = 2040706104L + random.nextLong(500) * 1000;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setName(String name) {
        customerName = name;
    }
    public String getName() {
        return customerName;
    }

    public void setBvn(String bvn) {
        this.bvn = bvn;
    }

    public String getBvn() {
        return bvn;
    }
    public void setPin(String pin) {
        if (pin.length() == 4)
            this.pin = pin;
        else throw new IllegalArgumentException("Invalid pin");
    }

    public String getPin() {
        return pin;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public void withdraw(double amount) {
        if (amount > balance) throw new IllegalArgumentException("Insufficient balance");
        else balance -= amount;
    }

    public double getBalance() {
        return balance;
    }
}
