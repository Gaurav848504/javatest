abstract class BankAccount {
    String accountNumber;
    double balance;

    public BankAccount(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public abstract boolean withdraw(double amount);
}

interface Transaction {
    boolean transfer(BankAccount toAccount, double amount);
}

class SavingsAccount extends BankAccount implements Transaction {
    public SavingsAccount(String accountNumber, double balance) {
        super(accountNumber, balance);
    }

    public boolean withdraw(double amount) {
        if (balance - amount >= 500) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public boolean transfer(BankAccount toAccount, double amount) {
        if (withdraw(amount)) {
            toAccount.deposit(amount);
            return true;
        }
        return false;
    }
}

class CurrentAccount extends BankAccount implements Transaction {
    private static final double OVERDRAFT_LIMIT = 5000;

    public CurrentAccount(String accountNumber, double balance) {
        super(accountNumber, balance);
    }

    public boolean withdraw(double amount) {
        if (balance - amount >= -OVERDRAFT_LIMIT) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public boolean transfer(BankAccount toAccount, double amount) {
        if (withdraw(amount)) {
            toAccount.deposit(amount);
            return true;
        }
        return false;
    }
}
class question2 {
    public static void main(String[] args) {
        SavingsAccount savings = new SavingsAccount("SA123", 1000);
        CurrentAccount current = new CurrentAccount("CA456", 2000);

        System.out.println("Initial Balances:");
        System.out.println("Savings: " + savings.balance);
        System.out.println("Current: " + current.balance);

        savings.deposit(500);
        current.withdraw(300);

        System.out.println("Balances after transactions:");
        System.out.println("Savings: " + savings.balance);
        System.out.println("Current: " + current.balance);

        savings.transfer(current, 200);

        System.out.println("Balances after transfer:");
        System.out.println("Savings: " + savings.balance);
        System.out.println("Current: " + current.balance);
    }
}
