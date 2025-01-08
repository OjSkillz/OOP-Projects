import java.util.ArrayList;
import java.util.List;

public class Bank {

    private String name;
    private List<Account> accounts = new ArrayList<Account>();

    public void setName(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void createNewAccount(String accountName, String bvn, String pin){
        Account newAccount = new Account();
        newAccount.setName(accountName);
        validateBvn(bvn);
        newAccount.setBvn(bvn);
        newAccount.setPin(pin);
        newAccount.setAccountNumber();
        accounts.add(newAccount);
    }

    public void deposit(long accountNumber, double amount){
        boolean match = false;
        if (amount <= 0.0) throw new IllegalArgumentException("Deposit Amount must be greater than #0.00!");
        for (Account account : accounts) {
            if (account.getAccountNumber() == accountNumber) {
                match = true;
                account.deposit(amount);
            }
        } if (match == false) {
            throw new IllegalArgumentException("Invalid Account Number!");
        }
    }

    public void withdraw(long accountNumber, double amount, String pin) {
        boolean match = false;
        if (amount <= 0.0) throw new IllegalArgumentException("Withdrawal Amount must be greater than #0.00!");
        for (Account account : accounts) {
            if (account.getAccountNumber() == accountNumber && account.getPin().equals(pin)) {
                match = true;
                account.withdraw(amount);
            }
        } if (match == false) {
            throw new IllegalArgumentException("Invalid Credentials. Check Account Number or Pin!");
        }

    }

    public void transfer(long sourceAccountNumber, long destinationAccountNumber, double amount, String pin){
        withdraw(sourceAccountNumber, amount, pin);
        deposit(destinationAccountNumber, amount);

        if (sourceAccountNumber == destinationAccountNumber) {
            throw new IllegalArgumentException("Source and Destination Accounts Cannot Be Same!");
        }
    }

    public int getNumberOfAccounts(){
     int numberOfAccounts = 0;
        for (Account account : accounts) {
         numberOfAccounts++;
     }
        return numberOfAccounts;
    }

    public long getAccountNumber(String accountName, String pin) {
        long accountNumber = 0;
        for (Account account : accounts) {
            if (account.getName().equals(accountName) && account.getPin().equals(pin)) {
                accountNumber = account.getAccountNumber();
            }
        }
        if (accountNumber == 0 ) throw new IllegalArgumentException("Invalid Credentials!");
        return accountNumber;
    }

    public double displayBalance(long accountNumber, String pin) {
        double balance = 0;
        for (Account account : accounts) {
            if (account.getAccountNumber() == accountNumber && account.getPin().equals(pin)) {
              balance = account.getBalance();
            }
        }
        return balance;
    }

    public void validateBvn(String bvn) {
        for (Account account : accounts) {
            if (account.getBvn().equals(bvn)) {
                throw new IllegalArgumentException("BVN already registered to another account!");
            }
        }
    }



}
