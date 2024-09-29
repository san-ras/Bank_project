package lt.techin.bank;

import ibank.Account;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Random;

public class AccountImpl implements Account {

    private BigDecimal balance;
    private final String number;
    private final String holderName;
    private final HashSet<Account> allAccountsOfAllBanks = new HashSet<>();
    private final BigDecimal creditLimit;

    public AccountImpl(String holderName) {
        this.balance = BigDecimal.ZERO;
        this.number = generateNumber();
        this.holderName = holderName;
        this.creditLimit = BigDecimal.ZERO;
        allAccountsOfAllBanks.add(this);
    }

    public AccountImpl(String holderName, BigDecimal creditLimit) {
        this.balance = BigDecimal.ZERO;
        this.number = generateNumber();
        this.holderName = holderName;
        this.creditLimit = creditLimit;
        allAccountsOfAllBanks.add(this);
    }

    private String generateNumber() {
        Random random = new Random();
        int accountDigits = 100000000 + random.nextInt(900000000);
        if (allAccountsOfAllBanks.stream().anyMatch(n -> n.getNumber().equals("LT" + accountDigits))) {
            generateNumber();
        } else {
            return "LT" + accountDigits;
        }
        return null;
    }

    @Override
    public String getNumber() {
        return this.number;
    }

    @Override
    public String getHolderName() {
        return this.holderName;
    }

    @Override
    public BigDecimal getBalance() {
        return this.balance;
    }

    @Override
    public boolean deposit(BigDecimal amount) {
        this.balance = this.balance.add(amount);
        return true;
    }

    @Override
    public boolean withdraw(BigDecimal amount) {
        if ((creditLimit.add(balance).subtract(amount)).compareTo(BigDecimal.ZERO) >= 0) {
            this.balance = this.balance.subtract(amount);
            return true;
        }
        return false;
    }


}
