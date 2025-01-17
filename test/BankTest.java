import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BankTest {
    @Test
    public void testThatBankExists() {
        Bank bank = new Bank();
        bank.setName("Loci Bank");
        assertEquals("Loci Bank", bank.getName());
    }
    @Test
    public void testThatBankCanCreateNewAccount() {
        Bank bank = new Bank();
        bank.createNewAccount("Adeboye Olodo", "23456645456767", "1234");
        assertEquals(1, bank.getNumberOfAccounts());
    }

    @Test
    public void testThatPinLengthMustBeFour(){
        Bank bank = new Bank();
        assertThrows(IllegalArgumentException.class, ()->bank.createNewAccount("Adeboye Olodo", "23456645456767", "541234"));

    }

    @Test
    public void testThatBvnIsUniqueToAccounts(){
        Bank bank = new Bank();
        bank.createNewAccount("Adeboye Olodo", "23456645456767", "1234");
        assertThrows(IllegalArgumentException.class, ()->bank.createNewAccount("Olaoye Deola", "23456645456767", "4568"));

    }

    @Test
    public void testThatNoTwoAccountsCanHaveSameNameAndPin() {
        Bank bank = new Bank();
        bank.createNewAccount("Adeboye Olodo", "23456645456767", "1234");
        assertThrows(IllegalArgumentException.class, ()->bank.createNewAccount("Adeboye Olodo", "23455456767", "1234"));


    }

    @Test
    public void testThatMultipleAccountsCanBeCreated(){
        Bank bank = new Bank();
        bank.createNewAccount("Adeboye Olodo", "23456645456767", "1234");
        bank.createNewAccount("Olaoye Deola", "23456645348897", "4568");
        assertEquals(2, bank.getNumberOfAccounts());
    }

    @Test
    public void testThatBankCanPerformDeposit(){
        Bank bank = new Bank();
        bank.createNewAccount("Olaoye Deola", "23456645348897", "4568");
        bank.createNewAccount("John Doe", "23458348897", "4324");
        bank.createNewAccount("Bisi Tedela", "156645348897", "9908");
        bank.createNewAccount("Made Oriola", "00456734354546", "7821");

        long accountNumber = bank.getAccountNumber("Made Oriola","7821");
        bank.deposit( accountNumber, 50000);
        assertEquals(50000.00, bank.displayBalance(accountNumber,"7821"));
    }

    @Test
    public void testThatBankCantPerformDepositWithWrongAccountNumber(){
        Bank bank = new Bank();
        bank.createNewAccount("Olaoye Deola", "23456645348897", "4568");
        long accountNumber = 75468865257L;

        assertThrows(IllegalArgumentException.class, ()->bank.deposit( accountNumber, 50000));
    }

    @Test
    public void testThatBankCantPerformDepositWithNonExistentAccountNumber(){
        Bank bank = new Bank();
        bank.createNewAccount("Olaoye Deola", "23456645348897", "4568");
        bank.createNewAccount("John Doe", "23458348897", "4324");
        bank.createNewAccount("Bisi Tedela", "156645348897", "9908");
        bank.createNewAccount("Made Oriola", "00456734354546", "7821");

        long accountNumber = 75468865257L;
        assertThrows(IllegalArgumentException.class, ()->bank.deposit( accountNumber, 45000));
    }

    @Test
    public void testThatBankCanPerformWithdrawal() {
        Bank bank = new Bank();
        bank.createNewAccount("John Doe", "23458348897", "4324");
        bank.createNewAccount("Bisi Tedela", "156645348897", "9908");
        bank.createNewAccount("Made Oriola", "00456734354546", "7821");

        long accountNumber = bank.getAccountNumber("Made Oriola","7821");

        bank.deposit( accountNumber, 55000);

        bank.withdraw( accountNumber, 25000, "7821");
        assertEquals(30000.00, bank.displayBalance(accountNumber,"7821"));

    }

    @Test
    public void testThatBankCantPerformWithdrawalWithWrongAccountNumber(){
        Bank bank = new Bank();
        bank.createNewAccount("John Doe", "23458348897", "4324");
        bank.createNewAccount("Bisi Tedela", "156645348897", "9908");

        long accountNumber = 75468865257L;

        assertThrows(IllegalArgumentException.class, ()->bank.withdraw( accountNumber, 50000, "9908"));
    }

    @Test
    public void testThatBankCantPerformWithdrawalWithWrongPin(){
        Bank bank = new Bank();
        bank.createNewAccount("John Doe", "23458348897", "4324");
        bank.createNewAccount("Bisi Tedela", "156645348897", "9908");

        long accountNumber = bank.getAccountNumber("Bisi Tedela","9908");

        assertThrows(IllegalArgumentException.class, ()->bank.withdraw( accountNumber, 50000, "9957"));
    }

    @Test
    public void testThatUserCannotWithdrawMoreThanBalance() {
        Bank bank = new Bank();
        bank.createNewAccount("John Doe", "23458348897", "4324");

        long accountNumber = bank.getAccountNumber("John Doe", "4324");

        bank.deposit( accountNumber, 55000);

        assertThrows(IllegalArgumentException.class, ()->bank.withdraw( accountNumber, 100000, "4324"));

    }

    @Test
    public void testThatBankCanTransfer() {
        Bank bank = new Bank();

        bank.createNewAccount("Ali Munguno", "6367429943743", "5622");
        bank.createNewAccount("Jess Glyne", "4738454999343", "9090");

        long sourceAccountNumber = bank.getAccountNumber("Jess Glyne", "9090");
        long destinationAccountNumber = bank.getAccountNumber("Ali Munguno", "5622");

        bank.deposit(sourceAccountNumber, 55000);
        bank.transfer(sourceAccountNumber, destinationAccountNumber, 30000, "9090");

        assertEquals(25000,bank.displayBalance(sourceAccountNumber,"9090"));

        assertEquals(30000,bank.displayBalance(destinationAccountNumber,"5622"));
    }

    @Test
    public void testThatBankCantTransferWithNonExistentSourceAccountNumber(){
        Bank bank = new Bank();

        bank.createNewAccount("Ali Munguno", "6367429943743", "5622");
        bank.createNewAccount("Jess Glyne", "4738454999343", "9090");

        long sourceAccountNumber = bank.getAccountNumber("Jess Glyne", "9090");
        long destinationAccountNumber = bank.getAccountNumber("Ali Munguno", "5622");

        bank.deposit(sourceAccountNumber, 55000);
        assertThrows(IllegalArgumentException.class, ()-> bank.transfer(45588348829947L, destinationAccountNumber, 30000, "9090"));

    }

    @Test
    public void testThatBankCantTransferWithNonExistentDestinationAccountNumber(){
        Bank bank = new Bank();

        bank.createNewAccount("Ali Munguno", "6367429943743", "5622");
        bank.createNewAccount("Jess Glyne", "4738454999343", "9090");

        long sourceAccountNumber = bank.getAccountNumber("Jess Glyne", "9090");

        bank.deposit(sourceAccountNumber, 55000);
        assertThrows(IllegalArgumentException.class, ()-> bank.transfer(sourceAccountNumber, 45588348829947L, 30000, "9090"));
    }

    @Test
    public void testThatBankCantTransferBetweenSameAccounts() {
        Bank bank = new Bank();

        bank.createNewAccount("Jess Glyne", "4738454999343", "9090");
        long sourceAccountNumber = bank.getAccountNumber("Jess Glyne", "9090");

        bank.deposit(sourceAccountNumber, 55000);

        assertThrows(IllegalArgumentException.class, ()-> bank.transfer(sourceAccountNumber, sourceAccountNumber, 30000, "9090"));

    }

    @Test
    public void testThatBankCanDisplayBalance() {
        Bank bank = new Bank();

        bank.createNewAccount("Jess Glyne", "4738454999343", "9090");

        long sourceAccountNumber = bank.getAccountNumber("Jess Glyne", "9090");

        bank.deposit(sourceAccountNumber, 55000);

        assertEquals(55000, bank.displayBalance(sourceAccountNumber,"9090"));
    }

    @Test
    public void testThatBankCanCloseAccount() {
        Bank bank = new Bank();

        bank.createNewAccount("Jess Glyne", "4738454999343", "9090");
        bank.createNewAccount("Bisi Tedela", "156645348897", "9908");
        long accountNumber = bank.getAccountNumber("Jess Glyne", "9090");
        bank.closeAccount(accountNumber, "9090");
        assertThrows(IllegalArgumentException.class, ()-> bank.findAccount(accountNumber));



    }
}