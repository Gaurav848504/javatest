abstract class BankAccount {
    private String accountNumber;
    protected double balance;

    public BankAccount(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public void deposit(double amount) {
        balance += amount;
        System.out.println("Deposited " + amount + ". New balance: " + balance);
    }

    public abstract boolean withdraw(double amount);

    public double getBalance() {
        return balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }
}

interface Transferable {
    boolean transferTo(BankAccount recipient, double amount);
}

class SavingsAccount extends BankAccount implements Transferable {
    private static final double MINIMUM_BALANCE = 500;

    public SavingsAccount(String accountNumber, double balance) {
        super(accountNumber, balance);
    }

    @Override
    public boolean withdraw(double amount) {
        if (balance - amount >= MINIMUM_BALANCE) {
            balance -= amount;
            System.out.println("Withdrawal of " + amount + " successful. New balance: " + balance);
            return true;
        }
        System.out.println("Withdrawal failed. Minimum balance of " + MINIMUM_BALANCE + " required.");
        return false;
    }

    @Override
    public boolean transferTo(BankAccount recipient, double amount) {
        if (withdraw(amount)) {
            recipient.deposit(amount);
            System.out.println("Transfer of " + amount + " successful. Savings balance: " + balance + ", Recipient balance: " + recipient.getBalance());
            return true;
        }
        return false;
    }
}

class CurrentAccount extends BankAccount implements Transferable {
    private static final double OVERDRAFT_LIMIT = -5000;

    public CurrentAccount(String accountNumber, double balance) {
        super(accountNumber, balance);
    }

    @Override
    public boolean withdraw(double amount) {
        if (balance - amount >= OVERDRAFT_LIMIT) {
            balance -= amount;
            System.out.println("Withdrawal of " + amount + " successful. New balance: " + balance);
            return true;
        }
        System.out.println("Withdrawal failed. Overdraft limit exceeded.");
        return false;
    }

    @Override
    public boolean transferTo(BankAccount recipient, double amount) {
        if (withdraw(amount)) {
            recipient.deposit(amount);
            System.out.println("Transfer of " + amount + " successful. Current account balance: " + balance + ", Recipient balance: " + recipient.getBalance());
            return true;
        }
        return false;
    }
}

public class question2 {
    public static void main(String[] args) {
        SavingsAccount savings = new SavingsAccount("SAV123", 5000);
        CurrentAccount current = new CurrentAccount("CUR456", 2000);

        savings.deposit(1000);
        current.withdraw(3000);

        savings.transferTo(current, 1500);
        current.transferTo(savings, 6000);
    }
}
