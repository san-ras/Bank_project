package lt.techin.bank;

import ibank.Account;
import ibank.Bank;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashSet;

public class MyBank implements Bank {

    private HashSet<Account> accountList = new HashSet<>();

    public MyBank() {
        this.accountList = new HashSet<>();
    }

    @Override
    public int getNumberOfAccounts() {
        return accountList.size();
    }

    @Override
    public BigDecimal getTotalReserves() {
        BigDecimal sum = BigDecimal.ZERO;
        for (Account account : accountList) {
            sum = sum.add(account.getBalance());
        }
        return sum;
    }

    @Override
    public Collection<Account> getAllAccounts() {
        return accountList;
    }

    @Override
    public Account openDebitAccount(String holder) {
        if (getAccountByHolderName(holder) == null) {
            Account debitAcc = new AccountImpl(holder);
            accountList.add(debitAcc);
            return debitAcc;
        }
        return null;
    }

    @Override
    public Account openCreditAccount(String holder, BigDecimal creditLimit) {
        if (getAccountByHolderName(holder) == null) {
            Account creditAcc = new AccountImpl(holder, creditLimit);
            accountList.add(creditAcc);
            return creditAcc;
        }
        return null;
    }

    @Override
    public Account getAccountByHolderName(String holderName) {
        return accountList.stream().filter(n -> n.getHolderName().equals(holderName)).findFirst().orElse(null);
    }

    @Override
    public Account getAccountByNumber(String accNo) {
        return accountList.stream().filter(n -> n.getNumber().equals(accNo)).findFirst().orElse(null);
    }

    @Override
    public void closeAccount(Account account) {
        accountList.remove(account);
    }

}
