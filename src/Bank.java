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
        checkUniquenessOfAccount(accountName, pin);
        validateBvn(bvn);
        newAccount.setName(accountName);
        newAccount.setBvn(bvn);
        newAccount.setPin(pin);
        newAccount.setAccountNumber();
        accounts.add(newAccount);
    }

    public void deposit(long accountNumber, double amount){

        if (amount <= 0.0) throw new IllegalArgumentException("Deposit Amount must be greater than #0.00!");
        findAccount(accountNumber).deposit(amount);
    }


    public void withdraw(long accountNumber, double amount, String pin) {

        if (amount <= 0.0) throw new IllegalArgumentException("Withdrawal Amount must be greater than #0.00!");
        findAccount(accountNumber, pin).withdraw(amount);

    }

    public void transfer(long sourceAccountNumber, long destinationAccountNumber, double amount, String pin){
        if (sourceAccountNumber == destinationAccountNumber) {
            throw new IllegalArgumentException("Source and Destination Accounts Cannot Be Same!");
        }
        withdraw(sourceAccountNumber, amount, pin);
        deposit(destinationAccountNumber, amount);
    }

    public int getNumberOfAccounts(){
     return accounts.size();
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
        double balance = findAccount(accountNumber, pin).getBalance();

        return balance;
    }

    public void closeAccount(long accountNumber, String pin) {
                accounts.remove(findAccount(accountNumber, pin));
            }

    public void validateBvn(String bvn) {
        for (Account account : accounts) {
            if (account.getBvn().equals(bvn)) {
                throw new IllegalArgumentException("BVN already registered to another account!");
            }
        }
    }

    public void checkUniquenessOfAccount(String accountName, String pin) {
            if (findAccount(accountName, pin)) throw new IllegalArgumentException("Account Details Already Registered To Another Customer");
    }

    public Account findAccount(long accountNumber, String pin) {
        for (Account account1 : accounts) {
            if (account1.getAccountNumber() == accountNumber && account1.getPin().equals(pin)) return account1;
        }
        throw new IllegalArgumentException("Invalid Credentials! Try again later..");
    }

    public Account findAccount(long accountNumber) {
        for (Account account1 : accounts) {
            if (account1.getAccountNumber() == accountNumber) return account1;
        }
        throw new IllegalArgumentException("Invalid Account Details! Try again later..");
    }

    public boolean findAccount(String accountName, String pin) {
        for (Account account: accounts) {
            if (account.getName().equals(accountName) && account.getPin().equals(pin)) return true;
        }
        return false;
    }



}
